package com.example.app

import com.example.library.Adder

class TestSuite extends munit.FunSuite:
  test("hello"):
    val adder = new Adder(1)
    val result = adder.add(2)
    assertEquals(result, 42)
