package com.mle.stm

/**
 * @author mle
 */
object Itemizer {
  def compute[C[_, _]](c: C[String, Int])(implicit i: ItemMapTypeClass[String, Int, C]): Option[Int] = {
    i.put(c, "key", 1)
    i.get(c, "key")
  }
}
