package com.lxgolovin.battlecodes.list

sealed trait EasyList[+A] {
  self =>

  def head: A
  def tail: EasyList[A]

  def first: Option[A] = {
    self match {
      case Empty => None
      case Cons(head, _)=> Some(head)
    }
  }

  def map[B](f: A => B): EasyList[B] = {
    self match {
      case Empty => Empty
      case Cons(h, t) => Cons(f(h), t.map(f))
    }
  }

  def flatMap[B](f: A => EasyList[B]): EasyList[B] = {
    self match {
      case Empty => Empty
      case Cons(h, t) => f(h) ++ t.flatMap(f)
    }
  }

//  def flatMapUsingFoldLeft[B](f: A => EasyList[B]): EasyList[B] = {
//    self.foldLeft(Cons[B]())(_ ++ f(_))
//  }

  def ++[AA >: A](otherList: EasyList[AA]) : EasyList[AA] = {
    self match {
      case Empty => otherList
      case Cons(head, tail) => Cons(head, tail ++ otherList)
    }
  }

  def foldLeft[B](x: B)(op: (B, A) => B): B = {
    self match {
      case Empty => x
      case Cons(h, t) => t.foldLeft(op(x, h))(op)
    }
  }

  def foldRightUsingFoldLeft[B](x: B)(op: (A, B) => B): B = {
    val result = foldLeft(identity: B => B)((acc: B => B, a: A) => (b: B) => acc(op(a,b)))
    result(x)
  }

  def foldRightUsingFoldLeftFreak[B](x: B)(op: (A, B) => B): B = {
    foldLeft[B => B](identity)((acc, a) => b => acc(op(a,b)))(x)
  }

  def foldLeftUsingFoldRight[B](x: B)(op: (B, A) => B): B = {
    val result = foldRight(identity: B => B)((a: A, acc: B => B) => (b: B) => acc(op(b, a)))
    result(x)
  }

  def foldLeftUsingFoldRightFreak[B](x: B)(op: (B, A) => B): B = {
    foldRight[B => B](identity)((a, acc) => b => acc(op(b, a)))(x)
  }

  def foldRight[B](x: B)(op: (A, B) => B): B = {
    self match {
      case Empty => x
      case Cons(h, t) => op(h, t.foldRight(x)(op))
    }
  }
}

case class Cons[+A](head: A, tail: EasyList[A]) extends EasyList[A]

object Cons {
  def apply[A](items: A*): EasyList[A] = {
    if (items.isEmpty) {
      Empty
    } else {
      Cons(items.head, apply(items.tail: _*))
    }
  }
}

object Empty extends EasyList[Nothing] {
  override def head = throw new NoSuchElementException("No head in the empty list")
  override def tail = throw new NoSuchElementException("No tail in the empty list")
}

