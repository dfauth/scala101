package type_classes

import org.apache.logging.log4j.scala.Logging
import org.scalatest.{FlatSpec, Matchers}
import Statistics._

class NumberLikeSpec extends FlatSpec with Matchers with Logging {

  val numbers = Vector[Double](13, 23.0, 42, 45, 61, 73, 96, 100, 199, 420, 900, 3839)

  "A NumberLikeDouble" should "be a weird thing" in {
    logger.info("mean is " + mean(numbers))
    mean(numbers) should be(484.25)
    logger.info("median is " + median(numbers))
    median(numbers) should be(96.0)
    logger.info("quartiles is " + quartiles(numbers))
    quartiles(numbers) should be(45.0, 96.0, 420.0)
    logger.info("iqr is " + iqr(numbers))
    iqr(numbers) should be(375.0)
  }

  val ints = Vector[Int](13, 23, 42, 45, 61, 73, 96, 100, 199, 420, 900, 3839)

  "A NumberLikeInt" should "be a weird thing" in {
    logger.info("mean is " + mean(ints))
    mean(ints) should be(484)
    logger.info("median is " + median(ints))
    median(ints) should be(96)
    logger.info("quartiles is " + quartiles(ints))
    quartiles(ints) should be(45, 96, 420)
    logger.info("iqr is " + iqr(ints))
    iqr(ints) should be(375)
  }

  import org.joda.time.Duration._

  val durations = Vector(standardSeconds(20), standardSeconds(57), standardMinutes(2),
    standardMinutes(17), standardMinutes(30), standardMinutes(58), standardHours(2),
    standardHours(5), standardHours(8), standardHours(17), standardDays(1),
    standardDays(4))

  "A NumberLikeDuration" should "be a weird thing" in {
    logger.info("mean is " + mean(durations).getStandardHours)
    mean(durations).getStandardHours should be(12)
    logger.info("median is " + median(durations).getStandardHours)
    median(durations).getStandardHours should be(2)
    logger.info("quartiles is " + quartiles(durations)._1.getStandardHours + " " + quartiles(durations)._2.getStandardHours + " " + quartiles(durations)._3.getStandardHours + " ")
    (quartiles(durations)._1.getStandardHours, quartiles(durations)._2.getStandardHours, quartiles(durations)._3.getStandardHours) should be(0, 2, 17)
    logger.info("iqr is " + iqr(durations).getStandardHours)
    iqr(durations).getStandardHours should be(16)
  }
}