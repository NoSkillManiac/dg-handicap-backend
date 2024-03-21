package space.knightdev.dghandicap.database;

import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReplaceOptions;
import org.springframework.data.mongodb.core.query.BasicUpdate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import org.springframework.stereotype.Service;
import space.knightdev.dghandicap.dao.course.Course;
import space.knightdev.dghandicap.dao.course.CourseLayout;

import java.util.UUID;

@Slf4j
@Service
public class CourseDatabase {

    private final MongoTemplate mongoTemplate;
    private static final String COURSE_COLLECTION = "course";

    @Autowired
    public CourseDatabase(final MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public CourseLayout getCourseLayout(final UUID courseId, final Integer layoutId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(courseId));
        query.addCriteria(Criteria.where("_id").is(layoutId));
        return mongoTemplate.findOne(query, CourseLayout.class, COURSE_COLLECTION);
    }

    public Course getCourse(final UUID courseId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(courseId));
        return mongoTemplate.findOne(query, Course.class, COURSE_COLLECTION);
    }

    public Course upsertCourse(final Course course) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(course.getCourseId()));

        mongoTemplate.replace(query, course, ReplaceOptions.replaceOptions().upsert() , COURSE_COLLECTION);
        return course;
    }

    public Course upsertLayoutForCourse(final UUID courseId, final CourseLayout layout) {
        return null;
    }

}
