ThisBuild / version      := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := Dependencies.Versions.scala

lazy val `simple-crawler` =
  (project in file("."))
    .settings(
      name := "SimpleCrawler",
      libraryDependencies ++= Dependencies.Modules.All
    )
