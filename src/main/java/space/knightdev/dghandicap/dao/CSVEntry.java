package space.knightdev.dghandicap.dao;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class CSVEntry {
    //TODO: Validate csv entry positions inside of UDisc Export CSV

    @CsvBindByPosition(position = 0)
    private String uDiscName;

    @CsvBindByPosition(position = 1)
    private Integer roundScore;

}
