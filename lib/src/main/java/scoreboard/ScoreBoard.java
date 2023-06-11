package scoreboard;

import scoreboard.match.MatchInfo;

import java.util.List;

public interface ScoreBoard {

    void startMatch(String homeTeamName, String awayTeamName);

    List<MatchInfo> getMatchesSummary();
}
