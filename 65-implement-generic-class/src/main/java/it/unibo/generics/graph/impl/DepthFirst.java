package it.unibo.generics.graph.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import it.unibo.generics.graph.api.Method;

public class DepthFirst<N> implements Method<N> {

    @Override
    public List<N> getPath(N source, N target, GraphImpl<N> graph) {
        if (source == null || !graph.nodeSet().contains(source)) {
            return null;
        }
        if (target == null || !graph.nodeSet().contains(target)){
            return null;
        }

        Stack<List<N>> stack = new Stack<>();
        List<N> visited = new ArrayList<>();
        List<N> initialPath = new ArrayList<>();

        initialPath.add(source);
        stack.push(initialPath);
        visited.add(source);

        while (!stack.isEmpty()) {
            List<N> path = stack.pop();
            N lastNode = path.get(path.size() - 1);

            if (lastNode.equals(target)) {
                return path;
            }

            for (N neighbor : graph.linkedNodes(lastNode)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    List<N> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    stack.push(newPath);
                }
            }
        }

        return null;
    }
}