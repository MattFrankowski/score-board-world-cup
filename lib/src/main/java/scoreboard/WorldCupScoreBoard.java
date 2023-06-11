package scoreboard;

import scoreboard.match.MatchInfo;
import scoreboard.match.MatchUp;
import scoreboard.match.Score;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class WorldCupScoreBoard implements ScoreBoard {

    private Map<MatchUp, Score> matches = new HashMap<>();

    @Override
    public void startMatch(String homeTeamName, String awayTeamName) {
        MatchUp matchUp = new MatchUp();
        matches.put(matchUp, new Score());
    }

    @Override
    public List<MatchInfo> getMatchesSummary() {
        return matches.entrySet()
                      .stream()
                      .map(MatchInfo::new)
                      .toList();
    }
}
