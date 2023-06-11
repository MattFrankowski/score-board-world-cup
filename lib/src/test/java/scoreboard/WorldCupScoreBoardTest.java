package scoreboard;

import org.junit.jupiter.api.Test;
import scoreboard.match.MatchInfo;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WorldCupScoreBoardTest {
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

    @Test
    void should_not_permit_adding_duplicate_match() {
        // given
        ScoreBoard scoreBoard = new WorldCupScoreBoard();
        scoreBoard.startMatch("Poland", "Argentina");

        // when & then
        Exception result = assertThrows(IllegalStateException.class,
                                        () -> scoreBoard.startMatch("Poland", "Argentina"));
        assertEquals(result.getMessage(), "Cannot start a duplicate match: Poland - Argentina");
    }

    @Test
    void should_finish_an_existing_match() {
        // given
        ScoreBoard scoreBoard = new WorldCupScoreBoard();
        scoreBoard.startMatch("Poland", "Argentina");

        // when
        scoreBoard.finishMatch("Poland", "Argentina");
        List<MatchInfo> resultSummary = scoreBoard.getMatchesSummary();

        // then
        assertEquals(resultSummary.size(), 0);
    }

    @Test
    void should_not_permit_to_finish_nonexistent_match() {
        // given
        ScoreBoard scoreBoard = new WorldCupScoreBoard();

        // when & then
        Exception result = assertThrows(IllegalStateException.class,
                                        () -> scoreBoard.finishMatch("Poland", "Argentina"));
        assertEquals(result.getMessage(), "Cannot finish non-existent match: Poland - Argentina");
    }
}
