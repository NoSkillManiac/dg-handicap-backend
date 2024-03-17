package space.knightdev.dghandicap.dao.course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class CourseLayout {
    @Id
    private Integer layoutId;
    private String name;
    private Double ssa;
}