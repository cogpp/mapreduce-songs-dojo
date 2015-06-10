# mapreduce-songs-dojo
This Dojo is based on the concept of MapReduce, a programming model for processing large datasets.
Google is often credited with having invented the method, and holds a patent.
The distributed implementation was heavily used for many years inside the company.
See for example: http://www.baselinemag.com/c/a/Infrastructure/How-Google-Works-1/5

The original paper introducing MapReduce is available online:
http://research.google.com/archive/mapreduce.html


## Warmup exercise

This exercise is an introduction to the general idea of decomposing a problem
into map and reduce stages - if you are familiar with higher order functions
and functional programming it will probably be very easy!

Implement a solution to the following problems using an iterative solution:
- Given a list of integers, return the sum
- Given a list of integers, return the sum of their squares
- Given a list of integers, return the product of double their squares

*Note: there are tests and stub implementations in the bbc.dojonorth.mapreduce.Exercise1 classes*

These problems can be all be broken into 2 distinct parts: First, the input elements are transformed. Then, the transformed elements are combined into a result.

Try to reimplement your 3 solutions using the language's built-in ```map``` and ```reduce```
functions for these 2 stages.

Can you implement a higher order function ```mapReduce``` for all 3
problems? (N.B. a higher order function is one which takes functions as arguments).


## Getting into the swing of it
In these exercises you will apply the same principles as before with a bigger
set of data - in this case a sample of data from the million song database.

### Getting the songs data

The song lyrics data is provided by Columbia University at:
http://labrosa.ee.columbia.edu/millionsong/musixmatch

There are 3 text files containing the data we need.

If you have WGET installed, you can easily download the zip files with the
following commands:

```
wget http://labrosa.ee.columbia.edu/millionsong/sites/default/files/AdditionalFiles/mxm_dataset_train.txt.zip
wget http://labrosa.ee.columbia.edu/millionsong/sites/default/files/AdditionalFiles/mxm_dataset_test.txt.zip
wget http://labrosa.ee.columbia.edu/millionsong/sites/default/files/AdditionalFiles/mxm_779k_matches.txt.zip
```

Unzip these files somewhere in the project directory structure.

The first 2 files contain lists of (stemmed) words, followed by a list of track
information. This consists of a Million Song Database Track ID, a musiXmatch ID and a list of word
counts (words are indexed starting from 1).
The 3rd file maps Million Song Database track IDs to metadata from the
MusixMatch database. Specifically, we can get the artist and song titles - this
will be useful for displaying results.

Code is provided to load these files. You can also read them
into a SQLite database, which will fill up to around 2GB with indexes!
The SQLite-generating code can be found here:
https://github.com/tbertinmahieux/MSongsDB/tree/master/Tasks_Demos/Lyrics)

### Task 1: Compute the total number of words
With the LyricDataLoader class, you have a method to get a list of tracks, each with a dictionary mapping words (strings) to their respective counts.
The MusixMatchSongMatcher class will load the "mxm_779k_matches.txt" file.
It provides a dictionary mapping Track ID to "MSDTrack", a class with artist and title information.

1. Write a map function which takes a track and produces the dictionary (i.e. Scala Map) of word counts.

2. Write a reduce function which takes 2 dictionaries of word counts and merges them to a single dictionary.

3. Combine your map and reduce functions to get the total word count for all the songs in the Test dataset. Try to find the top 10 words by count.

#### Hints:
- In Scala, use the ```HashMap``` type rather than ```Map``` if you want to benefit from the built-in ```merge``` function for combining two Maps.
- You can also use Lists to store intermediate data instead of dictionaries.

### Task 2: More word statistics

In a similar way to the previous task, write map and reduce functions to find the average number of different words in a song, and the average total number of words in a song.

Find out which songs have the largest vocabulary.

### Task 3: Grep

Your task now is to write a function which finds all songs containing a given word.
Note that our dataset contains stemmed words!

1. Write a map function which takes a track and returns the Track ID and Boolean reflecting whether the track contains the specified word.

2. Write a reduce function which takes a list of Track IDs and a single (Track ID, Boolean) pair. It should add the Track ID to the list if the Boolean is true.

3. Use the map and reduce functions to define your grep function.

### Task 4: Reverse index

Write map and reduce functions which enable you to create a dictionary of words to the Track IDs of songs  containing them.

This reverse-index may be more useful than the Grep function you defined in the previous task.
Can you think of any interesting queries using it?

### Task 5: Obscenity

Try to use your solutions from the previous tasks to find out which songs have the most profane lyrics.

You will need some way to measure how obscene a given song is.

### Task 6: Real world map reduce implementations

Have a look at some of the well known open source MapReduce implementations for your chosen programming language.
Find out how you could port your code to use them.
Most implementations use Hadoop under the hood.

Examples for Scala:
Spark, Scalding, Scoobi.

Amazon provide Elastic Map Reduce (EMR). This runs Hadoop jobs across EC2 instances.
There is a QwikLab available here:
https://run.qwiklabs.com/focuses/preview/1055?locale=en
