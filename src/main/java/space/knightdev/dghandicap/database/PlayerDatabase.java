package space.knightdev.dghandicap.database;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import space.knightdev.dghandicap.config.CouchbaseConfig;
import space.knightdev.dghandicap.dao.player.Player;

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

    //TODO: Add PlayerHandicap lookup.

}
