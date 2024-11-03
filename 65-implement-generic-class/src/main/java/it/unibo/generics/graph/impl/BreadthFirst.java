package it.unibo.generics.graph.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import it.unibo.generics.graph.api.Method;

public class BreadthFirst<N> implements Method<N> {

    @Override
    public List<N> getPath(N source, N target, GraphImpl<N> graph) {
        if (source == null || !graph.nodeSet().contains(source)) {
            return null;
        }
        if (target == null || !graph.nodeSet().contains(target)){
            return null;
        }

        Queue<List<N>> queue = new LinkedList<>();
        List<N> visited = new ArrayList<>();
        List<N> initialPath = new ArrayList<>();

        initialPath.add(source);
        queue.add(initialPath);
        visited.add(source);

        while (!queue.isEmpty()) {
            List<N> path = queue.poll();
            N lastNode = path.get(path.size() - 1);

            if (lastNode.equals(target)) {
                return path;
            }

            for (N neighbor : graph.linkedNodes(lastNode)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    List<N> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    queue.add(newPath);
                }
            }
        }

        return null;
    }
}