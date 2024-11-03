package it.unibo.generics.graph.impl;

import it.unibo.generics.graph.api.Graph;
import it.unibo.generics.graph.api.Method;

import java.util.*;

/**
 * Simple implementation of the Graph interface using a Map to store nodes and edges.
 * 
 * @param <N> type of nodes in the graph
 */
public class GraphImpl<N> implements Graph<N> {

    private final Map<N, Set<N>> adjacency = new HashMap<>();
    private final Method<N> method;

    public GraphImpl(Method<N> method) {
        this.method = method;
    }

    @Override
    public void addNode(N node) {
        if (node != null && !adjacency.containsKey(node)) {
            adjacency.put(node, new HashSet<>());
        }
    }

    @Override
    public void addEdge(N source, N target) {
        if (source != null && target != null) {
            addNode(source);
            addNode(target);
            adjacency.get(source).add(target);
        }
    }

    @Override
    public Set<N> nodeSet() {
        return adjacency.keySet();
    }

    @Override
    public Set<N> linkedNodes(N node) {
        Set<N> list = new HashSet<>();
        list.addAll(adjacency.get(node));
        return list;
    }

    @Override
    public List<N> getPath(N source, N target) {
        return method.getPath(source, target, this);
    }
}