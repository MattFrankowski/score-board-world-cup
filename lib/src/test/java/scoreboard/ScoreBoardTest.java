package scoreboard;

import org.junit.jupiter.api.Test;
import scoreboard.match.MatchInfo;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreBoardTest {
    @Test
    void should_start_a_new_match() {
        // given
        ScoreBoard scoreBoard = new WorldCupScoreBoard();

        // when
        scoreBoard.startMatch("Poland", "Argentina");
        List<MatchInfo> resultSummary = scoreBoard.getMatchesSummary();

        // then
        assertEquals(resultSummary.size(), 1);
    }
}
