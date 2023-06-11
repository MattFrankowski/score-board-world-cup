package scoreboard;

import scoreboard.match.MatchInfo;
import scoreboard.match.MatchUp;
import scoreboard.match.Score;
import scoreboard.match.team.Team;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class WorldCupScoreBoard implements ScoreBoard {

    private Map<MatchUp, Score> matches = new HashMap<>();

    @Override
    public void startMatch(String homeTeamName, String awayTeamName) {
        MatchUp matchUp = new MatchUp(homeTeamName, awayTeamName);
        if (isAnyTeamInMatch(homeTeamName, awayTeamName)) {
            throw new IllegalStateException(String.format("Cannot start a match for a team that is already in game. Match: %s - %s",
                                                          homeTeamName, awayTeamName));
        }
        matches.put(matchUp, new Score());
    }

    private boolean isAnyTeamInMatch(String homeTeamName, String awayTeamName) {
        return matches.keySet()
                      .stream()
                      .anyMatch(matchUp -> isAnyTeamInMatch(matchUp, homeTeamName, awayTeamName));
    }

    private boolean isAnyTeamInMatch(MatchUp inGameMatchUp, String homeTeamName, String awayTeamName) {
        return inGameMatchUp.getSides()
                            .stream()
                            .map(Team::getCode)
                            .anyMatch(teamInGameCode -> isAnyTeamEqualToCurrentlyPlayingTeam(teamInGameCode, homeTeamName,
                                                                                             awayTeamName));
    }

    private static boolean isAnyTeamEqualToCurrentlyPlayingTeam(String teamInGameCode, String homeTeamName,
                                                                String awayTeamName) {
        return teamInGameCode.equalsIgnoreCase(homeTeamName) || teamInGameCode.equalsIgnoreCase(awayTeamName);
    }

    @Override
    public void finishMatch(String homeTeamName, String awayTeamName) {
        MatchUp matchUp = new MatchUp(homeTeamName, awayTeamName);
        if (matches.remove(matchUp) == null) {
            throw new IllegalStateException(String.format("Cannot finish non-existent match: %s - %s",
                                                          homeTeamName, awayTeamName));
        }
    }

    @Override
    public void updateScore(String homeTeamName, String awayTeamName, Integer homeTeamScore, Integer awayTeamScore) {
        MatchUp matchUp = new MatchUp(homeTeamName, awayTeamName);
        if (!matches.containsKey(matchUp)) {
            throw new IllegalStateException(String.format("Cannot update score for non-existent match: %s - %s",
                                                          homeTeamName, awayTeamName));
        }
        matches.put(matchUp, new Score(homeTeamScore, awayTeamScore));
    }

    @Override
    public List<MatchInfo> getMatchesSummary() {
        return matches.entrySet()
                      .stream()
                      .map(MatchInfo::new)
                      .sorted(getSortingPolicy())
                      .toList();
    }

    @Override
    public Comparator<MatchInfo> getSortingPolicy() {
        return Comparator.comparingInt(MatchInfo::getTotalScore).reversed();
    }
}
