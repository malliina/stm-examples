package com.mle.stm

import scala.concurrent.stm.{TMap, atomic}

/**
 * @author mle
 */
class StmMap[K, V] {
  val inner = TMap.empty[K, V]

  def get(key: K) = inner.single.get(key)

  def put(key: K, value: V): Option[V] = atomic(implicit txn => inner.put(key, value))

  def existsKey(key: K) = atomic(implicit txn => inner.exists(kv => kv._1 == key))

  def remove(key: K) = atomic(implicit txn => inner.remove(key))

  def find(p: ((K, V)) => Boolean) = atomic(implicit txn => inner.find(p))

  def snapshot = inner.snapshot
}
