/** Creado el "23 - Noviembre - 2024" por:
 * Pedro Enrique Mendoza Garcia   A01772504
 * Mayra González Martínez        A01769543
 * Bruno Arturo Goñi Flores       A01769630
 * --------------------------------------------
 * Ultima modificacion "29 - Noviembre - 2024"
 */

//-------------------------------------------------------------------------------------------------------------------------

package paquetegrafos;

//-------------------------------------------------------------------------------------------------------------------------

// Se crea la clase principal en donde se correra
public class PaqueteGrafos {

    public static void main(String[] args) {
        Grafo g = new Grafo();

        g.LlamarArchivo("Grafo.txt");
        g.CalcularRutaCritica();
        //AGREGAR INSTRUCCIONES AQUI
    }
}
