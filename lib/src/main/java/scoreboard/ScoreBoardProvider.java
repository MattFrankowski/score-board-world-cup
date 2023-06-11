package scoreboard;

public final class ScoreBoardProvider {

    private ScoreBoardProvider() {

    }

    public static ScoreBoard create() {
        return new WorldCupScoreBoard();
    }
}
