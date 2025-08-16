/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.graphs;

/**
 *
 * @author Chris
 */
public class StaticGraph<E> {
    int MAX = 20; // Máximo de vértices
    int[][] matrizAdy; 
    E[] vertices;
    boolean esDirigido;
    int numVertices; // contador actual de vértices

    
    public StaticGraph(boolean esDirigido) {
        this.esDirigido = esDirigido;
        this.matrizAdy = new int[MAX][MAX];
        this.vertices = (E[]) new Object[MAX];
        this.numVertices = 0;
        inicializarMatriz();
    }

    // 1. Inicializar la matriz con -1
    private void inicializarMatriz() {
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                matrizAdy[i][j] = -1;
            }
        }
    }

    // 2. Agregar un vértice al arreglo
    public boolean agregarVertice(E vertice) {
        if (numVertices >= MAX || vertice == null) {
            return false;
        }
        vertices[numVertices] = vertice; 
        numVertices = numVertices + 1; 
        return true;
    }

    // 3. Encontrar índice por vértice
    private int encontrarIndice(E vertice) {
        if (vertice == null) {
            return -1;
        }
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(vertice)) {
                return i;
            }
        }
        return -1; 
    }

    // 4. Conectar dos vértices
    public boolean conectar(E v1, E v2) {
        int i = encontrarIndice(v1);
        int j = encontrarIndice(v2);

        if (i == -1 || j == -1) { 
            return false; 
        }

        matrizAdy[i][j] = 1;

        if (!esDirigido) {
            matrizAdy[j][i] = 1;
        }
        return true;
    }
    
    public void normalizarMatriz() {
    for (int i = 0; i < numVertices; i++) {
        for (int j = 0; j < numVertices; j++) {
            if (matrizAdy[i][j] == -1) {
                matrizAdy[i][j] = 0; // ahora representa "sin conexión"
            }
        }
    }
}

    // 5. Mostrar la matriz de adyacencia (para pruebas)
    public void imprimirMatriz() {
        normalizarMatriz(); // convertir -1 a 0
        System.out.println("Matriz de adyacencia:");
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                System.out.print(matrizAdy[i][j] + " ");
            }
            System.out.println();
        }
    }
}

