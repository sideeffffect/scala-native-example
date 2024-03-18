package com.example.app

import com.example.library.Adder

object Main:
  def main(args: Array[String]): Unit =
    val adder = new Adder(42)
    val result = adder.add(13)
    println(s"Hello world! $result")
