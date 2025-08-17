/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.graphs;

/**
 *
 * @author Chris
 */

public class Graphs {

    public static void main(String[] args) {

        //Encontrar el camino más corto con Dijkstra:
        DynamicGraph s1 = new DynamicGraph(false);
        s1.addVertex("A");
        s1.addVertex("B");
        s1.addVertex("C");
        s1.addVertex("D");
        s1.addVertex("E");
        s1.addVertex("Z");

        s1.connect("A", "B", 4);
        s1.connect("A", "C", 2);
        s1.connect("B", "C", 1);
        s1.connect("B", "D", 5);
        s1.connect("D", "C", 8);
        s1.connect("D", "E", 2);
        s1.connect("D", "Z", 6);
        s1.connect("C", "E", 10);
        s1.connect("E", "Z", 3);

        s1.dijkstra("A");

        s1.printShortestPathsFrom("A");
        
        
        // Camino más corto por número de pasos (BFS)
        DynamicGraph s2 = new DynamicGraph(false);
        s2.addVertex("A");
        s2.addVertex("B");
        s2.addVertex("C");
        s2.addVertex("D");
        s2.addVertex("E");
        s2.addVertex("Z");

        s2.connect("A", "B", 1);
        s2.connect("A", "C", 1);
        s2.connect("B", "C", 1);
        s2.connect("B", "D", 1);
        s2.connect("D", "C", 1);
        s2.connect("D", "E", 1);
        s2.connect("D", "Z", 1);
        s2.connect("C", "E", 1);
        s2.connect("E", "Z", 1);
        
        s2.caminoCortoBFS(s2, "D", "E");

    }
}
