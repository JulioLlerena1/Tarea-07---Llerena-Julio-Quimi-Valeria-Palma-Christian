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
        StaticGraph g1 = new StaticGraph(false);
        g1.agregarVertice("V1");
        g1.agregarVertice("V2");
        g1.agregarVertice("V3");
        g1.conectar("V1", "V2");
        g1.imprimirMatriz();
        
        DynamicGraph s1 = new DynamicGraph(false);
        
        s1.addVertex("Vertex1");
        s1.addVertex("Vertex2");
        s1.addVertex("Vertex3");
        s1.addVertex("Vertex4");
        s1.addVertex("Vertex5");

        s1.connect("Vertex1", "Vertex2", 10);
        s1.connect("Vertex1", "Vertex3", 5);
        s1.connect("Vertex2", "Vertex3", 2);
        s1.connect("Vertex2", "Vertex4", 1);
        s1.connect("Vertex3", "Vertex4", 9);
        s1.connect("Vertex3", "Vertex5", 2);
        s1.connect("Vertex4", "Vertex5", 4);

        // Ejecutar Dijkstra desde Vertex1
        s1.dijkstra("Vertex1");

        // Mostrar caminos más cortos desde Vertex1
        s1.printShortestPathsFrom("Vertex1");

    }
}
