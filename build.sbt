name := "sandbox-scala"

version := "0.1"

scalaVersion := "2.13.6"

libraryDependencies += "org.projectlombok" % "lombok"       % "1.18.12" % "provided"
libraryDependencies += "org.scalactic"     %% "scalactic"   % "3.2.9"
libraryDependencies += "org.scalatest"     %% "scalatest"   % "3.2.9" % "test"
libraryDependencies += "org.typelevel"     %% "cats-core"   % "2.3.0"
libraryDependencies += "org.typelevel"     %% "cats-effect" % "3.3.4"
//libraryDependencies ++= Seq("org.typelevel" %% "cats-effect-kernel" % "3.3.4",
//                            "org.typelevel" %% "cats-effect-laws"   % "3.3.4" % Test)

val circeVersion = "0.14.1"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)

Compile / PB.targets := Seq(
  scalapb.gen() -> (Compile / sourceManaged).value / "scalapb"
)
