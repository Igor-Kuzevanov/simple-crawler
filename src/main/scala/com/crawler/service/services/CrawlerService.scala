package com.crawler.service.services

import cats.effect.kernel.Async
import cats.syntax.all._
import com.crawler.domain.client.{AnswerClient, CrawlerContent}
import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.model._
import org.typelevel.log4cats.Logger
import org.typelevel.log4cats.slf4j.Slf4jLogger

import scala.util.{Failure, Success, Try}

final class CrawlerService[F[_]: Async](log: Logger[F]) {
  private val browser = JsoupBrowser()

  private def getTitle(document: Either[String, Document]): Either[String, String] = document.map { doc =>
    val titleElement = doc >> element("title")
    titleElement >> allText
  }

  def parseTitleFromUrl(urls: List[String]): AnswerClient = {
    log.info("Parsing")
    val resultCrawler = urls.map { url =>
      val responseE = Try(browser.get(url)) match {
        case Success(value)     => Right(value)
        case Failure(exception) => Left(exception.getMessage)
      }
      val titleE    = getTitle(responseE)
      titleE match {
        case Right(value) => CrawlerContent(url, title = Some(value))
        case Left(ex)      => CrawlerContent(url, title = None, error = ex)
      }
    }

    AnswerClient(resultCrawler)
  }
}

object CrawlerService {
  def make[F[_]: Async]: F[CrawlerService[F]] = for {
    log <- Slf4jLogger.fromName[F]("CrawlerService")
  } yield new CrawlerService[F](log)
}
