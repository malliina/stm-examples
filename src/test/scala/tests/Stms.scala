package tests

import org.specs2.mutable.Specification

import scala.concurrent.stm.{Ref, TMap}

/**
 * @author mle
 */
class Stms extends Specification {
  val ref = Ref(0)
  val map = TMap[String, Int]("Kate" -> 1)
  "STM" should {
    "have a working Ref" in {
      ref.single() mustEqual 0
      ref.single.transform(_ + 1)
      ref.single() mustEqual 1
    }

    "have a working TMap" in {
      map.single.get("Kate") mustEqual Option(1)
      val maybePair = map.single.find(kv => kv._1 == "Kate")
      maybePair.exists(_._2 == 1) must beTrue
    }

    "not mutate outside" in {
      val orig = map.single.toMap
      val origSnap = map.snapshot
      map.single.put("Miranda", 2)
      val origExpected = Map("Kate" -> 1)
      orig mustEqual origExpected
      val expectedSnap = Map("Kate" -> 1, "Miranda" -> 2)
      val snap = map.snapshot
      snap mustEqual expectedSnap
      snap mustEqual map.single.toMap
      origSnap mustEqual origExpected
    }
  }
}
