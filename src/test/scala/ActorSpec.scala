import akka.actor.{ActorSystem, Props}
import org.apache.logging.log4j.scala.Logging
import org.scalatest._

class ActorSpec extends FlatSpec with Logging {

//  val logger = Logger.getLogger(ActorSpec.class);

  val system = ActorSystem("SimpleSystem")

  "An Actor" should "do somthing" in {

    val actor = system.actorOf(Props[SimpleActor], "SimpleActor")

    logger.info("starting")
    actor ! "Hi"
    actor ! 42
    actor ! 'a'
  }
}