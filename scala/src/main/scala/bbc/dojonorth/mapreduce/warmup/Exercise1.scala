package bbc.dojonorth.mapreduce.warmup

class Exercise1 {

  def sumOfList(xs: List[Int]): Int = xs.sum

  def sumOfSquaresOfList(xs: List[Int]): Int = xs map(x => x*x) sum

  def productOfDoubleOfSquaresOfList(xs: List[Int]): Int = xs map (x=> 2*x*x) reduceOption (_ * _) getOrElse 0

  // mapReduce is a higher-order function
  def mapReduce(mapFn: (Int => Int), reduceFun: (Int,Int) => Int, input: List[Int]): Int = input map mapFn reduce reduceFun

}
