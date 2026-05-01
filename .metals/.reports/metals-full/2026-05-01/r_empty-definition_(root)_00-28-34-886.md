error id: file:///C:/Users/ugoet/ugo-dev/amazon_reviews/src/main/scala/edu/vcu/amazon_reviews/WordCount.scala:org/apache/spark/rdd/RDD#flatMap().
file:///C:/Users/ugoet/ugo-dev/amazon_reviews/src/main/scala/edu/vcu/amazon_reviews/WordCount.scala
empty definition using pc, found symbol in pc: 
found definition using semanticdb; symbol org/apache/spark/rdd/RDD#flatMap().
empty definition using fallback
non-local guesses:

offset: 1332
uri: file:///C:/Users/ugoet/ugo-dev/amazon_reviews/src/main/scala/edu/vcu/amazon_reviews/WordCount.scala
text:
```scala
package edu.vcu.amazon_reviews

/**
 * Everyone's favourite wordcount example.
 */

import org.apache.spark.rdd._

object WordCount {
  /**
   * A slightly more complex than normal wordcount example with optional
   * separators and stopWords. Splits on the provided separators, removes
   * the stopwords, and converts everything to lower case.
   */
  def withStopWordsFiltered(rdd : RDD[String],
    separators : Array[Char] = " ".toCharArray,
    stopWords : Set[String] = Set("the")): RDD[(String, Int)] = {

    val tokens: RDD[String] = rdd.flatMap(_.split(separators).
      map(_.trim.toLowerCase))
    val lcStopWords = stopWords.map(_.trim.toLowerCase)
    val words = tokens.filter(token =>
      !lcStopWords.contains(token) && (token.length > 0))
    val wordPairs = words.map((_, 1))
    val wordCounts = wordPairs.reduceByKey(_ + _)
    wordCounts
  }

  def bySentimentTopN(reviews: RDD[(Array[String], Int)] , by: Int, top_n: Int = 20): Array[(String, Int)] = {
    // if by is < 0, then we want to get the most frequent tokens regardless of sentiment

    if (by >= 0) {
      val reviewsBySenti = reviews.filter({case (tokens, senti) => 
        senti == by })
      val reviewOnly = reviewsBySenti.map({case (tokens, senti) => tokens})
      val tokenOnes = reviewOnly.flatMap@@(x => x).map((_,1))
      tokenOnes.reduceByKey(_+_).sortBy(pair => pair._2, false).take(top_n)
    }
    else {
      val reviewOnly = reviews.map(tuple => tuple._1)
      val tokenOnes = reviewOnly.flatMap(x => x).map((_,1))
      tokenOnes.reduceByKey(_+_).sortBy({pair => pair._2}, false).take(top_n)
    }
    
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 