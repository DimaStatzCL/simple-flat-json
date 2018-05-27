package com.dima

import org.scalatest._

class TestJsonUtils extends Matchers with WordSpecLike {
  "Json utils logic" must {
    "flatten json" in {
      val jsonMap = Map(
        "fieldA" -> 5,
        "fieldB" -> List(1,2),
        "fieldC" -> Map(
          "nested_c_a" -> 10,
          "nested_c_b" -> List(11, 12))
      )

      val map = JsonUtils.flatten(jsonMap)
      map.keys.toList.length should be (6)
      map.contains("field_1") should be (true)
    }

  }
}