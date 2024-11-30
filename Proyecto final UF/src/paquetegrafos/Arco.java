package paquetegrafos;

//--------------------------------------------------------------------------------------------------------------------------

// Clase Arco en un grafo con un destino, peso y un enlace al siguiente arco
public class Arco {
    Object destino;
    float peso;
    Arco siguiente;

    //----------------------------------------------------------------------------------------------------------------------

    //Constructor que inicializara los valores
    public Arco(Object destino) {
        this.destino = destino;
        this.peso = 0;
        this.siguiente = null;
    }

    //----------------------------------------------------------------------------------------------------------------------

    //Constructor 2 que permitira el parametro peso
    public Arco(Object destino, float peso) {
        this.destino = destino;
        this.peso = peso;
        this.siguiente = null;
    }

    //----------------------------------------------------------------------------------------------------------------------

    // Metodo que obtendra el peso
    public float getPeso() {
        return peso;
    }

    //----------------------------------------------------------------------------------------------------------------------

    // Metodo que devuelve una representación en texto del arco
    public String toString() {
        return "Arco{" +
                "destino=" + destino +
                ", peso=" + peso +
                '}';
    }
}

