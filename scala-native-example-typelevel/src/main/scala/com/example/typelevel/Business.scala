package com.example.typelevel

import cats.effect.IO
import com.example.typelevel.Business.*
import io.circe.*
import org.http4s.*
import org.http4s.circe.CirceEntityDecoder.*
import org.http4s.client.Client
import org.http4s.implicits.uri

class Business(client: Client[IO]):

  lazy val getGreeting: String =
    "Hey There!"

  def getHello(person: String): String =
    s"Hello, $person"

  def getJoke: IO[Joke] =
    client.expect[Joke](Request(Method.GET, uri"https://icanhazdadjoke.com/"))

object Business:
  case class Joke(joke: String) derives Decoder
