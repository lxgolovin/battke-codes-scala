package com.lxgolovin.scala.party.state

class StateExample {

  def funcOldSchool(list: Array[Int]): Int = {
    var sum = 0
    for (i <- list) {
      sum = sum + i
    }
    sum
  }

  def funcRec(list: Array[Int]): (Int, Int) = {
    def loop(index: Int, sum: Int): (Int, Int) = {
      list match {
        case l if l.length <= 1 => (sum, index)
        case l                  => loop(index + 1, sum + l(index))
      }
    }

    loop(0, 0)
  }

}
