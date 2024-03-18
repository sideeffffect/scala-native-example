ThisBuild / version := "0.1.0"

ThisBuild / scalaVersion := "3.3.3"

lazy val root = crossProject(JVMPlatform, NativePlatform)
  .in(file("."))
  .settings(
    organization := "com.example",
    name := "scala-native-example-library"
  )

// sbt publishLocal
