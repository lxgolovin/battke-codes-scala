package com.lxgolovin.scala.party.state

object StateApp extends App {

  type Stack[A] = State[A, List[Int]]

  implicit class StackOps[A](stack: Stack[A]) {
    def andThen[B](other: Stack[B]): Stack[B] =
      stack.flatMap(v => {
        println(s"inside andThen crazy ${v}")
        other
      })
  }

  def pop: Stack[Option[Int]] = {
    State {
      case head :: tail => (Some(head), tail)
      case s @ Nil      => (None, s)
    }
  }

  def push(v: Int): Stack[Unit] = State { s => ((), v :: s) }

  val list = List(1, 3, 5)

  val sm = State[Option[Int], List[Int]] {
    case s @ _ :: _ =>
      println(s"inside run $s ${s.headOption} left ${s.tail}")
      (s.headOption, s.tail)
    case Nil =>
      println(s"inside run nothing to return")
      (None, Nil)
  }
  val sm1 = sm.map(s => {
    println(s"inside map Some value ${s}")
    s.map(v => {
      println(s"inside map increment value $v")
      v + 100
    })
  })
  println(sm1.andThen(sm1).run(list))
  println()

  val program = for {
    x <- pop
    _ = println(s"before s0 popped: $x")
    s0 <- State.local
    _  <- push(10)
    _ = println(s"after s0 $s0")
    s1 <- State.local
    _  <- push(x.getOrElse(0))
    _ = println(s"after getOrElse s1 $s1")
    s2 <- State.local
  } yield (s0, s1, s2)
  val initialState = List(5)
//
  println(program.run(initialState)) // (None, List(0,1))
  println()
  println(
    program
      .andThen(program)
      .andThen(program)
      .andThen(program)
      .run(initialState)
  ) // (None, List(0,1))

  // s0 = LIst()
  // s1 = List(1)
  // s2 = List(5, 1)
  // (List(), List(1), List(5,1), LIst(5,1))

  // (Some(5), List(5))

//  ((List(1),List(1, 1),List(5, 1, 1)),List(5, 1, 1))
}
