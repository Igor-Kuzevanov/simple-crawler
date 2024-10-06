package com.crawler

import cats.effect.{IO, IOApp}
import com.crawler.domain.config.AppConfig
import com.crawler.endpoints.CrawlerEndpoint
import com.crawler.service.server.HttpServer
import com.crawler.service.services.CrawlerService
import org.typelevel.log4cats.slf4j.Slf4jLogger

object Application extends IOApp.Simple {

  override def run: IO[Unit] = {
    for {
      log     <- Slf4jLogger.fromName[IO]("Application").toResource
      _       <- log.info("Application starting...").toResource
      config  <- AppConfig.load[IO].toResource
      crawler <- CrawlerService.make[IO].toResource
      httpApp  = CrawlerEndpoint.make[IO](crawler)
      _       <- HttpServer.create[IO](config.server)(httpApp)
    } yield ()
  }.useForever
}
