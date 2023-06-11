package scoreboard.validation;

import scoreboard.match.MatchUp;
import scoreboard.match.Score;
import scoreboard.match.team.Team;

import java.util.Map;

public final class AddMatchValidator {

    private AddMatchValidator() {

    }

    public static boolean isAnyTeamInExistingMatch(Map<MatchUp, Score> matches, String homeTeamName, String awayTeamName) {
        return matches.keySet()
                      .stream()
                      .anyMatch(existingMatchUp -> isAnyTeamInExistingMatch(existingMatchUp, homeTeamName, awayTeamName));
    }

    private static boolean isAnyTeamInExistingMatch(MatchUp existingMatchUp, String homeTeamName, String awayTeamName) {
        return existingMatchUp.getSides()
                              .stream()
                              .map(Team::getCode)
                              .anyMatch(teamInGameCode -> isAnyTeamEqualToCurrentlyPlayingTeam(teamInGameCode, homeTeamName,
                                                                                               awayTeamName));
    }

    private static boolean isAnyTeamEqualToCurrentlyPlayingTeam(String teamInGameCode, String homeTeamName,
                                                                String awayTeamName) {
        return teamInGameCode.equalsIgnoreCase(homeTeamName) || teamInGameCode.equalsIgnoreCase(awayTeamName);
    }
}
