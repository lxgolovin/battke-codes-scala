package com.lxgolovin.scala.party.types

import scala.annotation.implicitNotFound

@implicitNotFound("Need to provide converter. Try making implicits in companion object")
trait Print[A] {
  def print(a: A): String
}

object Print {

  implicit val printInt: Print[Int] = (a: Int) => a.toString

  implicit def printList[A](implicit arg: Print[A]): Print[List[A]] = new Print[List[A]] {
    override def print(a: List[A]): String = a.foldLeft("List(")((acc, a) => acc + arg.print(a) + ",") + ")"
  }

}
