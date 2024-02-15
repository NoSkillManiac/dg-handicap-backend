package space.knightdev.dghandicap.dao.course;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;

@Data
public class Courses {
    @Id
    @NonNull
    private UUID courseId;
    private String name;
    private List<CourseLayout> layout;
}
