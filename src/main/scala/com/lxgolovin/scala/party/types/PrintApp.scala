package com.lxgolovin.scala.party.types

import io.circe.Encoder
import io.circe.generic.auto._

object PrintApp extends App {

  def print[A](a: A)(implicit printable: Print[A]): Unit = println(printable.print(a))

  // TODO: 1) try with sealed trait
  // TODO: 2) check monoid implementation in Haskel
  def printJson[A](a: A)(implicit jsonable: Encoder[A]): Unit = println(jsonable(a).spaces2)

  print(1)
  print(List(1, 2))

  printJson(Color(1, 2, 3, 0))
  printJson(List(Color(1, 2, 3, 0)))
  printJson(Map("color" -> Color(1, 2, 3, 0)))

}
