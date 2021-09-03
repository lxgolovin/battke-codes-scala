package com.lxgolovin.scala.party.types

case class Color(r: Int, g: Int, b: Int, d: Double) extends Ordering[Color] {
  override def compare(that: Color): Int = that match {
    case Color(r, g, b, _) => (this.r + this.b + this.g) - (r + b + g)
  }
}

object Color {

  implicit val addableColor: MonoidLike[Color] = new MonoidLike[Color] {
    override def add(a: Color, aa: Color): Color =
      Color(Math.max(a.r, aa.r), Math.max(a.g, aa.g), Math.max(a.b, aa.b), Math.max(a.d, aa.d))

    override def empty: Color = Color(0, 0, 0, 0)
  }

  implicit val moreColor: More[Color] = (a: Color, aa: Color) => (a.r + a.g + a.b) - (aa.r + aa.g + aa.b)
}
