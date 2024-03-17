package space.knightdev.dghandicap.database;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import space.knightdev.dghandicap.dao.Round;

import java.util.List;
import java.util.UUID;

@Service
public class RoundDatabase {

    private final MongoTemplate mongoTemplate;
    private static final String ROUND_COLLECTION = "round";

    public RoundDatabase(final MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Round getRound(final UUID roundId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(roundId));
        return mongoTemplate.findOne(query, Round.class, ROUND_COLLECTION);
    }

    public List<Round> getRounds(final UUID courseId, final Integer layoutId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("courseId").is(courseId));
        query.addCriteria(Criteria.where("layoutId").is(layoutId));
        return mongoTemplate.find(query,Round.class, ROUND_COLLECTION);
    }




}
