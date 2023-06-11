package scoreboard;

import org.junit.jupiter.api.Test;
import scoreboard.match.MatchInfo;
import scoreboard.match.MatchUp;
import scoreboard.match.Score;

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

    @Test
    void should_set_initial_score_to_zero() {
        // given
        ScoreBoard scoreBoard = new WorldCupScoreBoard();

        // when
        scoreBoard.startMatch("Poland", "Argentina");
        List<MatchInfo> resultSummary = scoreBoard.getMatchesSummary();

        // then
        assertEquals(resultSummary.get(0).getScore().getAwayTeamScore(), 0);
        assertEquals(resultSummary.get(0).getScore().getHomeTeamScore(), 0);
    }

    @Test
    void should_update_match_score() {
        // given
        ScoreBoard scoreBoard = new WorldCupScoreBoard();
        scoreBoard.startMatch("Poland", "Argentina");

        // when
        scoreBoard.updateScore("Poland", "Argentina", 0, 1);
        List<MatchInfo> resultSummary = scoreBoard.getMatchesSummary();

        // then
        assertEquals(resultSummary.get(0).getScore().getHomeTeamScore(), 0);
        assertEquals(resultSummary.get(0).getScore().getAwayTeamScore(), 1);
    }

    @Test
    void should_not_permit_to_update_nonexistent_match_score() {
        // given
        ScoreBoard scoreBoard = new WorldCupScoreBoard();

        // when & then
        Exception result = assertThrows(IllegalStateException.class,
                                        () -> scoreBoard.updateScore("Poland", "Argentina",
                                                       1, 1));
        List<MatchInfo> matchInfo = scoreBoard.getMatchesSummary();

        assertEquals(matchInfo.size(), 0);
        assertEquals(result.getMessage(), "Cannot update score for non-existent match: Poland - Argentina");
    }

    @Test
    void should_sort_matches_summary_by_total_score() {
        // given
        ScoreBoard scoreBoard = new WorldCupScoreBoard();

        scoreBoard.startMatch("Poland", "Argentina");
        scoreBoard.updateScore("Poland", "Argentina", 0, 1);

        scoreBoard.startMatch("Mexico", "Sweden");
        scoreBoard.updateScore("Mexico", "Sweden", 3, 2);

        scoreBoard.startMatch("Spain", "Germany");
        scoreBoard.updateScore("Spain", "Germany", 1, 1);

        // when
        List<MatchInfo> resultSummary = scoreBoard.getMatchesSummary();

        // then
        assertEquals(resultSummary.get(0).getMatchUp(), new MatchUp("Mexico", "Sweden"));
        assertEquals(resultSummary.get(0).getScore(), new Score(3, 2));

        assertEquals(resultSummary.get(1).getMatchUp(), new MatchUp("Spain", "Germany"));
        assertEquals(resultSummary.get(1).getScore(), new Score(1, 1));

        assertEquals(resultSummary.get(2).getMatchUp(), new MatchUp("Poland", "Argentina"));
        assertEquals(resultSummary.get(2).getScore(), new Score(0, 1));
    }

    @Test
    void should_not_permit_to_start_a_match_for_a_team_already_playing() {
        // given
        ScoreBoard scoreBoard = new WorldCupScoreBoard();
        scoreBoard.startMatch("Mexico", "Argentina");

        // when & then
        Exception result = assertThrows(IllegalStateException.class,
                                        () -> scoreBoard.startMatch("Poland", "Argentina"));

        assertEquals(result.getMessage(), "Cannot start a match for a team that is already in game. Match: Poland - Argentina");
    }
}
