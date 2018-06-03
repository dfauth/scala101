package type_classes

object Math {
  trait NumberLike[T] {
    def plus(x:T, y:T):T
    def divide(x:T, y:T):T
    def minus(x:T, y:T):T
    def unit(n:Int):T
//    def *(x:T, y:T):T
  }

  object NumberLike {
    implicit object NumberLikeDouble extends NumberLike[Double] {
      def plus(x:Double, y:Double):Double = x + y
      def divide(x:Double, y:Double):Double = x / y
      def minus(x:Double, y:Double):Double = x - y
      def unit(n:Int): Double = n.toDouble
//      def *(x:Double, y:Double):Double = x * y
    }
    implicit object NumberLikeInt extends NumberLike[Int] {
      def plus(x:Int, y:Int):Int = x + y
      def divide(x:Int, y:Int):Int = x / y
      def minus(x:Int, y:Int):Int = x - y
      def unit(n:Int): Int = n
//      def *(x:Int, y:Int):Int = x * y
    }
    import org.joda.time.Duration
    implicit object NumberLikeDuration extends NumberLike[Duration] {
      def plus(x: Duration, y: Duration): Duration = x.plus(y)
      def divide(x: Duration, y: Duration): Duration = Duration.millis(x.getMillis / y.getMillis)
      def minus(x: Duration, y: Duration): Duration = x.minus(y)
      def unit(n:Int): Duration = new org.joda.time.Duration(n)
    }
  }
}
