package com.lxgolovin.scala.party.implicits

import scala.concurrent.{ExecutionContext, Future}

object ImplicitsSandbox extends App {

  val httpCall: Future[String] = Future.successful("Some string")

  def func(
    s: String
  )(implicit executionContext: ExecutionContext, dep: Dependency, m: Monoid[Dependency]): Future[Unit] = {
    httpCall
      .map(_ + s)
      .map(_ + m.append(dep, dep).name)
      .map(println)
  }

  implicit val executionContext: ExecutionContext = ExecutionContext.global

  implicit val multiplier: Multiplier = Multiplier(5)

  /*
  Implicits are found
  1) Syntactic context
  2) Companion object of the dependency
  3) Companion object of the type class
  4) In the companion object of the type class instance (inside trait)

  isomorphic means equivalent
  iso - same
  morph - form

  To read:
  https://www.youtube.com/watch?v=IOiZatlZtGU
  Philip Wadler

  Alonzo Church

  https://www.youtube.com/watch?v=2u0sNRO-QKQ
   */
  func("lalala")
}

case class Dependency(name: String)

object Dependency {

  implicit def dependency(implicit m: Multiplier): Dependency = Dependency("x" * m.value)
}

case class Multiplier(value: Int)
