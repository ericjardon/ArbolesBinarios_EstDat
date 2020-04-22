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
        System.out.println("\nPrueba impresora");
        new impresoraDeArbol<>(arbol).imprime();
        arbol.remove(100);
        System.out.println("Borrando 100");
        new impresoraDeArbol<>(arbol).imprime();

        System.out.println("\n\nOtro árbol");
        Arbol<Integer> prueba = new Arbol<>();
        prueba.insertar(3);
        prueba.insertar(2);
        prueba.insertar(6);
        prueba.insertar(7);
        prueba.insertar(5);
        new impresoraDeArbol<>(prueba).imprime();

        System.out.println("\n\n Último árbol");
        Arbol<Integer> prueba2 = new Arbol<>();
        prueba2.insertar(10);
        prueba2.insertar(5);
        prueba2.insertar(3);
        prueba2.insertar(8);
        prueba2.insertar(7);
        prueba2.insertar(40);
        prueba2.insertar(42);
        prueba2.insertar(41);
        prueba2.insertar(100);
        prueba2.insertar(102);
        new impresoraDeArbol<>(prueba2).imprime();
    }
}
