package scoreboard.match;

import java.util.Map;
import java.util.Objects;

public class MatchInfo {

    private final MatchUp matchUp;
    private final Score score;

    public MatchInfo(Map.Entry<MatchUp, Score> scorboardEntry) {
        this.matchUp = scorboardEntry.getKey();
        this.score = scorboardEntry.getValue();
    }

    public MatchUp getMatchUp() {
        return matchUp;
    }

    public Score getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchInfo matchInfo = (MatchInfo) o;
        return matchUp.equals(matchInfo.matchUp) && score.equals(matchInfo.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchUp, score);
    }
}
