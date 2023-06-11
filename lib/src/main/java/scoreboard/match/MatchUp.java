package scoreboard.match;

import scoreboard.match.team.Team;

import java.util.Objects;

public class MatchUp {

    private final Team homeTeam;
    private final Team awayTeam;

    public MatchUp(String homeTeamName, String awayTeamName) {
        this.homeTeam = new Team(homeTeamName);
        this.awayTeam = new Team(awayTeamName);
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchUp matchUp = (MatchUp) o;
        return homeTeam.equals(matchUp.homeTeam) && awayTeam.equals(matchUp.awayTeam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeTeam, awayTeam);
    }

    @Override
    public String toString() {
        return String.format("%s - %s", homeTeam, awayTeam);
    }
}
