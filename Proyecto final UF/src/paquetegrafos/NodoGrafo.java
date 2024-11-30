package paquetegrafos;

//-------------------------------------------------------------------------------------------------------------------------

//Clase Nodo Grafos y la declaracion de sus variables
public class NodoGrafo {
    Object dato;
    ListaAdyacencia lista;
    NodoGrafo siguiente;
    int inicioTemprano;
    int inicioTardio;
    int holgura;

    //----------------------------------------------------------------------------------------------------------------------

    // Constructor que inicilaizara las variables con sus valores
    public NodoGrafo(Object x) {
        dato = x;
        lista = new ListaAdyacencia();
        siguiente = null;
        inicioTemprano = 0;
        inicioTardio = Integer.MAX_VALUE; // Inicializado con un valor alto
        holgura = 0;
    }

    //----------------------------------------------------------------------------------------------------------------------

    // Metodo que calculara la holgura restando su inicio tardio con el temprano
    public void CalcularHolgura() {
        holgura = inicioTardio - inicioTemprano;
    }

    //----------------------------------------------------------------------------------------------------------------------

    // Metodo que devuelve una representación en texto del nodo
    public String toString() {
        return "NodoGrafos{" +
                "dato=" + dato +
                ", inicioTemprano=" + inicioTemprano +
                ", inicioTardio=" + inicioTardio +
                ", holgura=" + holgura +
                '}';
    }
}
