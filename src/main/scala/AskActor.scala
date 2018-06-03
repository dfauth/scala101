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
      logger.info(self+" ask name")
      sender ! NameResponse(name)
    }
    case NameResponse(name) => {
      logger.info(self+" NameResponse: "+name)
    }
    case AskNameOf(other) => {
      val mySender = sender
      implicit val timeout = Timeout(1.seconds)
      val r = other ? AskName
      logger.info(self+" AskNameOf returned: "+r)
      r.onComplete {
        case Success(s) =>
          logger.info(self+" AskNameOf returned success: "+s)
          mySender ! NameResponse(s.toString)
        case Failure(e) =>
          logger.info(self+" AskNameOf failed: "+e)
      }
    }
  }

}
