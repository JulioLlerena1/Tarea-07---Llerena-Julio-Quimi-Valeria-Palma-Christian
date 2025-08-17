/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.graphs;

import java.util.*;

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

    private Vertex<V, E> findVertex(V content) {
        if (content == null) return null;
        for (Vertex<V, E> v : vertices) {
            if (v.getValue().equals(content)) {
                return v;
            }
        }
        return null;
    }

    public boolean addVertex(V content) {
        if (content == null || findVertex(content) != null) {
            return false;
        }
        Vertex<V, E> newVertex = new Vertex<>(content);
        vertices.add(newVertex);
        return true;
    }

    public boolean connect(V from, V to, double weight) {
        if (from == null || to == null) return false;

        Vertex<V, E> v1 = findVertex(from);
        Vertex<V, E> v2 = findVertex(to);

        if (v1 == null || v2 == null) return false;

        Edge<V, E> edge = new Edge<>(v1, v2, weight);
        v1.getEdges().add(edge);

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


    public void printGraph() {
        for (Vertex<V, E> v : vertices) {
            System.out.print(v.getValue() + " -> ");
            for (Edge<V, E> e : v.getEdges()) {
                System.out.print(e.target.getValue() + "(" + e.weight + ") ");
            }
            System.out.println();
        }
    }
    
    public int caminoCortoBFS(DynamicGraph grafo,String nodoI,String nodoF){
    
        if(grafo == null || grafo.vertices.isEmpty() || grafo.vertices.size() == 1){
            return 0;
        }
        int altura = 0;

        for (Vertex<V, E> v : vertices) {
            v.isVisited = false;
        }

        Deque<Vertex<V,E>> colaVertices = new ArrayDeque<>();
        Vertex<V,E> nodo = grafo.findVertex(nodoI);
        colaVertices.offer(nodo);

        while(!colaVertices.isEmpty()){
            int size = colaVertices.size();
            for(int i = 0; i < size ; i++) {
                Vertex<V, E> v = colaVertices.poll();
                if (v.getValue().equals(nodoF))
                    return altura;

                if (!v.isVisited) {
                    v.isVisited = true;
                    for (Edge<V, E> e : v.edges) {
                        if (!e.target.isVisited) {
                            colaVertices.offer(e.target);
                        }
                    }
                }
            }
            altura++;
        }
        return -1;
    }
}
