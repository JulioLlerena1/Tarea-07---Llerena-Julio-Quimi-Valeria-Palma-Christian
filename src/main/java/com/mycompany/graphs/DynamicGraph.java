/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.graphs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * @author Chris
 */
public class DynamicGraph<V, E> {
    private List<Vertex<V, E>> vertices;
    private boolean directed;

    public DynamicGraph(boolean directed) {
        this.vertices = new ArrayList<>();
        this.directed = directed;
    }

    // 1. Find vertex by content
    private Vertex<V, E> findVertex(V content) {
        if (content == null) return null;
        for (Vertex<V, E> v : vertices) {
            if (v.getValue().equals(content)) {
                return v;
            }
        }
        return null;
    }

    // 2. Add vertex
    public boolean addVertex(V content) {
        if (content == null || findVertex(content) != null) {
            return false;
        }
        Vertex<V, E> newVertex = new Vertex<>(content);
        vertices.add(newVertex);
        return true;
    }

    // 3. Connect two vertices (add edge)
    public boolean connect(V from, V to, double weight) {
        if (from == null || to == null) return false;

        Vertex<V, E> v1 = findVertex(from);
        Vertex<V, E> v2 = findVertex(to);

        if (v1 == null || v2 == null) return false;

        // Add edge v1 -> v2
        Edge<V, E> edge = new Edge<>(v1, v2, weight);
        v1.getEdges().add(edge);

        // If not directed, also add v2 -> v1
        if (!directed) {
            Edge<V, E> reverseEdge = new Edge<>(v2, v1, weight);
            v2.getEdges().add(reverseEdge);
        }
        return true;
    }
    
    
    
    public void dijkstra(V startContent) {
        Vertex<V, E> start = findVertex(startContent);
        if (start == null) return;
        start.setDistance(0);
        // PriorityQueue by distance
        PriorityQueue<Vertex<V, E>> queue = new PriorityQueue<>(
                (a,b) -> { return  Double.compare(a.getDistance(), b.getDistance());  }
        );
        queue.add(start);

        while (!queue.isEmpty()) {
            Vertex<V, E> u = queue.poll();
            if(!u.isVisited){
                for (Edge<V, E> edge : u.getEdges()) {
                    Vertex<V, E> v = edge.target;
                    double newDist = u.getDistance() + edge.weight;
                    if (newDist < v.getDistance()) {
                        v.setDistance(newDist);
                        v.setPredecessor(u);
                        queue.add(v);
                    }
                }
                u.isVisited = true;
            }
        }
    }
    
    public void printShortestPathsFrom(V startContent) {
        Vertex<V, E> start = findVertex(startContent);
        if (start == null) {
            System.out.println("Start vertex not found.");
            return;
        }

        System.out.println("Shortest paths from vertex: " + startContent);
        for (Vertex<V, E> v : vertices) {
            
            if (v != start) { // solo procesar si no es el vértice inicial
                System.out.print("To " + v.getValue() + ": ");

                if (v.getDistance() == Double.POSITIVE_INFINITY) {
                    System.out.println("No path");
                } else {
                    List<V> path = new ArrayList<>();
                    Vertex<V, E> current = v;
                    while (current != null) {
                        path.add(0, current.getValue());
                        current = current.getPredecessor();
                    }

                    System.out.print("Path: ");
                    for (int i = 0; i < path.size(); i++) {
                        System.out.print(path.get(i));
                        if (i < path.size() - 1) System.out.print(" -> ");
                    }
                    System.out.println(" | Distance: " + v.getDistance());
                }
            }
        }
    }


    // 4. Print graph
    public void printGraph() {
        for (Vertex<V, E> v : vertices) {
            System.out.print(v.getValue() + " -> ");
            for (Edge<V, E> e : v.getEdges()) {
                System.out.print(e.target.getValue() + "(" + e.weight + ") ");
            }
            System.out.println();
        }
    }
}
