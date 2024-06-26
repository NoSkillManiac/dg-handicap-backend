package space.knightdev.dghandicap.service;

import space.knightdev.dghandicap.dao.course.Course;
import space.knightdev.dghandicap.dao.course.CourseLayout;

import java.util.List;
import java.util.UUID;

public interface CourseService {
    Course getCourse(final UUID courseId);
    Course upsertCourse(final Course course);
    Course removeCourse(final UUID courseId);
    List<CourseLayout> getLayoutAtCourse(UUID courseId, Integer layoutId);
    Course upsertLayout(final UUID courseId, final CourseLayout layout);
    Course removeLayout(final UUID courseId, final Integer layoutId);
}
