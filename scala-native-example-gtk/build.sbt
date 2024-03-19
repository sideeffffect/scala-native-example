ThisBuild / version := "0.1.0"

ThisBuild / scalaVersion := "3.3.3"

lazy val root = project
  .in(file("."))
  .settings(
    organization := "com.example",
    name := "scala-native-example-gtk",
    libraryDependencies ++= List(
      "com.example" %%% "scala-native-example-library" % "0.1.0",
      "com.indoorvivants.gnome" %%% "gtk4" % "0.0.5"
    ),
    nativeCompileOptions ++= {
      pkgConfig("gtk4", "cflags")
    },
    nativeLinkingOptions ++= {
      pkgConfig("gtk4", "libs")
    }
  )
  .enablePlugins(ScalaNativePlugin)

def pkgConfig(pkg: String, arg: String): List[String] = {
  import sys.process.*
  s"pkg-config --$arg $pkg".!!.trim.split(" ").toList
}

// `sbt run` or `target/scala-3.3.3/scala-native-example-gtk-out`
