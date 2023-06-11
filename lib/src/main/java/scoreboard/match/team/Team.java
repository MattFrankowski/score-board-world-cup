package scoreboard.match.team;

import java.util.Objects;

public class Team {
    private final String name;
    private final String code;

    public Team(String name) {
        this.name = name;
        this.code = name.toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return code.equals(team.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
