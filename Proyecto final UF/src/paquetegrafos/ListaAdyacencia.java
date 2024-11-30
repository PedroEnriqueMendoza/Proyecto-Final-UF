package paquetegrafos;

//----------------------------------------------------------------------------------------------------------------------

// Clase publica de lista de adyacencias y la declaracion de sus variables
public class ListaAdyacencia {
    Arco primero;
    Arco ultimo;

    //----------------------------------------------------------------------------------------------------------------------

    // Constructor que inicializara valores
    public ListaAdyacencia() {
        primero = null;
        ultimo = null;
    }

    //----------------------------------------------------------------------------------------------------------------------

    // Metodo que nos dejara saber si esta vacia la lista de adyacencias
    public boolean ListaVacia() {
        return primero == null;
    }

    //----------------------------------------------------------------------------------------------------------------------

    // Metodo que creara una nueva adyacencia apartir de su destino y peso
    public void NuevaAdyancencia(Object destino, float peso) {
        if (!Adyancente(destino)) {
            Arco nodo = new Arco(destino, peso);
            if (ListaVacia()) {
                primero = nodo;
                ultimo = nodo;
            } else {
                ultimo.siguiente = nodo;
                ultimo = nodo;
            }
        }
    }

    //----------------------------------------------------------------------------------------------------------------------

    // Metodo que eliminara la adyacencia conciendo su destino
    public void EliminarAdyancencia(Object destino) {
        if (!ListaVacia()) {
            Arco actual = primero;
            Arco anterior = null;

            while (actual != null) {
                if (actual.destino.equals(destino)) {
                    if (anterior == null) { // Si es el primer arco
                        primero = actual.siguiente;
                    } else {
                        anterior.siguiente = actual.siguiente;
                    }
                    if (actual == ultimo) { // Si es el último arco
                        ultimo = anterior;
                    }
                    break;
                }
                anterior = actual;
                actual = actual.siguiente;
            }
        }
    }

    //----------------------------------------------------------------------------------------------------------------------

    // Metodo que nos permitira verfiicar si existe una adyancencia al destino que escribamos dentro del parametro
    public boolean Adyancente(Object destino) {
        Arco temp = primero;
        while (temp != null) {
            if (temp.destino.equals(destino)) {
                return true;
            }
            temp = temp.siguiente;
        }
        return false;
    }
}
