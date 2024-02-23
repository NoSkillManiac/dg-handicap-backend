package space.knightdev.dghandicap.database;

import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoDatabase {

    @Value("mongo.client.connection")
    private String mongoUrl;

    public Document executeCommand(final String database, final Bson filter) {
        return null;
    }

    public MongoCursor<Document> findRecord(final String database, final Bson filter) {
        return null;
    }

    public InsertManyResult bulkInsert(final String database, final List<Document> documents) {
        return null;
    }

    public UpdateResult updateRecord(final String database, final Bson filter, final Document document) {
        return null;
    }

    public DeleteResult deleteRecord(final String database, final Bson filter) {
        return null;
    }
}
