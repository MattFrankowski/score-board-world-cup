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
        MatchUp matchUp = new MatchUp(homeTeamName, awayTeamName);
        if (matches.containsKey(matchUp)) {
            throw new IllegalStateException(String.format("Cannot start a duplicate match: %s - %s",
                                                          homeTeamName, awayTeamName));
        }
        matches.put(matchUp, new Score());
    }

    @Override
    public void finishMatch(String homeTeamName, String awayTeamName) {

    }

    @Override
    public List<MatchInfo> getMatchesSummary() {
        return matches.entrySet()
                      .stream()
                      .map(MatchInfo::new)
                      .toList();
    }
}
