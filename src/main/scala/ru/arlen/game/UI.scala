package ru.arlen.game

import scala.io.StdIn
import scala.util.Try

/**
  * @author satovritti
  *
  * UI class for the game.
  */
case class UI(gameRunner: Game) {
  private var userScore: Int = 0
  private var computerScore: Int = 0
  private var numberOfGames: Int = 0

  /**
    * Start point of the game.
    */
  def play(): Unit = {
    playOnce()
    playAgain()
  }

  /**
    * Runs game once.
    */
  private def playOnce(): Unit = {
    print("Rock Paper Scissors!")

    val numberToMoveNames = Move.numberToMoveName.foldLeft("") { case (result, (number, moveName)) =>
      result + s"\n$number) $moveName"
    }
    val chooseMoveMsg =
      s"""
         |Please choose your move:$numberToMoveNames
         |>""".stripMargin

    retryUntilValidValue(letPlayerChooseIntValueWithMsg(chooseMoveMsg), Move.numberToMoveName.keySet.toSeq: _*) match {
      case Some(playerMove) => printGameResult(gameRunner.start(Move.findByNumber(playerMove)))
      case None => println("\nInvalid input. Exiting program.")
    }
  }

  /**
    * Proposes to repeat the game.
    */
  private def playAgain(): Unit = {
    val chooseMoveMsg =
      s"""
         |1) Play again
         |2) Exit
         |>""".stripMargin

    retryUntilValidValue(letPlayerChooseIntValueWithMsg(chooseMoveMsg), 1, 2) match {
      case Some(mode) if mode == 1 => play()
      case Some(mode) if mode == 2 => printTableResult()
      case None => println("Invalid input. Exiting program.")
    }
  }

  /**
    * Retries the given user function until one of the valid values are returned.
    */
  private def retryUntilValidValue[T](userInputFunc: => Option[T], validValues: T*): Option[T] = {
    Stream.continually(userInputFunc)
      .flatten // only keep user inputs which got something in them
      .find(userInput => validValues contains userInput) // only keep valid values
  }

  /**
    * Prints a message to the user and asks him to enter a value.
    * @param msg - message to the user
    * @return the user input result
    */
  private def letPlayerChooseIntValueWithMsg(msg: String): Option[Int] = {
    print(msg)

    for {
      userInput <- Option(StdIn.readLine())
      input <- Try(userInput.toInt).toOption
    } yield input
  }

  /**
    * Prints result of the single game.
    *
    * @param result contain result of the game, user move and computer move
    */
  private def printGameResult(result: (Result, Move, Move)): Unit = {
    val (gameResult, playerMove, computerMove) = result

    println(s"You move - $playerMove, computer move - $computerMove")
    gameResult match {
      case Win =>
        userScore += 1
        println(s"$playerMove beats $computerMove. You $gameResult.")
      case Lose =>
        computerScore += 1
        println(s"$computerMove beats $playerMove. You $gameResult.")
      case Tie => println("Tie!")
    }
    numberOfGames += 1
  }

  /**
    * Prints the results of all games.
    */
  private def printTableResult(): Unit = {
    // print line
    println(s"+${stars(49)}+")

    // print table headers
    printf("|  %6s  |  %6s  |  %6s  |  %12s  |\n", "WIN", "LOSE", "TIE", "TOTAL GAMES")

    // print line
    println(s"|${stars(10)}+${stars(10)}+${stars(10)}+${stars(16)}+")

    // print results
    val ties = numberOfGames - userScore - computerScore
    printf("|  %6d  |  %6d  |  %6d  |  %12d  |\n", userScore, computerScore, ties, numberOfGames)

    // print line
    println(s"+${stars(49)}+")
    println("Thanks for playing!")
  }

  /**
    * Prints string with certain number of stars.
    *
    * @param count number of stars
    * @return string with stars
    */
  private def stars(count: Int) = "*" * count
}