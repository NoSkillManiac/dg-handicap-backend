package space.knightdev.dghandicap.service;

import space.knightdev.dghandicap.dao.course.Course;
import space.knightdev.dghandicap.dao.course.CourseLayout;

import java.util.UUID;

public interface CourseService {
    Course getCourse(final UUID courseId);
    Course upsertCourse(final Course course);
    Course removeCourse(final UUID courseId);
    Course upsertLayout(final UUID courseId, final CourseLayout layout);
    Course removeLayout(final UUID courseId, final int layoutId);
}
