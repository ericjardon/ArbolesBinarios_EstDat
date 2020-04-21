public class Nodo<T>
{
    private Nodo<T> derecho;
    private Nodo<T> izquierdo;
    private int depth;
    private int height;
    private T elemento;

    public Nodo(T elemento) {
        this.elemento = elemento;
    }

    public Nodo<T> getDerecho() {
        return derecho;
    }

    public void setDerecho(Nodo<T> derecho) {
        this.derecho = derecho;
    }

    public Nodo<T> getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(Nodo<T> izquierdo) {
        this.izquierdo = izquierdo;
    }

    public T getElemento() {
        return elemento;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public boolean isLeaf() {
        return (this.derecho == null && this.izquierdo == null);
    }

    @Override
    public String toString() {
        return "Nodo{" +
                //"derecho=" + derecho +
                //", izquierdo=" + izquierdo +
                "" + elemento +
                '}';
    }
}
