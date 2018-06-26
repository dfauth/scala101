import java.util.concurrent.TimeUnit

import org.apache.logging.log4j.scala.Logging
import org.scalatest._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ComposeAndThenSpec extends FlatSpec with Logging {

  "compose " should "and then" in {
    logger.info("f1(1): "+f1(1))
    logger.info("f2(f1(1)): "+f2(f1(1)))
    logger.info("f3(f2(f1(1))): "+f3(f2(f1(1))))
    val f = f3 _ compose f2 compose f1

    logger.info("f3 _ compose f2 compose f1(1): "+f(1))

    val g = f1 _ andThen f2 andThen f3

    logger.info("f1 _ andThen f2 andThen f3(1): "+g(1))
  }

  def f1(i:Int):String = {
    logger.info("f1 called with argument: "+i)
    i.toString
  }

  def f2(str:String):Double = {
    logger.info("f2 called with argument: "+str)
    str.toDouble * 2
  }

  def f3(d:Double):Int = {
    logger.info("f3 called with argument: "+d)
    d.toInt * 10
  }

  def alwaysFails(i:Int):Future[String] = {
    val result = Future[String]{
      logger.info(this+" will run in another thread. i is: "+i)
      TimeUnit.SECONDS.sleep(1)
      throw new RuntimeException("Oops. Always failing")
    }
    result
  }

}