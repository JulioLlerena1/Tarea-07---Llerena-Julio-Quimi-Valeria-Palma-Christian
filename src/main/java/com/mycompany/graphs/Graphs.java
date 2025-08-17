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

        // Camino más corto por número de pasos (BFS)
        DynamicGraph s2 = new DynamicGraph(false);
        s2.addVertex("Mercado");
        s2.addVertex("Escuela");
        s2.addVertex("Hospital");
        s2.addVertex("Parque");
        s2.addVertex("Biblioteca");
        s2.addVertex("Estación");
        s2.addVertex("Universidad");

        s2.connect("Mercado", "Escuela", 1);
        s2.connect("Escuela", "Hospital", 1);
        s2.connect("Hospital", "Parque", 1);
        s2.connect("Parque", "Biblioteca", 1);
        s2.connect("Biblioteca", "Estación", 1);
        s2.connect("Estación", "Universidad", 1);
        s2.connect("Universidad", "Parque", 1);
        System.out.println(s2.caminoCortoBFS(s2, "Mercado", "Estación"));


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


    }
}
