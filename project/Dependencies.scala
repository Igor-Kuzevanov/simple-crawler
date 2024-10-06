import sbt.*

object Dependencies {

  object Versions {
    val scala = "2.13.14"

    val catsEffect = "3.5.4"
    val fs2        = "3.10.2"
    val scraper    = "3.1.1"
    val circe      = "0.14.9"
    val http4s     = "1.0.0-M42"
    val slf4j      = "2.0.13"
    val log4j      = "2.19.0"
    val log4cats   = "2.7.0"
    val pureconfig = "0.17.7"
  }

  object Runtime {
    val catsEffect: ModuleID   = "org.typelevel"    %% "cats-effect"   % Versions.catsEffect
    val fs2: ModuleID          = "co.fs2"           %% "fs2-core"      % Versions.fs2
    val scalaScraper: ModuleID = "net.ruippeixotog" %% "scala-scraper" % Versions.scraper

    val all: Seq[ModuleID] = Seq(catsEffect, fs2, scalaScraper)
  }

  object Configuration {
    val pureConfig: ModuleID = "com.github.pureconfig" %% "pureconfig" % Versions.pureconfig

    val all: Seq[ModuleID] = Seq(pureConfig)
  }

  object Http {
    val http4sDls         = "org.http4s" %% "http4s-dsl"          % Versions.http4s
    val http4sEmberServer = "org.http4s" %% "http4s-ember-server" % Versions.http4s
    val http4sEmberClient = "org.http4s" %% "http4s-ember-client" % Versions.http4s

    val all: Seq[ModuleID] = Seq(http4sDls, http4sEmberServer, http4sEmberClient)
  }

  object Json {
    val circeCore: ModuleID    = "io.circe" %% "circe-core"    % Versions.circe
    val circeParser: ModuleID  = "io.circe" %% "circe-parser"  % Versions.circe
    val circeGeneric: ModuleID = "io.circe" %% "circe-generic" % Versions.circe

    val all: Seq[ModuleID] = Seq(circeCore, circeParser, circeGeneric)
  }

  object Logging {
    val slf4jSimple: ModuleID   = "org.slf4j"      % "slf4j-simple"   % Versions.slf4j
    val slf4jApi: ModuleID      = "org.slf4j"      % "slf4j-api"      % Versions.slf4j
    val log4catsCore: ModuleID  = "org.typelevel" %% "log4cats-core"  % Versions.log4cats
    val log4catsSlf4j: ModuleID = "org.typelevel" %% "log4cats-slf4j" % Versions.log4cats

    val all: Seq[ModuleID] = Seq(slf4jSimple, slf4jApi, log4catsCore, log4catsSlf4j)
  }

  object Modules {
    val All: Seq[ModuleID] =
      Runtime.all ++
        Configuration.all ++
        Http.all ++
        Json.all ++
        Logging.all
  }

}
