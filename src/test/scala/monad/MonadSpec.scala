package monad

import org.apache.logging.log4j.scala.Logging
import org.scalatest._

class MonadSpec extends FlatSpec with Matchers with Logging {

  "A Monad" should "blah blah blah" in {
    import Monad._

    val ref = List(1,2,3,4)
    val refResult = List(2,4,6,8)

    logger.info("ef.flatMap(v => (v * 2).pure[List]: "+ref.flatMap(v => (v * 2).pure[List]))
    ref.flatMap(v => (v * 2).pure[List]) should be (refResult)

    logger.info(Option(1) >>= { v => (v * 2).pure[Option] })
    logger.info("Option(1).flatMap(v => (v * 2).pure[Option]): "+Option(1).flatMap(v => (v * 2).pure[Option]))

    (List(1,2,3,4) >>= { v => (v * 2).pure[List] }) should be (refResult)
    (Option(1) >>= {v => (v * 2).pure[Option]}) should be (Some(2))

    /*
println(List(1,2,3,4) >>= { v => (v * 2).pure[List] })
println(Option(1) >>= { v => (v * 2).pure[Option] })
     */

  }

}