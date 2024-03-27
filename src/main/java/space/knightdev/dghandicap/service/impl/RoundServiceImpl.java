package space.knightdev.dghandicap.service.impl;

import jdk.jshell.spi.ExecutionControl;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.InvalidFileNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.knightdev.dghandicap.dao.Round;
import space.knightdev.dghandicap.database.RoundDatabase;
import space.knightdev.dghandicap.dto.RoundDetail;
import space.knightdev.dghandicap.service.RoundService;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class RoundServiceImpl implements RoundService {

    private final RoundDatabase roundDatabase;

    @Autowired
    public RoundServiceImpl(final RoundDatabase roundDatabase) {
        this.roundDatabase = roundDatabase;
    }

    @Override
    public Round addRound(File newRound) {
        if (!newRound.getName().endsWith(".csv")) {
            throw new InvalidFileNameException(newRound.getName(), "File is not a csv.");
        }
        // File will need to be stored on the local filestore, and retrieved after the fact


        return null;
    }

    @Override
    public Round updateRound(RoundDetail roundDetail) {
        log.error("Method not yet implemented.");
        return null;
    }

    @Override
    public Round getRound(UUID roundId) {
        return roundDatabase.getRound(roundId);
    }

    @Override
    public List<Round> getRounds(UUID courseId, Integer layoutId) {
        return roundDatabase.getRounds(courseId, layoutId);
    }
}
