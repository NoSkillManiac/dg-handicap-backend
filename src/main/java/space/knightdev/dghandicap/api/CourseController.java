package space.knightdev.dghandicap.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import space.knightdev.dghandicap.dao.course.Course;
import space.knightdev.dghandicap.dao.course.CourseLayout;
import space.knightdev.dghandicap.service.CourseService;

import java.util.UUID;

@Controller
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(final CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/course/{courseId}")
    public Course getCourse(@PathVariable final UUID courseId) {
        return courseService.getCourse(courseId);
    }

    @PostMapping("/course")
    public Course addOrUpdateCourse(@NonNull final Course course) {
        return courseService.upsertCourse(course);
    }

    @DeleteMapping("/{courseId}")
    public Course removeCourse(@PathVariable final UUID courseId) {
        return courseService.removeCourse(courseId);
    }

    @PostMapping("/{courseId}/layout")
    public Course addOrUpdateLayout(@PathVariable final UUID courseId, @NonNull final CourseLayout layout) {
        return courseService.upsertLayout(courseId, layout);
    }

    @DeleteMapping("/{courseId}/{layoutId}")
    public Course removeLayout(@PathVariable final UUID courseId, @PathVariable final int layoutId) {
        return courseService.removeLayout(courseId, layoutId);
    }
}
