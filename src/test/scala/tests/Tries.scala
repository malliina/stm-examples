package tests

import org.specs2.mutable.Specification

import scala.collection.concurrent.TrieMap

/**
 * @author mle
 */
class Tries extends Specification {
  "TrieMap" should {
    "behave" in {
      val map = TrieMap("a" -> 1)
      val snap = map.snapshot()
      val readOnlySnap = map.readOnlySnapshot()
      map.put("b", 2)
      map.get("b") mustEqual Option(2)
      readOnlySnap.get("b") mustEqual None
      snap.get("b") mustEqual None
      val snap2 = map.snapshot()
      snap2.get("b") mustEqual Option(2)
      snap.put("b", 3)
      snap.get("b") mustEqual Option(3)
    }
  }
}
