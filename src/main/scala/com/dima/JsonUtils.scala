package com.dima

import org.json4s.jackson.JsonMethods.parse

object JsonUtils {
  
  def jsonToMap(json: String): Map[String, Any] = {
    parse(json).values.asInstanceOf[Map[String, Any]]
  }

  def flatten(json: Map[String, Any], prefix: String = ""): Map[String, Any] = {
    val flatMap = json.map(i => i._2 match {
      case x: Map[String, Any] => x.map(j => flatten(x, s"$prefix${i._1}_and_"))
      case x: List[Any] => x.zipWithIndex.map(j => Map(s"${prefix}_${i._1}_${j._2}" -> j._1))
      case _ => List(Map(prefix + i -> i._2))
    })

    flatMap.flatten.flatMap(i => i.toList).groupBy(i => i._1)
  }

  def unescape(message: String): String = {
    val unescaped = StringContext.treatEscapes(message)
    if(unescaped(0) == '"') unescaped.substring(1, unescaped.length - 2) else unescaped
  }
}
