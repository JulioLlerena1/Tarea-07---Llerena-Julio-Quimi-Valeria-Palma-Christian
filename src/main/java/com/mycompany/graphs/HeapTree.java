/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.graphs;

import java.util.Comparator;

/**
 *
 * @author Chris
 */
public class HeapTree<E> {
    private Comparator<E> f;
    private E[] arreglo;
    private int MAX = 100;
    private int efectivo;
    private boolean isMax;


    public HeapTree(Comparator<E> f, boolean isMax) {
        this.f = f;
        this.isMax = isMax;
        this.efectivo = 0;
        this.arreglo = (E[]) new Object[MAX];
    }
    
    private int padre(int i) {
        if (i <= 0 || i >= efectivo) return -1; 
        return (i - 1) / 2;
    }

    private int izquierdo(int i) {
        int pos = 2 * i + 1;
        if (i < 0 || pos >= efectivo) return -1;
        return pos;
    }

    private int derecho(int i) {
        int pos = 2 * i + 2;
        if (i < 0 || pos >= efectivo) return -1;
        return pos;
        
    }
    
   
    
    private void swap(int i, int j) {
        if (i < 0 || j < 0 || i >= efectivo || j >= efectivo) {
            System.out.println("Índices inválidos para intercambio: " + i + ", " + j);
            return;
        }
        E temp = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = temp;
    }
    
    
    private int hijoMayor(int i) {
        int izq = izquierdo(i);
        int der = derecho(i);

        if (izq == -1 && der == -1) return -1;

        if (der == -1) return izq;

        if (izq == -1) return der;

        if (comparar(arreglo[izq], arreglo[der])) {
            return izq;
        } else {
            return der;
        }
    }
    
    
    private boolean comparar(E a, E b) {
        int resultado = f.compare(a, b);

        if (isMax) {
            return resultado > 0; 
        } else {
            return resultado < 0; 
        }
    }
    
    public void ajustar(int posRaiz) {
        int posIzq = izquierdo(posRaiz);
        int posDer = derecho(posRaiz);
        int posMayor = posRaiz;

        if (isValidIndex(posIzq) && comparar(arreglo[posIzq], arreglo[posMayor])) {
            posMayor = posIzq;
        }

        if (isValidIndex(posDer) && comparar(arreglo[posDer], arreglo[posMayor])) {
            posMayor = posDer;
        }

        if (posMayor != posRaiz) {
            swap(posRaiz, posMayor);
            ajustar(posMayor);
        }
    }
    
    private boolean isValidIndex(int i) {
        return i >= 0 && i < efectivo;
    }
    
    public void construirHeap(E[] elementos) {
        int n = elementos.length;

        if (n > MAX) {
            System.out.println("Cantidad de elementos excede el tamaño máximo del heap.");
            return;
        }

        for (int i = 0; i < n; i++) {
            arreglo[i] = elementos[i];
        }
        efectivo = n;

        for (int i = (efectivo / 2) - 1; i >= 0; i--) {
            ajustar(i);
        }
    }

    
    public void encolar(E elemento) {
        if (efectivo >= MAX) {
            System.out.println("Heap lleno, no se puede insertar más elementos.");
            return;
        }

        arreglo[efectivo] = elemento;
        int posActual = efectivo;
        efectivo++;

        int posPadre = padre(posActual);
        while (posPadre != -1 && comparar(arreglo[posActual], arreglo[posPadre])) {
            swap(posActual, posPadre);
            posActual = posPadre;
            posPadre = padre(posActual);
        }
    }
    
    
    public E desencolar() {
        if (efectivo == 0) {
            System.out.println("Heap vacío");
            return null;
        }

        E raiz = arreglo[0];
        arreglo[0] = arreglo[efectivo - 1];
        arreglo[efectivo - 1] = null;
        efectivo--;

        ajustar(0);

        return raiz;
    }

}
