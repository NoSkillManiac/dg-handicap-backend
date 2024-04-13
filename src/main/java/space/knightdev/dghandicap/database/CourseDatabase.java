package space.knightdev.dghandicap.database;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.json.JsonArray;
import com.couchbase.client.java.query.QueryOptions;
import com.couchbase.client.java.query.QueryScanConsistency;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import space.knightdev.dghandicap.config.CouchbaseConfig;
import space.knightdev.dghandicap.dao.course.Course;
import space.knightdev.dghandicap.dao.course.CourseLayout;

import java.util.List;
import java.util.UUID;

@Slf4j
@Repository
public class CourseDatabase {

    private static final String COURSE_COLLECTION = "course";

    private Cluster cluster;
    private Collection courseDb;
    private CouchbaseConfig config;

    @Autowired
    public CourseDatabase(final Cluster cluster, final Bucket bucket, final CouchbaseConfig config) {
        this.cluster = cluster;
        this.courseDb = bucket.scope("dg-backend").collection(COURSE_COLLECTION);
        this.config = config;
    }

    public List<CourseLayout> getCourseLayout(final UUID courseId, final Integer layoutId) {
        String stmt = "SELECT * FROM `" + config.getBucketName() + "`.`dg-backend`.`" + COURSE_COLLECTION +
                "` WHERE courseId=$1 AND layoutId=$2";
        return cluster.query(stmt, QueryOptions.queryOptions()
                        .parameters(JsonArray.from(courseId, layoutId))
                        .scanConsistency(QueryScanConsistency.REQUEST_PLUS))
                .rowsAs(CourseLayout.class);
    }

    public Course getCourse(final UUID courseId) {
        return courseDb.get(courseId.toString()).contentAs(Course.class);
    }

    public Course upsertCourse(final Course course) {
        courseDb.upsert(course.getCourseId().toString(), course);
        return course;
    }

    public Course upsertLayoutForCourse(final UUID courseId, final CourseLayout layout) {
        return null;
    }

}
