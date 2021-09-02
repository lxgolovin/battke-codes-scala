package com.lxgolovin.battlecodes.list

// other monads

sealed trait Optional[+A] {
  self =>

  def flatMap[B](f: A => Optional[B]): Optional[B] = {
    self match {
      case Nichego => Nichego
      case Just(x) => f(x)
    }
  }

  def map[B](f: A => B): Optional[B] = {
    self match {
      case Nichego => Nichego
      case Just(x) => Just(f(x))
    }
  }
}

object Optional extends App {
  val x: Optional[String] = Just("Hello world")

  def firstWord(str: String): Optional[String] = {
    if (str.isEmpty) {
      Nichego
    } else {
      Just(str.split(" ").head)
    }
  }

  def lastChar(str: String): Optional[Char] = {
    str match {
      case s if s.isEmpty => Nichego
      case s              => Just(s.last)
    }
  }

  println(x.flatMap(firstWord))
  x.map(firstWord).map(println)
  println(x.flatMap(firstWord).flatMap(lastChar).map(_.toInt))

  val y: Optional[String] = Just("Hello world")
  val res: Optional[String] = for {
    frase     <- y
    word      <- firstWord(frase)
    character <- lastChar(word)
  } yield "the result from " + frase + " is " + character.toInt
  println("Got res: " + res)

  println(x.flatMap(frase => {
    firstWord(frase)
      .flatMap(lastChar)
      .map(c => "the result from " + frase + " is " + c.toInt)
  }))
}

object Nichego extends Optional[Nothing]

case class Just[+A](v: A) extends Optional[A]
