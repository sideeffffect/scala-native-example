ThisBuild / version := "0.1.0"

ThisBuild / scalaVersion := "3.3.3"

lazy val root = project
  .in(file("."))
  .settings(
    organization := "com.example",
    name := "scala-native-example-typelevel",
    libraryDependencies ++= List(
      "com.example" %%% "scala-native-example-library" % "0.1.0",
      "com.armanbilge" %%% "epollcat" % "0.1.6",
      "org.http4s" %%% "http4s-ember-client" % "0.23.26",
      "org.http4s" %%% "http4s-ember-server" % "0.23.26",
      "org.http4s" %%% "http4s-dsl" % "0.23.26",
      "org.http4s" %%% "http4s-circe" % "0.23.26"
    ),
    vcpkgDependencies := VcpkgDependencies(
      "s2n"
    )
  )
  .enablePlugins(ScalaNativePlugin)
  .enablePlugins(VcpkgNativePlugin)

// `sbt run` or `target/scala-3.3.3/scala-native-example-typelevel-out`
