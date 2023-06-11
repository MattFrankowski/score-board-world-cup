# Football World Cup Score Board

This project provides a lightweight library that lets the user to create and manage a Football Score Board.

Library's main features:
- Creating a scoreboard
- Starting a new match
- Finishing a match
- Updating match score
- Fetching matches summary sorted by total score.

The library's entrypoint is a factory method located in `ScoreBoardProvider` that returns an implementation of `ScoreBoard` interface:
```java
ScoreBoard scoreBoard = ScoreBoardProvider.create();
``` 
Management of the scoreboard is handled by invoking it's methods:
```java
// starts a new match (with default score 0:0) 
scoreBoard.startMatch("Poland", "Argentina");
// updates match score
scoreBoard.updateScore("Poland", "Argentina", 0, 1);
// starts another match
scoreBoard.startMatch("Switzerland", "Germany");
// gets information about every match in progress (sorted by total score descending)
List<MatchInfo> summary = scoreBoard.getMatchesSummary();
// finishes a match
scoreBoard.finishMatch("Poland", "Argentina");
```

`MatchInfo` instance is a value object that contains information about the match:
- MatchUp
- Score

Example:
```java
// gets the match with the highest total score
MatchInfo matchInfo = scoreBoard.getMatchesSummary()
                                .stream()
                                .findFirst()
                                .orElseThrow(UserCustomException::new);
System.out.println(matchInfo.toString()); // prints "Poland 0 - Argentina 1"
System.out.println(matchInfo.getMatchUp().toString()); // prints "Poland - Argentina"
System.out.println(matchInfo.getMatchUp().getHomeTeam().toString()); // prints "Poland"
System.out.println(matchInfo.getMatchUp().getAwayTeam().toString()); // prints "Argentina"
System.out.println(matchInfo.getScore().toString()); // prints "0 : 1"
``` 
