import akka.actor.Actor
import org.apache.logging.log4j.scala.Logging

class SimpleActor extends Actor with Logging {
  override def receive = {
    case s:String => sleep(3);logger.info("String: "+s)
    case n:Int => sleep(2);logger.info("Int: "+n)
    case x => sleep(1);logger.info("Something else: "+x)
  }

  def foo = logger.info("Normal method")

  def sleep(secs:Int) = {
    // TimeUnit.SECONDS.sleep(secs)
  }
}
