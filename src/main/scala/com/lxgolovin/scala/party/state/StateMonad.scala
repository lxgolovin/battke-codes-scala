package com.lxgolovin.scala.party.state

case class StateMonad[A, S](exec: S => (A, S)) {

  def map[B](f: A => B): StateMonad[B, S] = {
    StateMonad { s =>
      exec(s) match {
        case (a, sa) => (f(a), sa)
      }
    }
  }

  def flatMap[B](f: A => StateMonad[B, S]): StateMonad[B, S] = {
    StateMonad { s =>
      exec(s) match {
        case (a, sa) => f(a).exec(sa)
      }
    }
  }
}

case class Writer[A, S](value: (A, List[S])) {

  def map[B](f: A => B): Writer[B, S] = {
    Writer {
      value match {
        case (a, l) => (f(a), l)
      }
    }
  }

  def flatMap[B](f: A => Writer[B, S]): Writer[B, S] = {
    // TODO: with match case and val style
    // def update(f: S => S): State[A, S]
    // def pure[A, S](a: A): State[A, S]
    // remove the list and make monoid for S
    /*
    wa: Writer[A, S] => {
      val tuple = wa.value match {
        case (a, la) => (f(a), la)
      }
      val initialList =
      val (b, lb): (B, List[B]) = w.value
      Writer((b, lb))
    }

     */
    ???
  }
}

object Writer {
  def pure[A, S](a: A): Writer[A, S] = Writer((a, List.empty))

  def write[S](s: S): Writer[Unit, S] = Writer(((), List(s)))
}

object Test extends App {
  val v: Writer[Int, String] = for {
    _ <- Writer.write("First")
    x <- Writer.pure(1)
    _ <- Writer.write(x.toString)
  } yield x + 1

  println(v.value)

  // (2, List("First", "1"))
}
