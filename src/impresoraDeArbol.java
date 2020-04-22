public class impresoraDeArbol<T extends Comparable<T>>
{
    private Arbol a;
    private String C1 = "├──";  // para bajar de nivel y apuntar a nodos de mayor valor e intermedios
    private String C2 = "└──";  // para bajar de nivel y apuntar al nodo de menor valor (hasta la izquierda) de subárbol
    private String C3 = "│";    // para unir ramas en el mismo nivel y dejar espacio

    public impresoraDeArbol(Arbol a){
        this.a = a;
    }
        // Usaremos la convención de diagrama horizontal, puesto que es una implementación versátil y práctica
        // Dicha implementación requiere un recorrido en Reversa: raíz, derecha, izquierda.
        // de esta forma hasta abajo quedan los más chicos. El subárbol derecho queda arriba y el izquierdo queda abajo.

        private String recorreRaiz(Nodo<T> raiz){
            // StringBuilder es como un acumulador de tipos String.
            if (raiz == null) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            sb.append(raiz.getElemento());

            String nexoDerecho = (raiz.getIzquierdo() != null) ? "├──" : "└──";
            String nexoIzquierdo = "└──";

            recorreNodos(sb, "", nexoDerecho, raiz.getDerecho(), raiz.getIzquierdo()!=null);
            recorreNodos(sb, "", nexoIzquierdo, raiz.getIzquierdo(), false);

            return sb.toString();
        }

    private void recorreNodos(StringBuilder sb, String espacio, String nexo, Nodo<T> nodo,
                              boolean hayIzquierdo) {
        // StringBuilder es como un acumulador de tipos String.
        if (nodo != null) {
            // visita e imprime la raíz, después de haber hecho el espacio y nexo correspondientes.
            sb.append("\n");
            sb.append(espacio);
            sb.append(nexo);
            sb.append(nodo.getElemento());

            StringBuilder espacioBuilder = new StringBuilder(espacio);
            if (hayIzquierdo) {
                espacioBuilder.append("│  ");       // carácter y dos espacios
            } else{
                espacioBuilder.append("   ");       // tres espacios
            }


            String espacioDeAmbos = espacioBuilder.toString();
            String nexoDerecho = (nodo.getIzquierdo() != null) ? "├──" : "└──";
            String nexoIzquierdo = "└──";

            recorreNodos(sb, espacioDeAmbos, nexoDerecho, nodo.getDerecho(), nodo.getIzquierdo()!=null);
            recorreNodos(sb, espacioDeAmbos, nexoIzquierdo, nodo.getIzquierdo(), false);
        }
    }

    public void imprime(){
        System.out.print(recorreRaiz(a.getRaiz()));
    }

}
