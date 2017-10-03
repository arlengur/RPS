package ru.arlen.game

/**
  * @author satovritti
  */
import scala.collection.immutable.SortedMap

/**
  * Util object for moves
  */
case object Move {
  val values: List[Move] = List(Rock, Paper, Scissors)

  lazy val numberToMoveName: SortedMap[Int, String] = {
    val elements = values
      .zipWithIndex
      .map { case (gesture, index) => (index + 1, gesture.toString) }
      .sortBy(_._1)

    SortedMap(elements: _*)
  }

  def findByNumber(number: Int): Move = values(number - 1)
}

/**
  * All moves the game supports.
  */
sealed trait Move {
  def winsAgainst(opponentGesture: Move): Result
}

/**
  * Rock move
  */
case object Rock extends Move {
  def winsAgainst(opponentMove: Move): Result = opponentMove match {
    case _: Rock.type => Tie
    case _: Scissors.type => Win
    case _: Paper.type => Lose
  }
}

/**
  * Paper move
  */
case object Paper extends Move {
  def winsAgainst(opponentMove: Move): Result = opponentMove match {
    case _: Rock.type => Win
    case _: Scissors.type => Lose
    case _: Paper.type => Tie
  }
}

/**
  * Scissors move
  */
case object Scissors extends Move {
  def winsAgainst(opponentMove: Move): Result = opponentMove match {
    case _: Rock.type => Lose
    case _: Scissors.type => Tie
    case _: Paper.type => Win
  }
}

/**
  * Result trait of the game.
  */
sealed trait Result

case object Win extends Result

case object Lose extends Result

case object Tie extends Result