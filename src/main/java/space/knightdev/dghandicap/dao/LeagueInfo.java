package space.knightdev.dghandicap.dao;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import space.knightdev.dghandicap.dao.course.Course;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@Document
public class LeagueInfo {
    @Id
    private UUID leagueId;
    private String leagueName;
    private List<Course> courses;
}
