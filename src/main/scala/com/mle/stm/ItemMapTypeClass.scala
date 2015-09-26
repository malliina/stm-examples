package com.mle.stm

import scala.collection.concurrent.TrieMap
import scala.concurrent.stm.TMap


/**
 * @author mle
 */
trait ItemMapTypeClass[K, V, -M[_, _]] {
  def put(m: M[K, V], key: K, value: V): Option[V]

  def remove(m: M[K, V], key: K): Option[V]

  def get(m: M[K, V], key: K): Option[V]

  def snapshot(m: M[K, V]): Map[K, V]

  def keys(m: M[K, V]): Seq[K]

  def size(m: M[K, V]): Int
}

object ItemMapTypeClass {
  implicit def map[K, V]: ItemMapTypeClass[K, V, TrieMap] = new ItemMapTypeClass[K, V, TrieMap] {
    override def put(m: TrieMap[K, V], key: K, value: V): Option[V] = m.put(key, value)

    override def snapshot(m: TrieMap[K, V]): Map[K, V] = m.readOnlySnapshot().toMap

    override def get(m: TrieMap[K, V], key: K): Option[V] = m.get(key)

    override def size(m: TrieMap[K, V]): Int = m.size

    override def remove(m: TrieMap[K, V], key: K): Option[V] = m.remove(key)

    override def keys(m: TrieMap[K, V]): Seq[K] = m.keys.toSeq
  }

  implicit def stm[K, V]: ItemMapTypeClass[K, V, TMap] = new ItemMapTypeClass[K, V, TMap] {
    override def put(m: TMap[K, V], key: K, value: V): Option[V] = m.single.put(key, value)

    override def snapshot(m: TMap[K, V]): Map[K, V] = m.snapshot

    override def get(m: TMap[K, V], key: K): Option[V] = m.single.get(key)

    override def size(m: TMap[K, V]): Int = m.single.size

    override def remove(m: TMap[K, V], key: K): Option[V] = m.single.remove(key)

    override def keys(m: TMap[K, V]): Seq[K] = m.single.keys.toSeq
  }
}
