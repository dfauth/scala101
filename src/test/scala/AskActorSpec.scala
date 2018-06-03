import akka.actor.{ActorRef, ActorSystem, Props}
import akka.util.Timeout
import org.apache.logging.log4j.scala.Logging
import org.scalatest._
import akka.pattern._

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class AskActorSpec extends FlatSpec with Logging {

  val system = ActorSystem("SimpleSystem")

  "An AskActor" should "do somthing" in {

    val actor1 = newActor("Fred")
    val actor2 = newActor("Wilma")
    val actor3 = newActor("Barney")

    logger.info("starting")

    implicit val timeout = Timeout(1.seconds)
    val answer = actor1 ? AskName
    logger.info("answer: "+answer)

    answer.foreach(n => logger.info("foreach answer: "+n))

    val answer1 = actor2 ? AskNameOf(actor3)
    logger.info("answer1: "+answer1)

    answer1.foreach(n1 => logger.info("foreach answer1: "+n1))

    logger.info("terminating")
    system.terminate()
    logger.info("done")
  }

  def newActor(name:String):ActorRef = {
    system.actorOf(Props(new AskActor(name)), name )
  }
}