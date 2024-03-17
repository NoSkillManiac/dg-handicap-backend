package space.knightdev.dghandicap.dao.course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.UUID;

@Builder
@Document("course")
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    private UUID courseId = UUID.randomUUID();
    private String name;
    private List<CourseLayout> layout;
}
