package it.unibo.generics.graph.api;

import java.util.List;

import it.unibo.generics.graph.impl.GraphImpl;

public interface Method<N> {
    List<N> getPath(N source, N target, GraphImpl<N> graph);
}