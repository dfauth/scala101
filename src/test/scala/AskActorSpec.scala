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

    val fred = newActor("Fred")
    val wilma = newActor("Wilma")
    val barney = newActor("Barney")

    logger.info("starting")

    implicit val timeout = Timeout(1.seconds)
    logger.info("ask fred his name")
    val answer = fred ? AskName
    logger.info("fred's future: "+answer)

    answer.foreach(n => logger.info("fred's answer: "+n))

    logger.info("ask wilma to ask barney his name")
    val answer1 = wilma ? AskNameOf(barney)
    logger.info("wilma's future: "+answer1)

    answer1.foreach(n1 => logger.info("wilma's answer: "+n1))

    logger.info("terminating")
    system.terminate()
    logger.info("done")
  }

  def newActor(name:String):ActorRef = {
    system.actorOf(Props(new AskActor(name)), name )
  }
}