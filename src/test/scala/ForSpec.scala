import org.apache.logging.log4j.scala.Logging
import org.scalatest._

class ForSpec extends FlatSpec with Logging {

  "A For loop" should "run in a loop" in {

    logger.info("0 to 9 using to: "+ (0 to 9))
    logger.info("0 to 9 using until: "+ (0 until 9))

    for(i <- 0 to 9) logger.info("using to i: "+i)
    for(i <- 0 until 9) logger.info("using until i: "+i)
  }


}