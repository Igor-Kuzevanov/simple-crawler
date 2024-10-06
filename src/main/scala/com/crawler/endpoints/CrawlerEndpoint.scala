package com.crawler.endpoints

import cats.effect.kernel.Async
import com.crawler.service.services.CrawlerService
import io.circe.syntax.EncoderOps
import org.http4s._
import org.http4s.dsl.Http4sDsl
import org.http4s.implicits._

private final class CrawlerEndpoint[F[_]: Async](crawler: CrawlerService[F]) {
  private val dsl = Http4sDsl[F]
  import dsl._

  implicit val listUrlQueryParamDecoder: QueryParamDecoder[List[String]] =
    QueryParamDecoder[String].map(str => str.split(",").toList)

  private object UrlQueryParamMatcher extends QueryParamDecoderMatcher[List[String]]("urls")

  private val titleByUrls = HttpRoutes.of[F] { case GET -> Root / "crawler" :? UrlQueryParamMatcher(urls) =>
    Ok(crawler.parseTitleFromUrl(urls).asJson.noSpaces)

  }

  def allRoutes: HttpRoutes[F] = titleByUrls

  def app: HttpApp[F] = allRoutes.orNotFound
}

object CrawlerEndpoint {
  def make[F[_]: Async](crawler: CrawlerService[F]): HttpApp[F] = new CrawlerEndpoint(crawler).app
}
