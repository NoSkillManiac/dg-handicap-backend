package space.knightdev.dghandicap.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import space.knightdev.dghandicap.dao.course.Course;
import space.knightdev.dghandicap.dao.course.CourseLayout;
import space.knightdev.dghandicap.service.CourseService;

import java.util.UUID;

@RestController
@RequestMapping("course")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(final CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/{courseId}")
    public Course getCourse(@PathVariable("courseId") final UUID courseId) {
        return courseService.getCourse(courseId);
    }

    @PostMapping("/")
    public Course addOrUpdateCourse(@NonNull @RequestBody final Course course) {
        return courseService.upsertCourse(course);
    }

    @DeleteMapping("/{courseId}")
    public Course removeCourse(@PathVariable("courseId") final UUID courseId) {
        return courseService.removeCourse(courseId);
    }

    @GetMapping("/{courseId}/layout/{layoutId}")
    public CourseLayout getLayoutAtCourse(@PathVariable("courseId") final UUID courseId,
                                          @PathVariable("layoutId") final Integer layoutId) {
        return courseService.getLayoutAtCourse(courseId, layoutId).get(0);
    }

    @PostMapping("/{courseId}/layout")
    public Course addOrUpdateLayout(@PathVariable("courseId") final UUID courseId,
                                    @NonNull @RequestBody final CourseLayout layout) {
        return courseService.upsertLayout(courseId, layout);
    }

    @DeleteMapping("/{courseId}/layout/{layoutId}")
    public Course removeLayout(@PathVariable("courseId") final UUID courseId,
                               @PathVariable("layoutId") final int layoutId) {
        return courseService.removeLayout(courseId, layoutId);
    }
}
