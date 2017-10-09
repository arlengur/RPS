package ru.arlen.game

import scala.util.Random

/**
  * @author satovritti
  *
  * RockPaperScissors enter point.
  */
object RockPaperScissors extends App {
  val computerMove = new Random().shuffle(Move.values).head
  private val gameRunner = Game(computerMove)
  UI(gameRunner).play()
}

/**
  * Stores computer move and computes result depending on user move.
  */
case class Game(computerMove: Move) {
  /**
    * Returns result of the game with user and computer moves.
    *
    * @param userMove - user move
    * @return result of the game with user and computer moves
    */
  def start(userMove: Move): (Result, Move, Move) = {
    val result = userMove.winsAgainst(computerMove)
    (result, userMove, computerMove)
  }
}

