package com.lxgolovin.scala.party.types

import MonoidLike._
import More._

object TypesApp extends App {

  def addAll[A](xs: List[A])(implicit addable: MonoidLike[A]): A =
//    xs.foldLeft(addable.empty)(addable.add)
//  same as this:
    xs.foldLeft(addable.empty)(_ |+| _)

  def combine[A](a: A, b: A)(implicit monoid: MonoidLike[A]): A =
    a |+| b

  println(addAll(List(1, 2, 3)))
  println(addAll(List("1", "2", "3")))
  println(addAll(List(List("1", "2", "3"), List("1"))))
  println(addAll(List(Color(100, 100, 100, 1.9), Color(20, 20, 20, 0.5))))
  println(Color(10, 10, 10, 1) |+| Color(25, 25, 25, 0.8))
  println(Color(10, 10, 10, 1) < Color(25, 25, 25, 0.8))
  println(Color(10, 10, 10, 1).compare(Color(25, 25, 25, 0.8)))

  println(Color(10, 10, 10, 1) > Color(25, 25, 25, 0.8))
  println(Color(25, 25, 25, 1) == Color(25, 25, 25, 1))
  println(Color(25, 25, 25, 1) <= Color(25, 25, 25, 1))
  println(Color(25, 25, 25, 1) >= Color(25, 25, 25, 1))

  println("Hello " |+| "World")
  println(combine("Hello ", "World"))
}
