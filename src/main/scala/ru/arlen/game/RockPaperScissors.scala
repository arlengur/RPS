package ru.arlen.game

import scala.util.Random

/**
  * @author satovritti
  *
  * RockPaperScissors enter point.
  */
object RockPaperScissors extends App {
  val computerMove = new Random().shuffle(Move.values).head
  private val gameRunner = GameRunner(computerMove)
  UI(gameRunner).play()
}

