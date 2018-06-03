import akka.actor.ActorRef

case class StartCounting(n:Int, other:ActorRef)
case class CountDown(n:Int)
case object AskName
case class AskNameOf(other: ActorRef)
case class NameResponse(name:String)
