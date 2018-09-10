package actor

import akka.actor.Actor
import org.apache.logging.log4j.scala.Logging

class CommandActor[T](val ctx:T) extends Actor with Logging {

  override def receive = {
    case m:CommandMessage[T] => m(ctx)
    case _ => logger.info("Oops")
  }
}

