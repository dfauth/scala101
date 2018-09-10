package actor

case class CommandMessage[T](f:T=>Unit) extends (T => Unit) {
  override def apply(t:T): Unit = f(t)
}
