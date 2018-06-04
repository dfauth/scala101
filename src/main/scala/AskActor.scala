import akka.actor.Actor
import org.apache.logging.log4j.scala.Logging
import akka.pattern._
import akka.util.Timeout
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global


import scala.util.{Failure, Success}

class AskActor(val name:String) extends Actor with Logging {
  override def receive = {
    case AskName => {
      logger.info(name+" asked name by "+sender)
      sender ! NameResponse(name)
    }
    case NameResponse(name) => {
      logger.info(name+" received NameResponse from: "+sender)
    }
    case AskNameOf(other) => {
      logger.info(name+" AskNameOf: "+other+" by "+sender)
      val mySender = sender
      implicit val timeout = Timeout(1.seconds)
      val r = other ? AskName
      logger.info(name+" AskNameOf future: "+r)
      r.onComplete {
        case Success(s) =>
          logger.info(name+" AskNameOf success answer: "+s)
          mySender ! NameResponse(s.toString)
        case Failure(e) =>
          logger.info(name+" AskNameOf failure answer: "+e)
      }
    }
  }

}
