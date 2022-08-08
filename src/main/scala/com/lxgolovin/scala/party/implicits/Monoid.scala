package com.lxgolovin.scala.party.implicits

trait Monoid[T] {
  def append(t1: T, t2: T): T
}

object Monoid {

  implicit val monoid: Monoid[Dependency] = (t1: Dependency, t2: Dependency) => Dependency(t1.name + t2.name)

}
