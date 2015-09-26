package com.mle.stm

import scala.collection.concurrent.TrieMap
import scala.concurrent.stm.TMap

/**
 * @author mle
 */
trait ItemMap[K, V] {
  def put(key: K, value: V): Option[V]

  def remove(key: K): Option[V]

  def get(key: K): Option[V]

  def items: Map[K, V]

  def keys: Seq[K]

  def size: Int
}

class TrieItemMap[K, V] extends ItemMap[K, V] {
  val inner = TrieMap.empty[K, V]

  override def put(key: K, value: V): Option[V] = inner.put(key, value)

  override def items: Map[K, V] = inner.toMap

  override def get(key: K): Option[V] = inner.get(key)

  override def size: Int = inner.size

  override def remove(key: K): Option[V] = inner.remove(key)

  override def keys: Seq[K] = inner.keys.toSeq
}

class StmItemMap[K, V] extends ItemMap[K, V] {
  val inner = TMap.empty[K, V]

  override def put(key: K, value: V): Option[V] = inner.single.put(key, value)

  override def items: Map[K, V] = inner.snapshot

  override def get(key: K): Option[V] = inner.single.get(key)

  override def size: Int = inner.single.size

  override def remove(key: K): Option[V] = inner.single.remove(key)

  override def keys: Seq[K] = inner.single.keys.toSeq
}