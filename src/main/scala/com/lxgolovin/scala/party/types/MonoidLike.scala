package com.lxgolovin.scala.party.types

trait MonoidLike[A] {

  def add(a: A, aa: A): A

  def empty: A
}

object MonoidLike {

  val x = 1

  implicit val addableToInt: MonoidLike[Int] = new MonoidLike[Int] {

    override def add(a: Int, aa: Int): Int = a + aa

    override val empty: Int = 0
  }

  implicit val addableToString: MonoidLike[String] = new MonoidLike[String] {

    override def add(a: String, aa: String): String = a ++ aa

    override def empty: String = ""
  }

  implicit def addableToLists[A]: MonoidLike[List[A]] = new MonoidLike[List[A]] {

    override def add(a: List[A], aa: List[A]): List[A] = a ++ aa

    override def empty: List[A] = List.empty
  }

  implicit class MonoidOps[A](a: A)(implicit monoid: MonoidLike[A]) {
    def |+|(b: A): A = monoid.add(a, b)
  }
}
