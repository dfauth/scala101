import java.util.concurrent.TimeUnit

import org.apache.logging.log4j.scala.Logging
import org.scalatest._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

class FutureSpec extends FlatSpec with Logging {

  "A Future" should "run in another thread" in {
    logger.info(this+" should run in the main thread")
    Future(logger.info(this+" should run in another thread")) // and it does
  }

  "A Future" should "be able to be composed" in {
    val ff1 = f1(1)
    logger.info("ff1: "+ff1)
    ff1.onComplete(t => {
      logger.info("ff1 onComplete: "+t)
    })
    TimeUnit.SECONDS.sleep(2)

    val ff2 = alwaysFails(1)
    logger.info("ff2: "+ff2)
    ff2.onComplete(t => {
      logger.info("ff2 onComplete: "+t)
    })
    ff2.recover {
      case t:Throwable =>
      logger.info("ff2 recover: "+t)
      throw new RuntimeException(t.toString)
    }

    TimeUnit.SECONDS.sleep(2)

    val ff3 = alwaysFails(1)
    logger.info("ff3: "+ff3)
    ff3.onComplete(t => {
      logger.info("ff3 onComplete: "+t)
    })
    val result = ff3.recoverWith {
      case t:Throwable => {
        logger.info("ff2 recoverWith: "+t)
        Future("recoverWith: "+t.toString)
      }
    }
    logger.info("result: "+result)

    TimeUnit.SECONDS.sleep(2)
  }

  "compose " should "futures" in {
    var result = f1(1).andThen{
      case s:Success[String] => s.map(f2)
      case f:Failure[String] => "Oops: "+f.exception.getMessage
    }
    logger.info("result is: "+result)
    result.onComplete(t => logger.info("t.get: "+t.get))
    TimeUnit.SECONDS.sleep(2)

//    val result1 = alwaysFails(1).andThen {
//      case s:Success[String] => s.map(f2)
//      case f:Failure[RuntimeException] => "Oops: "+f.exception.getMessage
//      case _ => "Big Oops"
//    }
//    logger.info("result1 is: "+result1)
//    result1.onComplete(t => logger.info("t.get: "+t.get))
//    TimeUnit.SECONDS.sleep(2)
  }

  def f1(i:Int):Future[String] = {
    val result = Future[String]{
      logger.info(this+" will run in another thread. i is: "+i)
      TimeUnit.SECONDS.sleep(1)
      i.toString
    }
    result
  }

  def f2(str:String):Future[Double] = {
    val result = Future[Double]{
      logger.info(this+" will run in another thread. str is: "+str)
      TimeUnit.SECONDS.sleep(1)
      str.toDouble
    }
    result
  }

  def f3(d:Double):Future[Int] = {
    val result = Future[Int]{
      logger.info(this+" will run in another thread. d is: "+d)
      TimeUnit.SECONDS.sleep(1)
      d.toInt
    }
    result
  }

  def alwaysFails(i:Int):Future[Try[String]] = {
    val result = Future[Try[String]]{
      logger.info(this+" will run in another thread. i is: "+i)
      TimeUnit.SECONDS.sleep(1)
      Failure(new RuntimeException("Oops. Always failing"))
    }
    result
  }

}