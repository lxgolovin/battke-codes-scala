package com.lxgolovin.scala.party.types

trait Ordering[T] {
  def compare(that: T): Int

  def <(that: T): Boolean  = (this compare that) < 0
  def <=(that: T): Boolean = (this compare that) <= 0
}
