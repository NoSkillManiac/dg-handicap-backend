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
        return null;
    }

    @Override
    public Course upsertCourse(Course course) {
        return null;
    }

    @Override
    public Course removeCourse(UUID courseId) {
        return null;
    }

    @Override
    public Course upsertLayout(UUID courseId, CourseLayout layout) {
        return null;
    }

    @Override
    public Course removeLayout(UUID courseId, Integer layoutId) {
        return null;
    }
}
