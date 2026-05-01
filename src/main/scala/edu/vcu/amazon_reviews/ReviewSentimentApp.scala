package edu.vcu.amazon_reviews

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd._

object ReviewSentimentApp extends App {
    val conf = new SparkConf().setMaster("local")
    val sc = new SparkContext(conf)
    // amazon review data
    val reviewsFile = sc.textFile(args(0))
    // nltk stopwords 
    val stopsFile = sc.textFile(args(1))
    // 
    val stopWordList = stopsFile.collect()
    // do some cleanup and split data by tab delimiter
    val cleanReviews = ReviewTokenizer.doSimpleParse(reviewsFile)
    // remove stopwords
    val reviewsNoStops: RDD[(Array[String], Int)] = cleanReviews.map({case (r, s) => 
        (StopwordRemoval.removeStopwords(r, stopWordList), s)})
    // flexible method to answer question 3 - try this strategy for questions 1 & 2
    val topN = WordCount.bySentimentTopN(reviewsNoStops, args(4).toInt, 20)
    sc.parallelize(topN).saveAsTextFile(args(2))
}
