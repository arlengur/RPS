package ru.arlen.game

/**
  * @author satovritti
  *
  * Result trait of the game.
  */
sealed trait Result

/**
  * User won.
  */
case object Win extends Result

/**
  * User lost.
  */
case object Lose extends Result

/**
  * Tie.
  */
case object Tie extends Result