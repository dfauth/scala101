import akka.actor.{ActorSystem, Props}
import org.apache.logging.log4j.scala.Logging
import org.scalatest._

class CountDownActorSpec extends FlatSpec with Logging {

  val system = ActorSystem("CountDownSystem")

  "A CountDown Actor" should "do somthing" in {

    val actor1 = system.actorOf(Props[CountDownActor], "CountDown1")
    val actor2 = system.actorOf(Props[CountDownActor], "CountDown2")

    logger.info("starting")
    actor1 ! StartCounting(10, actor2)
  }
}