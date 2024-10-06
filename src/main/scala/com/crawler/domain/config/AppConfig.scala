package com.crawler.domain.config

import cats.effect.kernel.Sync
import pureconfig.generic.auto._
import pureconfig.ConfigSource

case class AppConfig(server: HttpServerConfig)

object AppConfig {
  def load[F[_]: Sync]: F[AppConfig] = Sync[F].delay {
    ConfigSource.defaultApplication.loadOrThrow[AppConfig]
  }
}
