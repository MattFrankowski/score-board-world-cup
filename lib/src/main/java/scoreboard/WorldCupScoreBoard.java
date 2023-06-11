package scoreboard;

import scoreboard.match.MatchInfo;

import java.util.List;

class WorldCupScoreBoard implements ScoreBoard {

    @Override
    public void startMatch(String homeTeamName, String awayTeamName) {

    }

    @Override
    public List<MatchInfo> getMatchesSummary() {
        return List.of();
    }
}
