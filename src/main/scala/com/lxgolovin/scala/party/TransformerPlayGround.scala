package com.lxgolovin.scala.party

object TransformerPlayGround extends App {}

case class EitherT[F[_], E, A](value: F[Either[E, A]]) {
  def map[B](f: A => B)(implicit F: Functor[F]): EitherT[F, E, B] = EitherT(F.map(value)(x => x.map(f)))

  def flatMap[B](f: A => EitherT[F, E, B])(implicit F: Monad[F]): EitherT[F, E, B] = {
//    val value1 = F.flatMap(value) { x => x.flatMap(y => EitherT(f(y).value)) }

    EitherT(F.flatMap(value) {
      case Right(a) => f(a).value
      //case Left(e) => Left(e).value // ????
    })
  }
}

trait Functor[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]
}

object Functor {
  implicit val optionFunctor: Functor[Option] = new Functor[Option] {
    override def map[A, B](fa: Option[A])(f: A => B): Option[B] = fa.map(f)
  }
}

trait Monad[F[_]] extends Functor[F] {
  def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B]
}

object Monad {
  implicit val optionMonad: Monad[Option] = new Monad[Option] {

    override def flatMap[A, B](fa: Option[A])(f: A => Option[B]): Option[B] = fa.flatMap(f)

    override def map[A, B](fa: Option[A])(f: A => B): Option[B] = fa.map(f)
  }
}
