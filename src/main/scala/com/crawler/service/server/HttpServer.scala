package com.crawler.service.server

import cats.effect.Resource
import cats.effect.kernel.Async
import com.comcast.ip4s._
import com.crawler.domain.config.HttpServerConfig
import org.http4s.HttpApp
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.server.Server
import org.typelevel.log4cats.LoggerFactory
import org.typelevel.log4cats.slf4j.Slf4jFactory

object HttpServer {
  def create[F[_]: Async](config: HttpServerConfig)(app: HttpApp[F]): Resource[F, Server] = {
    implicit val loggerFactory: LoggerFactory[F] = Slf4jFactory.create[F]

    EmberServerBuilder
      .default[F]
      .withHost(Host.fromString(config.host).get)
      .withPort(Port.fromInt(config.port).get)
      .withHttpApp(app)
      .build
  }

}
