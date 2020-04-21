import static java.lang.Integer.max;

public class Arbol<T extends Comparable<T>>
{
    private Nodo<T> raiz;

    private boolean estaVacio(){
        return (raiz == null);
    }

    // ---------------------INSERT Y REMOVE -------------------//
    public void insertar(T elemento) {
        //
        Nodo <T> nodo = new Nodo(elemento);
        if (estaVacio()) {
            raiz = nodo;
        } else{
            InsertaRec(raiz,elemento);
        }
    }

    private Nodo<T> InsertaRec(Nodo<T> nodoPadre, T elemento){      // nodoPadre es el nodo que estamos viendo
        // regresa el nodo creado con el elemento especificado?
        if (nodoPadre==null) {   // caso base. EL nodo que estamos viendo es vacío; entonces llegamos al final del árbol.
            return new Nodo<T>(elemento);
        }else {
            if (elemento.compareTo(nodoPadre.getElemento()) > 0) {      // elemento es mayor a la raíz. Buscar recursivamente en lado derecho
                nodoPadre.setDerecho(InsertaRec(nodoPadre.getDerecho(),elemento));
            }
            if(elemento.compareTo(nodoPadre.getElemento()) < 0) {
                nodoPadre.setIzquierdo(InsertaRec(nodoPadre.getIzquierdo(),elemento));
            }
        }
        return nodoPadre;   // es necesaria esta línea?
    }
    // Al igual que insertar, quitar debe ser una implementación recursiva. Quita del árbol el nodo con el valor indicado.
    public void remove(T e){
        System.out.println("borrar" + e);
        raiz = removeRec(raiz, e);
    }

    public Nodo<T> removeRec(Nodo<T> nodoPadre, T e) {
        // Recursivo que regresa el nodo raíz (nodopadre) que sustituirá el nodo a eliminar.
        // A) Si nodo a eliminar es una hoja, simplemente la quitamos regresando null.
        // B) Si solo tiene un subárbol, la raíz de ese subárbol sube a sustituir.
        // C) Si tiene dos subárboles, escoger como sucesor el más chico del subárbol derecho.
        System.out.println("Llamada recursiva, nodoPadre = " + nodoPadre + "A eliminar: " + e);
        if (nodoPadre == null) {
            System.out.println("nodoPadre vacío. regresamos null");
            return nodoPadre;
        } else {
            // si no está vacío, comenzamos a explorar el arbol hasta encontrar el nodo a eliminar.
            if (e.compareTo(nodoPadre.getElemento()) < 0) {
                System.out.println("Explora izquierda");
                nodoPadre.setIzquierdo(removeRec(nodoPadre.getIzquierdo(), e));
            }
            if (e.compareTo(nodoPadre.getElemento()) > 0) {
                System.out.println("Explora derecha");
                nodoPadre.setDerecho(removeRec(nodoPadre.getDerecho(), e));
            }
            else if (e.compareTo(nodoPadre.getElemento()) == 0) {
                // si nodo padre solo tiene un subárbol, el primer nodo del subárbol lo sustituye
                // si está vacío por ambos lados (es una hoja), de todos modos regresa null
                System.out.println("Encontramos nodo a eliminar");
                if (nodoPadre.isLeaf()) {
                    System.out.println("Nodo a eliminar es hoja. regresar null");
                    return null;
                }else {
                    if (nodoPadre.getIzquierdo() == null) {
                        System.out.println("Sube subárbol derecho");
                        return nodoPadre.getDerecho();
                    } else if (nodoPadre.getDerecho() == null) {
                        System.out.println("Sube subárbol izquierdo");
                        return nodoPadre.getIzquierdo();
                    }
                }
                // si tiene dos subárboles, escogemos el más chico del subárbol derecho como sucesor. Su valor reemplaza el de e.
                System.out.println("Sustituimos valor:" + nodoPadre.getElemento() + " por " + findMin(nodoPadre.getDerecho()).getElemento());
                nodoPadre.setElemento(findMin(nodoPadre.getDerecho()).getElemento());
                // Esto duplica el valor del sucesor, por lo que debemos borrarlo. Como es una hoja, no hay mayor problema y solo es una asignación a null
                System.out.println("Borramos el duplicado hoja del sucesor: " + nodoPadre.getElemento());
                removeRec(nodoPadre.getDerecho(),nodoPadre.getElemento());
            }
            return nodoPadre;       // finalmente regresamos el nodo ya hechas las modificaciones.
        }
    }

    //-------------BUSCADORES-------------//
    public Nodo<T> findMax() {
        return findMax(raiz);
    }
    public Nodo<T> findMax(Nodo<T> nodoPadre) {
        // explora la derecha hasta que ya no haya más nodos a la derecha.
        if (nodoPadre.getDerecho() == null){
            return nodoPadre;
        }else{
            return findMax(nodoPadre.getDerecho());
        }
    }

    public Nodo<T> findMin() {
        return findMin(raiz);
    }
    public Nodo<T> findMin(Nodo<T> nodoPadre) {
        // explora la derecha hasta que ya no haya más nodos a la derecha.
        if (nodoPadre.getIzquierdo() == null){
            return nodoPadre;
        }else{
            return findMin(nodoPadre.getIzquierdo());
        }
    }

    public Nodo<T> find(T e){
        return find(e, raiz);
    }
    public Nodo<T> find(T e, Nodo<T> nodoPadre) {
        if (nodoPadre == null)
            return null;
        if (nodoPadre.getElemento() == e)
            return nodoPadre;
        if (e.compareTo(nodoPadre.getElemento()) < 0)   // si elemento es menor al de nodoPadre
            return find(e, nodoPadre.getIzquierdo());

        return find(e, nodoPadre.getDerecho()); // último caso no explorado: elemento es mayor al de nodoPadre
    }

    //------------RECORRIDOS--------------//
    public void recorreEnOrden(){
        recorreEnOrden(this.raiz);
    }
    public void recorreEnOrden(Nodo<T> nodo){
        if (nodo!=null){
            recorreEnOrden(nodo.getIzquierdo());        // 1. visita izquierdo
            System.out.print(nodo.getElemento() + ",");   // 2. visita raíz
            recorreEnOrden(nodo.getDerecho());             // 3. visita derecho
        }
    }
    public void recorrePreorden(){
        recorrePreorden(this.raiz);
    }
    public void recorrePreorden(Nodo<T> nodo){
        if (nodo!=null){
            System.out.print(nodo.getElemento() + ",");       // 1. visita la raíz
            recorrePreorden(nodo.getIzquierdo());       // 2. recorre lado izquierdo
            recorrePreorden(nodo.getDerecho());     // 3. recorre lado derecho
        }
    }

    public void recorrePostorden(){
        recorrePostorden(this.raiz);
    }
    public void recorrePostorden(Nodo<T> nodo){
        if (nodo!=null){
            recorrePostorden(nodo.getIzquierdo());
            recorrePostorden(nodo.getDerecho());
            System.out.print(nodo.getElemento() + ",");
        }
    }

    public int getHeight() {
        return getHeight(raiz);
    }
    public int getHeight(Nodo<T> n){
        return countHeightRec(n, 0);
    }
    public int countHeightRec(Nodo<T> n, int count){
        // Recusively count height of left and right subtree, and choose the larger one. sum it to the total.
        if (n == null) {
            return count;
        } else {
            count += 1;
            int leftHeight = countHeightRec(n.getIzquierdo(),0);
            int rightHeight = countHeightRec(n.getDerecho(), 0);
            return count + max(leftHeight,rightHeight);
        }

    }

    public Nodo<T> getRaiz() {
        return raiz;
    }
}