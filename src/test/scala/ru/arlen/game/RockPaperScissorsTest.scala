package ru.arlen.game

import org.junit.Assert._
import org.junit.Test

/**
  * @author satovritti
  */
class RockPaperScissorsTest {
  @Test def rockVsPaper(): Unit = {
    val gameRunner = Game(Paper).start(Rock)
    assertEquals(gameRunner, (Lose, Rock, Paper))
  }

  @Test def rockVsScissors(): Unit = {
    val gameRunner = Game(Scissors).start(Rock)
    assertEquals(gameRunner, (Win, Rock, Scissors))
  }

  @Test def rockVsRock(): Unit = {
    val gameRunner = Game(Rock).start(Rock)
    assertEquals(gameRunner, (Tie, Rock, Rock))
  }

  @Test def paperVsPaper(): Unit = {
    val gameRunner = Game(Paper).start(Paper)
    assertEquals(gameRunner, (Tie, Paper, Paper))
  }

  @Test def paperVsScissors(): Unit = {
    val gameRunner = Game(Scissors).start(Paper)
    assertEquals(gameRunner, (Lose, Paper, Scissors))
  }

  @Test def paperVsRock(): Unit = {
    val gameRunner = Game(Rock).start(Paper)
    assertEquals(gameRunner, (Win, Paper, Rock))
  }

  @Test def scissorsVsPaper(): Unit = {
    val gameRunner = Game(Paper).start(Scissors)
    assertEquals(gameRunner, (Win, Scissors, Paper))
  }

  @Test def scissorsVsScissors(): Unit = {
    val gameRunner = Game(Scissors).start(Scissors)
    assertEquals(gameRunner, (Tie, Scissors, Scissors))
  }

  @Test def scissorsVsRock(): Unit = {
    val gameRunner = Game(Rock).start(Scissors)
    assertEquals(gameRunner, (Lose, Scissors, Rock))
  }

}
