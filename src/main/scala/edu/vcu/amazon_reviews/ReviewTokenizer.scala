package edu.vcu.amazon_reviews

import org.apache.spark.rdd._

object ReviewTokenizer {
  def doSimpleParse(reviews: RDD[String], delimiter: String = "\\t"): RDD[(Array[String], Int)] = {
    val reviewSentiments = reviews.map(_.split(delimiter))
    reviewSentiments.map({r => (r(0).split(" ").map(_.toLowerCase()), r(1).toInt)})
  }
}
