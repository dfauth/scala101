import org.scalatest._

import scala.util.{Failure, Success, Try}

class TestSpec extends FlatSpec {

  val a:Array[(Int, Int)] = Array((1,1), (2,2), (3,3), (0,4), (5,0), (6,1))

  "A Monad" should "be weird thing" in {
    a.map((t: (Int, Int)) => if(t._2 == 0) None else Some(t._1 / t._2)).foreach(o => println(o))
  }

  "A Monad" should "catch an exception" in {
    a.map((t: (Int, Int)) => Try(t._1 / t._2)).foreach(o => {
//      println(o)
//      o.map(n => println(n))

      o match {
        case Success(n) => println("div gives "+n)
        case Failure(e) => println("exception: "+e.getMessage())
      }
    })
  }
}