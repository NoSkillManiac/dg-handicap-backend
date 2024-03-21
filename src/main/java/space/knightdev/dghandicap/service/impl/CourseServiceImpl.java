package space.knightdev.dghandicap.service.impl;

import org.springframework.stereotype.Service;
import space.knightdev.dghandicap.dao.course.Course;
import space.knightdev.dghandicap.dao.course.CourseLayout;
import space.knightdev.dghandicap.database.CourseDatabase;
import space.knightdev.dghandicap.service.CourseService;

import java.util.UUID;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseDatabase courseDatabase;

    public CourseServiceImpl(final CourseDatabase courseDatabase) {
        this.courseDatabase = courseDatabase;
    }

    @Override
    public Course getCourse(UUID courseId) {
        return courseDatabase.getCourse(courseId);
    }

    @Override
    public Course upsertCourse(Course course) {
        return courseDatabase.upsertCourse(course);
    }

    @Override
    public Course removeCourse(UUID courseId) {
        return null;
    }

    @Override
    public CourseLayout getLayoutAtCourse(UUID courseId, Integer layoutId) {
        return courseDatabase.getCourseLayout(courseId, layoutId);
    }

    @Override
    public Course upsertLayout(UUID courseId, CourseLayout layout) {
        return courseDatabase.upsertLayoutForCourse(courseId, layout);
    }

    @Override
    public Course removeLayout(UUID courseId, Integer layoutId) {
        return null;
    }
}
