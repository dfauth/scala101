package actor

import akka.actor.{ActorSystem, Props}
import org.apache.logging.log4j.scala.Logging
import org.scalatest._

class CommandActorSpec extends FlatSpec with Logging {

  val system = ActorSystem("CommandActorSystem")

  "An Actor" should "do somthing" in {

    val commandActor = system.actorOf(Props(new CommandActor[CommandActorSpec](this)), "CommandActor") // Props(new AskActor(name)), name

    logger.info("starting")
    commandActor ! CommandMessage[CommandActorSpec](s => logger.info("s is: "+s))
  }
}

