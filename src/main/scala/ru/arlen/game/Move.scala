package ru.arlen.game

import scala.collection.immutable.SortedMap

/**
  * @author satovritti
  *
  * Util object for moves
  */
case object Move {
  val values: List[Move] = List(Rock, Paper, Scissors)

  /**
    * Maps number to Move name
    */
  lazy val numberToMoveName: SortedMap[Int, String] = {
    val elements = values
      .zipWithIndex
      .map { case (gesture, index) => (index + 1, gesture.toString) }
      .sortBy(_._1)

    SortedMap(elements: _*)
  }

  /**
    * Returns move by number.
    * @param number - index in the list
    * @return Move object
    */
  def findByNumber(number: Int): Move = values(number - 1)
}

/**
  * Abstract trait for moves.
  */
sealed trait Move {
  /**
    * Returns the result of the comparison of moves.
    *
    * @param opponentMove - computer move
    * @return - result of the comparison of moves
    */
  def winsAgainst(opponentMove: Move): Result
}

/**
  * Rock move.
  */
case object Rock extends Move {
  def winsAgainst(opponentMove: Move): Result = opponentMove match {
    case _: Rock.type => Tie
    case _: Scissors.type => Win
    case _: Paper.type => Lose
  }
}

/**
  * Paper move.
  */
case object Paper extends Move {
  def winsAgainst(opponentMove: Move): Result = opponentMove match {
    case _: Rock.type => Win
    case _: Scissors.type => Lose
    case _: Paper.type => Tie
  }
}

/**
  * Scissors move.
  */
case object Scissors extends Move {
  def winsAgainst(opponentMove: Move): Result = opponentMove match {
    case _: Rock.type => Lose
    case _: Scissors.type => Tie
    case _: Paper.type => Win
  }
}

