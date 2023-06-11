package scoreboard.match;

import java.util.Objects;

public class Score {

    private final Integer homeTeamScore;
    private final Integer awayTeamScore;

    public Score() {
        this.homeTeamScore = 0;
        this.awayTeamScore = 0;
    }

    public Integer getHomeTeamScore() {
        return homeTeamScore;
    }

    public Integer getAwayTeamScore() {
        return awayTeamScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return homeTeamScore.equals(score.homeTeamScore) && awayTeamScore.equals(score.awayTeamScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeTeamScore, awayTeamScore);
    }
}
