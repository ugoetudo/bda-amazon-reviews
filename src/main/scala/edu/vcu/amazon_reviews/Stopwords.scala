package edu.vcu.amazon_reviews

import org.apache.spark.rdd._

object StopwordRemoval {
    def removeStopwords(tokens: Array[String], stopWords: Array[String]): Array[String] = {
        val noStops = tokens.map(x => if (!(stopWords.contains(x))) x else "")
        noStops.filter(x => x != "")
    }
}
