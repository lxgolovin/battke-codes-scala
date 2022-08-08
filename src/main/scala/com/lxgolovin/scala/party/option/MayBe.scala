package com.lxgolovin.scala.party.option

sealed trait MayBe[+A] {
  self =>

  def flatMap[B](f: A => MayBe[B]): MayBe[B] = {
    self match {
      case NoValue      => NoValue
      case SomeValue(x) => f(x)
    }
  }

  def map[B](f: A => B): MayBe[B] = {
    self match {
      case NoValue      => NoValue
      case SomeValue(x) => SomeValue(f(x))
    }
  }
}

object MayBe {
  def apply[A](value: A): MayBe[A] = {
    value match {
      case v if v == null => NoValue
      case v              => SomeValue(v)
    }
  }
}

object NoValue extends MayBe[Nothing]

case class SomeValue[A](value: A) extends MayBe[A]
