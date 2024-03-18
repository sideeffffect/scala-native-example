ThisBuild / version := "0.1.0"

ThisBuild / scalaVersion := "3.3.3"

lazy val root = project
  .in(file("."))
  .settings(
    organization := "com.example",
    name := "scala-native-example-app",
    libraryDependencies ++= List(
      "com.example" %%% "scala-native-example-library" % "0.1.0"
    )
  )
  .enablePlugins(ScalaNativePlugin)

// `sbt run` or `target/scala-3.3.3/scala-native-example-app-out`
