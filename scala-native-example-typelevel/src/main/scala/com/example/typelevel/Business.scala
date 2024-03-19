package com.example.typelevel

import cats.effect.IO
import com.example.library.Adder
import com.example.typelevel.Business.*
import io.circe.*
import org.http4s.*
import org.http4s.circe.CirceEntityDecoder.*
import org.http4s.client.Client
import org.http4s.implicits.uri

class Business(client: Client[IO], adder: Adder):

  lazy val getGreeting: String =
    "Hey There!"

  def getHello(person: String): String =
    val num = person.toIntOption
    val greeting = num match
      case Some(num) => adder.add(num).toString
      case None      => person
    s"Hello, $person"

  def getJoke: IO[Joke] =
    client.expect[Joke](Request(Method.GET, uri"https://icanhazdadjoke.com/"))

object Business:
  case class Joke(joke: String) derives Decoder
