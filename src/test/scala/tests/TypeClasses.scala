package tests

import com.mle.stm.Itemizer
import org.specs2.mutable.Specification

import scala.collection.concurrent.TrieMap
import scala.concurrent.stm.TMap

/**
 * @author mle
 */
class TypeClasses extends Specification {
  "Type class" should {
    "compile" in {
      val result = Itemizer.compute(TrieMap.empty[String, Int])
      result mustEqual Option(1)
      val stmResult = Itemizer.compute(TMap.empty[String, Int])
      stmResult mustEqual Option(1)
    }
  }
}
