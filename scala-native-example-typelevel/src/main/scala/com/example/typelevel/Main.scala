package com.example.typelevel

import cats.effect.IO
import cats.effect.kernel.Resource
import cats.implicits.*
import com.example.library.Adder
import epollcat.EpollApp
import fs2.io.net.Network
import fs2.io.net.tls.{S2nConfig, TLSContext}
import org.http4s.ember.client.EmberClientBuilder
import org.http4s.ember.server.EmberServerBuilder

object Main extends EpollApp.Simple:

  override def run: IO[Unit] =
    resource.useForever

  private lazy val resource: Resource[IO, Unit] = for
    s2nConfig <- S2nConfig.builder
      .withCipherPreferences("default_tls13")
      .build[IO]
    tlsContext = Network[IO].tlsContext.fromS2nConfig(s2nConfig)
    client <- EmberClientBuilder
      .default[IO]
      .withTLSContext(tlsContext)
      .withHttp2
      .build
    adder = Adder(42)
    business = Business(client, adder)
    routes = Routes(business)
    server <- EmberServerBuilder
      .default[IO]
      .withHttp2
      .withHttpApp(routes.routes.orNotFound)
      .build
    _ <- IO
      .println(show"baseUri: ${server.baseUri}, isSecure: ${server.isSecure}")
      .toResource
  yield ()
