package com.lxgolovin.scala.party

import java.time.{Instant, ZoneId, ZoneOffset}
import java.time.format.DateTimeFormatter

case class LocalDate(value: Long)

object JustTest extends App {
  def localDateLongToString(ld: LocalDate): String = {
//    DateTimeFormatter.ISO_LOCAL_DATE.format(Instant.ofEpochSecond(ld.value))
    DateTimeFormatter.ISO_LOCAL_DATE
      .withZone(ZoneId.from(ZoneOffset.UTC))
      .format(Instant.ofEpochSecond(ld.value))

  }

  val l1 = localDateLongToString(LocalDate(0))
  println(l1)
}
