package com.mj.union;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GenericUnionFind<V> {
	// 一个V 对应 一个 node
	// 之前将村庄维护在数组中，索引即为村庄，对应值为父级村庄
	// 现在将村庄维护在map中，key为村庄，对应值 Node 的 parent 指向自己或父级村庄
	private Map<V, Node<V>> nodes = new HashMap<>();

	public void makeSet(V v) {
		// 不希望每 put 一个 v 就 new 一个 node
		if (nodes.containsKey(v)) return;
		// 保存node节点以便在find时找到它
		nodes.put(v, new Node<>(v));
	}
	
	/**
	 * 找出v的根节点
	 */
	private Node<V> findNode(V v) {
		Node<V> node = nodes.get(v);
		// 判断空是因为我可能没有初始化V对应的node，而直接find(new V())
		if (node == null) return null;
		while (!Objects.equals(node.value, node.parent.value)) {
			// 路径分裂
			node.parent = node.parent.parent;
			node = node.parent;
		}
		return node;
	}
	
	public V find(V v) {
		Node<V> node = findNode(v);
		// 判断空是因为我可能没有初始化V对应的node，而直接find(new V())
		return node == null ? null : node.value;
	}
	
	public void union(V v1, V v2) {
		Node<V> r1 = findNode(v1);
		Node<V> r2 = findNode(v2);
		if (r1 == null || r2 == null) return;
		if (Objects.equals(r1.value, r2.value)) return;
		
		if (r1.rank < r2.rank) {
			r1.parent = r2;
		} else if (r1.rank > r2.rank) {
			r2.parent = r1;
		} else {
			r1.parent = r2;
			r2.rank += 1;
		}
	}
	
	public boolean isSame(V v1, V v2) {
		return Objects.equals(find(v1), find(v2));
	}
	
	private static class Node<V> {
		V value;
		Node<V> parent = this;
		int rank = 1;
		Node(V value) {
			this.value = value;
		}
	}
}
