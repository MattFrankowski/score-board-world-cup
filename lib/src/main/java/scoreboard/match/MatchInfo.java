package scoreboard.match;

import java.util.Map;

public class MatchInfo {
    private final MatchUp matchUp;
    private final Score score;

    public MatchInfo(Map.Entry<MatchUp, Score> scorboardEntry) {
        this.matchUp = scorboardEntry.getKey();
        this.score = scorboardEntry.getValue();
    }

    public Score getScore() {
        return score;
    }
}
