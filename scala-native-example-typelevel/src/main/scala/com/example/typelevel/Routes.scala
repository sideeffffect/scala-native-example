package com.example.typelevel

import cats.effect.*
import cats.implicits.*
import org.http4s
import org.http4s.*
import org.http4s.client.Client
import org.http4s.dsl.request.*
import org.http4s.implicits.uri

class Routes(business: Business) {
  def routes: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root =>
      Response[IO](Status.Ok).withEntity(business.getGreeting).pure[IO]
    case GET -> Root / "hello" / person =>
      Response[IO](Status.Ok).withEntity(business.getHello(person)).pure[IO]
    case GET -> Root / "joke" =>
      business.getJoke.map(_.joke).map(Response(Status.Ok).withEntity(_))
  }
}
