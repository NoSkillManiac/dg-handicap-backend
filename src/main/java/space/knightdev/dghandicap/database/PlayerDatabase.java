package space.knightdev.dghandicap.database;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.json.JsonArray;
import com.couchbase.client.java.query.QueryOptions;
import com.couchbase.client.java.query.QueryScanConsistency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import space.knightdev.dghandicap.config.CouchbaseConfig;
import space.knightdev.dghandicap.dao.Round;
import space.knightdev.dghandicap.dao.player.Player;

import java.util.List;
import java.util.UUID;

@Repository
public class PlayerDatabase {

    private static final String PLAYER_COLLECTION = "player";

    private Cluster cluster;
    private Collection playerDb;
    private CouchbaseConfig config;

    @Autowired
    public PlayerDatabase(final Cluster cluster, final Bucket bucket, final CouchbaseConfig config) {
        this.cluster = cluster;
        this.playerDb = bucket.scope("dg-backend").collection(PLAYER_COLLECTION);
        this.config = config;
    }

    public Player upsertPlayer(final Player player){
        playerDb.upsert(player.getPlayerId().toString(), player);
        return player;
    }

    public Player getPlayer(final UUID playerId) {
        return playerDb.get(playerId.toString())
                .contentAs(Player.class);
    }

    public UUID deletePlayer(final UUID playerId) {
        playerDb.remove(playerId.toString());
        return playerId;
    }

    public List<Player> getPlayerByUDisc(final String uDiscId) {
        String stmt = "SELECT * FROM `" + config.getBucketName() + "`.`dg-backend`.`" + PLAYER_COLLECTION +
                "` WHERE uDiscId=$1 LIMIT 1";
        return cluster.query(stmt, QueryOptions.queryOptions()
                        .parameters(JsonArray.from(uDiscId))
                        .scanConsistency(QueryScanConsistency.REQUEST_PLUS))
                .rowsAs(Player.class);
    }

    //TODO: Add PlayerHandicap lookup.

}
