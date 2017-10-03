package ru.arlen.game

/**
  * @author satovritti
  * Returns result of the game.
  */
case class GameRunner(computerMove: Move) {
  def start(playerMove: Move): (Result, Move, Move) = {
    val result = playerMove.winsAgainst(computerMove)
    (result, playerMove, computerMove)
  }
}

