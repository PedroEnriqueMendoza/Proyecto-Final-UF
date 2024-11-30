package paquetegrafos;

//--------------------------------------------------------------------------------------------------------------------------

import java.io.*;
import java.util.*;

//--------------------------------------------------------------------------------------------------------------------------

// Clase del grafo dirigido y la declaracion de sus variables
public class Grafo {
    private NodoGrafo primero;
    private NodoGrafo ultimo;

    //----------------------------------------------------------------------------------------------------------------------

    // Constructor de inicializacion
    public Grafo() {
        primero = null;
        ultimo = null;
    }

    //----------------------------------------------------------------------------------------------------------------------

    // Metodo booleano para verificar si el Grafo esta vacio
    public boolean GrafoVacio() {
        return primero == null;
    }

    //----------------------------------------------------------------------------------------------------------------------

    // Metodo para obtener el numero de vertices
    public int GetNumeroDeVertices() {
        int contador = 0;
        NodoGrafo temp = primero;
        while (temp != null) {
            contador++;
            temp = temp.siguiente;
        }
        return contador;
    }

    //----------------------------------------------------------------------------------------------------------------------

    // Metodo en donde se actulizara y guardara el archivo
    public void GuardarArchivo(String rutaArchivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            NodoGrafo tempFila = primero;

            while (tempFila != null) {
                StringBuilder linea = new StringBuilder();
                NodoGrafo tempColumna = primero;

                while (tempColumna != null) {
                    // Verificar si hay una arista entre tempFila y tempColumna
                    Arco arco = tempFila.lista.primero;
                    boolean encontrado = false;

                    while (arco != null) {
                        if (arco.destino.equals(tempColumna.dato)) {
                            linea.append((int) arco.peso).append(",");
                            encontrado = true;
                            break;
                        }
                        arco = arco.siguiente;
                    }

                    if (!encontrado) {
                        linea.append("0,");
                    }

                    tempColumna = tempColumna.siguiente;
                }

                // Eliminar la última coma y escribir la línea en el archivo
                bw.write(linea.toString().replaceAll(",$", ""));
                bw.newLine();
                tempFila = tempFila.siguiente;
            }

        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    //----------------------------------------------------------------------------------------------------------------------

    // Metodo booleano que nos deja saber si existe un vertice
    public boolean ExisteVertice(Object dato) {
        NodoGrafo temp = primero;
        while (temp != null) {
            if (temp.dato.equals(dato)) {
                return true;
            }
            temp = temp.siguiente;
        }
        return false;
    }

    //----------------------------------------------------------------------------------------------------------------------

    //Metodo que nos permitira agregar un vertice a traves de un parametro dato
    public void AgregarVertice(Object dato) {
        if (!ExisteVertice(dato)) {
            NodoGrafo nuevo = new NodoGrafo(dato);
            if (GrafoVacio()) {
                primero = nuevo;
                ultimo = nuevo;
            } else {
                ultimo.siguiente = nuevo;
                ultimo = nuevo;
            }
        }
    }

    //----------------------------------------------------------------------------------------------------------------------

    //Metodo que nos permitira eliminar un vertice a traves de un parametro dato
    public void EliminarVertice(Object dato) {
        if (!ExisteVertice(dato)) {
            System.out.println("El vértice " + dato + " no existe.");
            return;
        }

        // Eliminar todas las aristas que apuntan al vértice
        NodoGrafo temp = primero;
        while (temp != null) {
            if (temp.lista.Adyancente(dato)) {
                temp.lista.EliminarAdyancencia(dato);
            }
            temp = temp.siguiente;
        }

        // Eliminar el vértice de la lista principal de nodos
        NodoGrafo actual = primero;
        NodoGrafo anterior = null;

        while (actual != null) {
            if (actual.dato.equals(dato)) {
                if (anterior == null) { // Si es el primer nodo
                    primero = actual.siguiente;
                    if (actual == ultimo) { // Si era el único nodo
                        ultimo = null;
                    }
                } else {
                    anterior.siguiente = actual.siguiente;
                    if (actual == ultimo) { // Si es el último nodo
                        ultimo = anterior;
                    }
                }
                System.out.println("Vértice " + dato + " eliminado.");
                return;
            }
            anterior = actual;
            actual = actual.siguiente;
        }
    }

    //----------------------------------------------------------------------------------------------------------------------

    //Metodo que nos permitira agregar un arista conociendo el parametro de origen, destino y el peso
    public void AgregarArista(Object origen, Object destino, float peso) {
        if (ExisteVertice(origen) && ExisteVertice(destino)) {
            NodoGrafo temp = primero;
            while (temp != null) {
                if (temp.dato.equals(origen)) {
                    temp.lista.NuevaAdyancencia(destino, peso);
                    break;
                }
                temp = temp.siguiente;
            }
        }
    }

    //----------------------------------------------------------------------------------------------------------------------

    //Metodo que nos permitira eliminar una arista conociendo su orgen y destino
    public void EliminarArista(Object origen, Object destino) {
        if (ExisteVertice(origen) && ExisteVertice(destino)) {
            NodoGrafo temp = primero;
            while (temp != null) {
                if (temp.dato.equals(origen)) {
                    if (temp.lista.Adyancente(destino)) {
                        temp.lista.EliminarAdyancencia(destino);
                        System.out.println("Arista eliminada de " + origen + " a " + destino);
                    } else {
                        System.out.println("La arista de " + origen + " a " + destino + " no existe.");
                    }
                    break;
                }
                temp = temp.siguiente;
            }
        } else {
            System.out.println("Uno o ambos vértices no existen: " + origen + ", " + destino);
        }
    }

    //----------------------------------------------------------------------------------------------------------------------

    // Este metodo nos permite llamar al archivo que tenemos colocando su ruta como parametro
    public void LlamarArchivo(String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            int fila = 0;

            // Crear los nodos (A, B, C, ...) según el número de columnas
            if ((linea = br.readLine()) != null) {
                String[] columnas = linea.split(",");
                for (int i = 0; i < columnas.length; i++) {
                    char nodo = (char) ('A' + i);
                    AgregarVertice(String.valueOf(nodo));
                }
            }

            // Volver al inicio del archivo para leer las conexiones
            br.close();
            try (BufferedReader br2 = new BufferedReader(new FileReader(rutaArchivo))) {
                while ((linea = br2.readLine()) != null) {
                    String[] pesos = linea.split(",");
                    char origen = (char) ('A' + fila);

                    for (int columna = 0; columna < pesos.length; columna++) {
                        float peso = Float.parseFloat(pesos[columna]);
                        if (peso > 0) {
                            char destino = (char) ('A' + columna);
                            AgregarArista(String.valueOf(origen), String.valueOf(destino), peso);
                        }
                    }
                    fila++;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    //----------------------------------------------------------------------------------------------------------------------

    // Este metodo calculara la Ruta critica asi como la creacion del grafo
    public void CalcularRutaCritica() {
        Map<Object, Integer> entradas = new HashMap<>();
        Map<Object, NodoGrafo> nodos = new HashMap<>();

        // Inicializar entradas y nodos
        NodoGrafo temp = primero;
        while (temp != null) {
            entradas.put(temp.dato, 0);
            nodos.put(temp.dato, temp);
            temp = temp.siguiente;
        }

        // Calcular grados de entrada
        temp = primero;
        while (temp != null) {
            Arco arco = temp.lista.primero;
            while (arco != null) {
                entradas.put(arco.destino, entradas.get(arco.destino) + 1);
                arco = arco.siguiente;
            }
            temp = temp.siguiente;
        }

        // Orden topológico con cálculo de inicio temprano
        Queue<NodoGrafo> cola = new LinkedList<>();
        Stack<NodoGrafo> pila = new Stack<>(); // Para almacenar el orden topológico inverso

        for (Map.Entry<Object, Integer> entry : entradas.entrySet()) {
            if (entry.getValue() == 0) {
                cola.add(nodos.get(entry.getKey()));
            }
        }

        while (!cola.isEmpty()) {
            NodoGrafo actual = cola.poll();
            pila.push(actual); // Guardamos el nodo en la pila para usarlo en el inicio tardío

            Arco arco = actual.lista.primero;
            while (arco != null) {
                NodoGrafo destino = nodos.get(arco.destino);
                destino.inicioTemprano = Math.max(destino.inicioTemprano, actual.inicioTemprano + (int) arco.peso);
                entradas.put(arco.destino, entradas.get(arco.destino) - 1);
                if (entradas.get(arco.destino) == 0) {
                    cola.add(destino);
                }
                arco = arco.siguiente;
            }
        }

        // Calcular inicio tardío (propagación hacia atrás)
        NodoGrafo nodoFinal = pila.peek();
        nodoFinal.inicioTardio = nodoFinal.inicioTemprano; // Nodo final: inicio tardío = inicio temprano

        while (!pila.isEmpty()) {
            NodoGrafo actual = pila.pop();

            Arco arco = actual.lista.primero;
            while (arco != null) {
                NodoGrafo destino = nodos.get(arco.destino);
                actual.inicioTardio = Math.min(actual.inicioTardio, destino.inicioTardio - (int) arco.peso);
                arco = arco.siguiente;
            }
            // Calcular la holgura del nodo
            actual.CalcularHolgura();
        }

        // Mostrara los nodos en la ruta crítica asi como su inicio temprano y tardio
        StringBuilder resultado = new StringBuilder("Ruta crítica:\n");
        temp = primero;
        while (temp != null) {
            if (temp.holgura == 0) { // Vericador que nos permitira colocar aquellos nodos que su holgura es 0
                resultado.append(temp.dato)
                        .append(" (Inicio temprano: ").append(temp.inicioTemprano)
                        .append(", Inicio tardío: ").append(temp.inicioTardio)
                        .append(")\n"); // Salto de línea en lugar de flecha
            }
            temp = temp.siguiente;
        }

        System.out.println(resultado.toString().trim());
    }

}
