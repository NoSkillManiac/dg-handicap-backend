package space.knightdev.dghandicap.dao.course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @Builder.Default
    private UUID courseId = UUID.randomUUID();
    private String name;
    private List<CourseLayout> layout;
}
