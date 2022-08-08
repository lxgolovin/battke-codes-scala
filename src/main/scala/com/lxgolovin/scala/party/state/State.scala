package com.lxgolovin.scala.party.state

case class State[A, S](run: S => (A, S)) {

  def map[B](f: A => B): State[B, S] = {
    State { s =>
      run(s) match {
        case (a, a1) => (f(a), a1)
      }
    }
  }

  def flatMap[B](f: A => State[B, S]): State[B, S] = {
    State { s =>
      run(s) match {
        case (a, a1) => f(a).run(a1)
      }
    }
  }

  def update(f: S => S): State[A, S] = {
    State { s =>
      run(s) match {
        case (a, s1) => (a, f(s1))
      }
    }
  }

}

object State {

  def local[S]: State[S, S] = State { s => (s, s) }
}
