package com.example.typelevel

import cats.effect.IO
import cats.effect.kernel.Resource
import cats.implicits.*
import epollcat.EpollApp
import fs2.io.net.Network
import fs2.io.net.tls.{S2nConfig, TLSContext}
import org.http4s.ember.client.EmberClientBuilder
import org.http4s.ember.server.EmberServerBuilder

object Main extends EpollApp.Simple {

  override def run: IO[Unit] =
    resource.useForever

  private lazy val resource: Resource[IO, Unit] = for {
    tlsContext <- S2nConfig.builder
      .withCipherPreferences("default_tls13")
      .build[IO]
      .map(Network[IO].tlsContext.fromS2nConfig(_))
    client <- EmberClientBuilder
      .default[IO]
      .withTLSContext(tlsContext)
      .withHttp2
      .build
    business = Business(client)
    routes = Routes(business)
    server <- EmberServerBuilder
      .default[IO]
      .withHttp2
      .withHttpApp(routes.routes.orNotFound)
      .build
    _ <- IO
      .println(show"baseUri: ${server.baseUri}, isSecure: ${server.isSecure}")
      .toResource
  } yield ()

}