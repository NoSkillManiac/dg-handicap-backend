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

import java.util.List;
import java.util.UUID;

@Repository
public class RoundDatabase {

    private Cluster cluster;
    private Collection roundDB;
    private CouchbaseConfig config;

    @Autowired
    public RoundDatabase(final Cluster cluster, final Bucket bucket, final CouchbaseConfig config) {
        this.cluster = cluster;
        this.roundDB = bucket.scope("dg-backend").collection("round");
        this.config = config;
    }

    public Round getRound(final UUID roundId) {
        return roundDB.get(roundId.toString())
                .contentAs(Round.class);
    }

    public List<Round> getRounds(final UUID courseId, final Integer layoutId) {
        String stmt = "SELECT * FROM `" + config.getBucketName() + "`.`dg-backend`.`round`" +
                " WHERE courseId=$1 AND layoutId=$2";
        return cluster.query(stmt, QueryOptions.queryOptions()
                        .parameters(JsonArray.from(courseId, layoutId))
                        .scanConsistency(QueryScanConsistency.REQUEST_PLUS))
                .rowsAs(Round.class);
    }

    public Round addRound(final Round round) {
        roundDB.upsert(round.getRoundId().toString(), round);
        return round;
    }
}
