import org.apache.logging.log4j.scala.Logging
import org.scalatest._
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

class FutureSpec extends FlatSpec with Logging {

  "A Future" should "run in another thread" in {
    logger.info(this+" should run in the main thread")
    Future(logger.info(this+" should run in another thread"))
  }

}