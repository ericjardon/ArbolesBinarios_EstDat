public class Main
{
    public static void main(String[] args) {
        Arbol<Integer> arbol = new Arbol<>();
        arbol.insertar(50);
        arbol.insertar(40);
        arbol.insertar(100);
        arbol.insertar(80);
        arbol.insertar(70);
        arbol.insertar(90);
        arbol.insertar(200);
        arbol.insertar(150);
        arbol.insertar(130);
        arbol.insertar(400);
        arbol.recorreEnOrden();
        System.out.println();
        arbol.remove(100);
        System.out.println(arbol.getHeight());


    }
}
