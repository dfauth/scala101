import akka.actor.Actor
import org.apache.logging.log4j.scala.Logging

class CountDownActor extends Actor with Logging {
  override def receive = {
    case StartCounting(n, other) => {
      logger.info(self+" Start counting: "+n+" "+other)
      other ! CountDown(n-1)
    }
    case CountDown(n) => {
      if(n>0) {
        logger.info(self+" CountDown: "+n)
        sender ! CountDown(n-1)
      } else {
        context.system.terminate()
      }
    }
  }

}
