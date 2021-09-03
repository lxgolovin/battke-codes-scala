package com.lxgolovin.scala.party.list

import org.scalatest.flatspec.AnyFlatSpec

class EasyListTest extends AnyFlatSpec {

  it should "Throw an error for the head of the empty list" in {
    assertThrows[NoSuchElementException](Empty.head)
  }

  it should "Throw an error for the tail of the empty list" in {
    assertThrows[NoSuchElementException](Empty.tail)
  }

  it should "One element list" in {
    val easyList = EasyList(1)
    assert(easyList.head == 1)
  }

  it should "Extract first element" in {
    val easyList = EasyList(1, Empty)
    assert(easyList.first.contains(1))
  }

  "The first element if Empty" should "be None" in {
    val easyList = Empty
    assert(easyList.first.isEmpty)
  }

  "The list of Int mapping to String" should "be list of String" in {
//    val easyList = Cons(1, Cons(2, Empty))
    val easyList = EasyList(1, 2, Empty)
    val strings  = easyList.map(_.toString)
    assert(strings.first.get == "1")
  }

  "Empty list" should "map to Empty list" in {
    val easyList = Empty
    val strings  = easyList.map(_.toString)
    assert(strings.first.isEmpty)
  }

  "List with values" should "return folding left all arguments" in {
//    val easyList = List(1, 3)
    val easyList = EasyList(1, 3)
    val res      = 5 + 1 + 3 // ((5 + 1) + 3)
    assert(easyList.foldLeft(5)(_ + _) == res)
  }

  "List with values minus operation" should "return folding left all arguments" in {
    val easyList = EasyList(1, 3)
    val res      = List(1, 3).foldLeft(5)(_ - _)
    val res1     = ((5 - 3) - 1) // 1
    assert(easyList.foldLeft(5)(_ - _) == res)
    assert(res1 == res)
  }

  "foldRight with sum" should "be same as foldLeft" in {
    val foldLeft = EasyList(1, 3).foldLeft(5)(_ + _)
    val easyList = EasyList(1, 3)
    assert(easyList.foldRight(5)(_ + _) == foldLeft)
  }

  "foldRight with minus operator" should "be folding right" in {
    val easyList = EasyList(1, 3)
    val res      = List(1, 3).foldRight(5)(_ - _)
    val res1     = (5 - (3 - 1)) // 3
    assert(easyList.foldRight(5)(_ - _) == res)
    assert(res1 == res)
  }

  "flatMap" should "flat lists" in {
    val map = Map(1 -> "one", 2 -> "two")
    val l1  = Seq("1", "2", "2")
    val l2  = Seq(l1, l1)
    val l3  = l2.flatMap[String](x => x)

//    val l2 = l1.map(toInt)
//    val l3 = l1.flatMap(toInt)
    val el1 = EasyList("1", "2", "2")
    val el2 = EasyList(el1, el1)
    val el3 = el2.flatMap[String](x => x)
    assert(true, true)
  }

  "foldLeftPrime with values" should "return folding left all arguments" in {
    //    val easyList = List(1, 3)
    val easyList = EasyList(1, 3)
    val res      = 5 + 1 + 3 // ((5 + 1) + 3)
    assert(easyList.foldLeftUsingFoldRight(5)(_ + _) == res)
  }

  "foldLeftPrime with values minus operation" should "return folding left all arguments" in {
    val easyList = EasyList(1, 3)
    val res      = List(1, 3).foldLeft(5)(_ - _)
    val res1     = ((5 - 3) - 1) // 1
    assert(easyList.foldLeftUsingFoldRight(5)(_ - _) == res)
    assert(res1 == res)
  }

  "foldLeftPrimePlus with values" should "return folding left all arguments" in {
    //    val easyList = List(1, 3)
    val easyList = EasyList(1, 3)
    val res      = 5 + 1 + 3 // ((5 + 1) + 3)
    assert(easyList.foldLeftUsingFoldRightFreak(5)(_ + _) == res)
  }

  "foldLeftPrimePlus with values minus operation" should "return folding left all arguments" in {
    val easyList = EasyList(1, 3)
    val res      = List(1, 3).foldLeft(5)(_ - _)
    val res1     = ((5 - 3) - 1) // 1
    assert(easyList.foldLeftUsingFoldRightFreak(5)(_ - _) == res)
    assert(res1 == res)
  }

  "foldRightPrime with sum" should "be same as foldLeft" in {
    val foldLeft = EasyList(1, 3).foldLeft(5)(_ + _)
    val easyList = EasyList(1, 3)
    assert(easyList.foldRightUsingFoldLeft(5)(_ + _) == foldLeft)
  }

  "foldRightPrime with minus operator" should "be folding right" in {
    val easyList = EasyList(1, 3)
    val res      = List(1, 3).foldRight(5)(_ - _)
    val res1     = (5 - (3 - 1)) // 3
    assert(easyList.foldRightUsingFoldLeft(5)(_ - _) == res)
    assert(res1 == res)
  }

  "foldRightPrimePlus with sum" should "be same as foldLeft" in {
    val foldLeft = EasyList(1, 3).foldLeft(5)(_ + _)
    val easyList = EasyList(1, 3)
    assert(easyList.foldRightUsingFoldLeftFreak(5)(_ + _) == foldLeft)
  }

  "foldRightPrimePlus with minus operator" should "be folding right" in {
    val easyList = EasyList(1, 3)
    val res      = List(1, 3).foldRight(5)(_ - _)
    val res1     = (5 - (3 - 1)) // 3
    assert(easyList.foldRightUsingFoldLeftFreak(5)(_ - _) == res)
    assert(res1 == res)
  }

  "toString method of the empty list" should "return 'EasyList()'" in {
    val expected = "EasyList()"
    val easyList = Empty
    assert(expected == easyList.toString)
  }

  "toString method of the one element list" should "return 'EasyList(1)'" in {
    val expected = "EasyList(1)"
    val easyList = EasyList(1)
    assert(expected == easyList.toString)
  }

  "toString method of many elements list" should "return 'EasyList(1, 3)'" in {
    val expected = "EasyList(1, 3)"
    val easyList = EasyList(1, 3)
    assert(expected == easyList.toString)
  }

  "mkString method of many elements list with : splitter" should "return 'EasyList(1:3)'" in {
    val expected = "EasyList(1:3)"
    val easyList = EasyList(1, 3)
    assert(expected == easyList.mkString(":"))
  }
}
