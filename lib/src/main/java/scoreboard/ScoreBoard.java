package scoreboard;

import scoreboard.match.MatchInfo;

import java.util.Comparator;
import java.util.List;

public interface ScoreBoard {

    void startMatch(String homeTeamName, String awayTeamName);

    void finishMatch(String homeTeamName, String awayTeamName);

    void updateScore(String homeTeamName, String awayTeamName, Integer homeTeamScore, Integer awayTeamScore);

    List<MatchInfo> getMatchesSummary();

    Comparator<MatchInfo> getSortingPolicy();
}
