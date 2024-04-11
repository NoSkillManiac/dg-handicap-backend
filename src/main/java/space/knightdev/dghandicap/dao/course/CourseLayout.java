package space.knightdev.dghandicap.dao.course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseLayout {
    @Id
    private Integer layoutId;
    private String name;
    private Double ssa;
}