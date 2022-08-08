package com.lxgolovin.scala.party.tagless

import cats.Monad
import cats.data.State
import cats.implicits._
import com.lxgolovin.scala.party.tagless.FinancialContract.{FinancialState, Ledger}

trait FinancialContract[F[_]] {

  def tx(sourceId: String, targetId: String, amount: Int): F[Unit]

  def getBalance(id: String): F[Int]
}

case class Account(id: String, balance: Int)

object MyApp extends App {

  val run = program1[FinancialState]

  def program1[F[_]](implicit financials: FinancialContract[F], monad: Monad[F]): F[Unit] = {
    for {
      acc1 <- financials.getBalance("1")
      _ = println(acc1)
      acc2 <- financials.getBalance("2")
      _ = println(acc2)
      tx   <- financials.tx("1", "2", 10)
      acc1 <- financials.getBalance("1")
      _ = println(acc1)
      acc2 <- financials.getBalance("2")
      _ = println(acc2)
    } yield ()
//    financials.tx("1", "2", 20)
  }

  run.run(Ledger(Map("1" -> 100, "2" -> 20))).value
}

// HT: implement with IO monad
// https://typelevel.org/cats-effect/docs/std/ref

// do a state monad implementation with DB for example

object FinancialContract {
  case class Ledger(state: Map[String, Int])

  type FinancialState[A] = State[Ledger, A]

  implicit val financialContract: FinancialContract[FinancialState] = new FinancialContract[FinancialState] {
    override def tx(sourceId: String, targetId: String, amount: Int): FinancialState[Unit] = {
      for {
        ledger <- State.get[Ledger]
        balance1 = ledger.state.get(sourceId)
        balance2 = ledger.state.get(targetId)
        _ <- (balance1, balance2) match {
          case (Some(b1), Some(b2)) if b1 >= amount =>
            State.set(Ledger(ledger.state + (sourceId -> (b1 - amount)) + (targetId -> (b2 + amount))))
          case _ => State.pure[Ledger, Unit](())
        }
      } yield ()
    }

    override def getBalance(id: String): FinancialState[Int] = {
      for {
        ledger <- State.get
      } yield ledger.state.getOrElse(id, 0)

    }
  }
}
