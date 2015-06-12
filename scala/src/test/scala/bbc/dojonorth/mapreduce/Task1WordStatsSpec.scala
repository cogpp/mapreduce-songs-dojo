package bbc.dojonorth.mapreduce

import bbc.dojonorth.lyrics.SongWords
import org.scalatest.{Matchers, FlatSpec}


class Task1WordStatsSpec extends FlatSpec with Matchers {

  "Map Function for different words" should "take a song and return the number of different words" in {
    val wc = new Task1AverageDifferentWords
    val song = SongWords("tra123", 12345, Map("the" -> 4, "i" -> 5))

    val result = wc.mapFun(song)

    result should be (2)
  }

  "Reduce Function for different words" should "do something with 2 map results" in {
    val wc = new Task1AverageDifferentWords
    val songs  = List(SongWords("tra123", 12345, Map("the" -> 4, "i" -> 5)), SongWords("321art", 54321, Map("the" -> 10, "i" -> 10, "it" -> 10)))


    val results = (songs map wc.mapFun reduce wc.reduceFun) / 2
    
    results should be (5)
  }

  "Map Function for total words" should "take a song and return the total number of words" in {
    val wc = new Task1AverageTotalWords
    val song = SongWords("tra123", 12345, Map("the" -> 4, "i" -> 5))

    val result = wc.mapFun(song)

    result should be (9)
  }

  "Reduce Function for total words" should "do something with 2 map results" in {
    val wc = new Task1AverageTotalWords
    val songs = List(SongWords("tra123", 12345, Map("the" -> 4, "i" -> 5)) ,  SongWords("321art", 54321, Map("the" -> 10, "i" -> 10, "it" ->10)))

    val results = (songs map wc.mapFun reduce wc.reduceFun) / 2

    results should be (19)
  }
}
