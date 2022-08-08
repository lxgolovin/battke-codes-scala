package com.lxgolovin.scala.party.state

class EasyStack {}

object EasyStack extends App {

  def push(value: Int, list: List[Int]): (Unit, List[Int]) = {
    ((), value :: list)
  }

  def pop(list: List[Int]): (Option[Int], List[Int]) = {
    list match {
      case Nil    => (None, list)
      case x :: l => (Some(x), l)
    }
  }

  def replace(value: Int, list: List[Int]): (Option[Int], List[Int]) = {
    pop(list) match {
      case (v, l) => (v, push(value, l)._2)
    }
  }

  val l = List(1, 2, 3)

  val (_, newL) = replace(5, l)
  println(newL)
  println(replace(6, newL))
}
