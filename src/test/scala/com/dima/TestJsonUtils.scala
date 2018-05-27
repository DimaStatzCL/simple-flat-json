package com.dima

import org.scalatest._

class TestJsonUtils extends Matchers with WordSpecLike {
  "Json utils logic" must {
    "flatten json" in {
      val jsonMap = Map(
        "field_1" -> 5,
        "field_2" -> List(1,2),
        "field_3" -> Map(
          "nested_1" -> 10,
          "nested_2" -> List(11, 12))
      )

      val map = JsonUtils.flatten(jsonMap)
      map.keys.toList.length should be (6)
      map.contains("field_1") should be (true)
    }

  }
}