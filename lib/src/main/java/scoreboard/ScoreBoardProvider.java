package scoreboard;

public final class ScoreBoardProvider {
    public static ScoreBoard create() {
        return new WorldCupScoreBoard();
    }
}
