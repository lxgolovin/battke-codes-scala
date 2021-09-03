package com.lxgolovin.scala.party.types

trait More[T] {
  def more(a: T, b: T): Int
}

object More {
  implicit class MoreOperations[A](a: A)(implicit more: More[A]) {
    def >(b: A): Boolean  = more.more(a, b) > 0
    def >=(b: A): Boolean = (a == b) || (a > b)
  }
}
