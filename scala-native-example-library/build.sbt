ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.3"

lazy val root = crossProject(JVMPlatform, NativePlatform)
  .in(file("."))
  .settings(
    name := "scala-native-example-library"
  )
