package bbc.dojonorth.mapreduce

import bbc.dojonorth.lyrics.{LyricData, LyricDataLoader, SongWords}

class Task1AverageDifferentWords {

  // should return number of distinct words in a song
  def mapFun(song: SongWords): Int = {
    song.words.size
  }

  def reduceFun(firstCount: Int, secondCount: Int): Int = {
    // hint: can we compute an average without having processed all the input?
    firstCount + secondCount
  }
}

class Task1AverageTotalWords {

  // should return total number of words in a song
  def mapFun(song: SongWords): Int = {
    song.words.values.reduce(_ + _)
  }

  def reduceFun(firstCount: Int, secondCount: Int): Int = {
    // hint: can we compute an average without having processed all the input?
    firstCount + secondCount
  }
}

/**
 * Example app to use above classes for computing word statistics
 *
 * To run from console:
 * sbt "runMain bbc.dojonorth.mapreduce.WordStats path/to/mxm_dataset_train.txt"
 */
object WordStats extends App {
  val lyricDataLoader = new LyricDataLoader
  val differentWords = new Task1AverageDifferentWords
  val totalWords = new Task1AverageTotalWords

  def getAverageDifferentWords(songsData: LyricData) = {
    val theDifferentWords = songsData.songs
      .map(differentWords.mapFun)
      .reduceLeft(differentWords.reduceFun)

    ???
  }

  def getAverageTotalWords(songsData: LyricData) = {
    val allTheWords = songsData.songs
      .map(totalWords.mapFun)
      .reduceLeft(totalWords.reduceFun)

    ???
  }

  if (args.length != 1) {
    println("Usage: WordStatistics <path to an MXM file>")
  }
  else {
    val songsData1 = lyricDataLoader.load(args(0))
    val diffWords = getAverageDifferentWords(songsData1)
    println("Average number of different words per song: " + diffWords)

    val songsData2 = lyricDataLoader.load(args(0))
    val totalWords = getAverageTotalWords(songsData2)
    println("Average number of words per song: " + totalWords)
  }
}