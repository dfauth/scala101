import org.apache.logging.log4j.scala.Logging
import org.scalatest._

class RecursionSpec extends FlatSpec with Matchers with Logging {

  def isSorted[T](a: List[T], f: (T, T) => Boolean): Boolean = {
    a match {
      case h::Nil => true
      case h::t::Nil => f(h,t)
      case h::s::t => f(h, s) && isSorted(s::t, f)
    }
  }

  "Authorisation" should "do more that check for some stupid role..." in {

    val strlen = (r:String,s:String)=> {
      r.length<s.length
    }

    isSorted(List(1,2,3), (i:Int,j:Int)=> i<j) should be (true)
    isSorted(List(3,2,1), (i:Int,j:Int)=> i<j) should be (false)
    isSorted(List("1","2","3"), (r:String,s:String)=> r.length<s.length) should be (false)
    isSorted(List("10","200","3"), (r:String,s:String)=> r.length<s.length) should be (false)
    isSorted(List("1","20","300"), strlen) should be (true)
  }

  val f = (m:Map[String, Int], k:(Char, Int)) => {
    m + (k._1.toString -> k._2)
  }

  "count character" should "occurrence" in {

    val m = Map[String, Int]()
    //    logger.info("result: "+"aaabcdeddfghaabg".groupBy(k=>k).map(k=>k._1 -> k._2.size)) // should be (("a"->5, "b"->1, "c"->1, "d"->3, "e"->1, "f"->1, "g"->2, "h"->1))
    "aaabcdeddfghaabg".groupBy(k=>k).map(k=>k._1 -> k._2.size).foldLeft[Map[String, Int]](m)(f) should be (Map("a"->5, "b"->2, "c"->1, "d"->3, "e"->1, "f"->1, "g"->2, "h"->1))
  }

  trait MyList[T] {
    def add(t:T):MyList[T]
  }

  case class Person(name:String, age:Int)

  "roll your own" should "list" in {

    val f = new Person("fred", 42)
    val w = new Person("wilma", 41)
    val b = new Person("barney", 38)

    class PersonList(people:List[Person] = List.empty[Person]) extends MyList[Person] {
      override def add(t: Person): MyList[Person] = {
        new PersonList(people :+ t)
      }

      override def toString: String = "PersonList["+people.toString()+"]"
    }

    val l = new PersonList()
    logger.info("flintstones: "+l.add(f).add(w).add(b))

  }

}


