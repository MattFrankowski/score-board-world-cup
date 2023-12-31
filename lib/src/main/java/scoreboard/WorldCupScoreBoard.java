package scoreboard;

import scoreboard.match.MatchInfo;
import scoreboard.match.MatchUp;
import scoreboard.match.Score;
import scoreboard.validation.AddMatchValidator;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class WorldCupScoreBoard implements ScoreBoard {

    private Map<MatchUp, Score> matches = new HashMap<>();

    @Override
    public void startMatch(String homeTeamName, String awayTeamName) {
        MatchUp matchUp = new MatchUp(homeTeamName, awayTeamName);
        if (AddMatchValidator.isAnyTeamInExistingMatch(matches, homeTeamName, awayTeamName)) {
            throw new IllegalStateException(String.format("Cannot start a match for a team that is already in game. Match: %s - %s",
                                                          homeTeamName, awayTeamName));
        }
        matches.put(matchUp, new Score());
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
