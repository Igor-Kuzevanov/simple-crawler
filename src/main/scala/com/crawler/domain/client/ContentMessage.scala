package com.crawler.domain.client

import io.circe.Encoder
import io.circe.generic.semiauto.deriveEncoder

final case class AnswerClient(
    content: List[CrawlerContent]
)

object AnswerClient {
  implicit val answerClientE: Encoder[AnswerClient] = deriveEncoder[AnswerClient]
}

final case class CrawlerContent(
    url: String,
    title: Option[String],
    error: String = "-"
)

object CrawlerContent {
  implicit val clientMessageE: Encoder[CrawlerContent] = deriveEncoder[CrawlerContent]
}
