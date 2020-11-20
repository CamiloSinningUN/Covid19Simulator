package lab02_juanjulio_jorgesalazar_camilosinning;

import Listas.ListaNodos;
import java.awt.Graphics;
import javax.swing.JLabel;

public class Grafo {

    int cantidadNodos;
    int modoGrafo;
    static ListaNodos miListaNodos; //respesentando un grafo como lista
    ListaNodos Infectados;
    ListaNodos MePuedenInfectar;
    static boolean sw = true;
    int[][] Adyacencia = null;

    public Grafo() {
        this.modoGrafo = -1;
        this.cantidadNodos = -1;
        Grafo.miListaNodos = null;
    }

    //Crea el grafo a partir de una matriz y una lista de adyacencia
    public void InicioGrafo(Graphics g) {
        boolean sw1 = true;
        //int[][] Adyacencia = null;

        while (sw1) {
            Adyacencia = MatrizAdyacencia();

            System.out.println("");
            System.out.println("Matriz de adyacencia:");
            for (int[] Adyacencia1 : Adyacencia) {
                for (int j = 0; j < Adyacencia.length; j++) {
                    System.out.print(Adyacencia1[j]);
                }
                System.out.println("");
            }
            if (!ConNodosAislados(Adyacencia)) {

                sw1 = false;
            }
        }

        GrafoComoLista(g, Adyacencia);
    }

    //genera la matriz de adyacencia de manera aleatoria
    public int[][] MatrizAdyacencia() {
        int i = 0, j = 0;
        int[][] Adyacencia1 = new int[cantidadNodos][cantidadNodos];
        while (i < cantidadNodos) {
            while (j < cantidadNodos) {
                if (i == j) {
                    Adyacencia1[i][j] = 0;
                } else {
                    Adyacencia1[i][j] = (int) (Math.random() * 2);
                }
                //Un 0 en la matriz significa que no están relacionados y un número entre 1 si existe una arista de i a j
                if (Adyacencia1[i][j] == 1) {
                    Adyacencia1[i][j] = (int) (Math.random() * 5) + 1;
                }
                j++;
            }
            j = 0;
            i++;
        }
        return Adyacencia1;
    }

    //Verifica que no haya nodos aislados
    boolean ConNodosAislados(int Matriz[][]) {
        boolean Aislados = false;
        int i = 0, j = 0, aux = 0, acum = 0, acum2 = 0;

        while (i < cantidadNodos) {
            while (j < cantidadNodos) {
                acum = Matriz[i][j] + acum;
                j++;
            }
            if (acum == 0) {
                while (aux < cantidadNodos) {
                    acum2 = Matriz[aux][i] + acum2;
                    aux++;
                }
                if (acum2 == 0) {
                    Aislados = true;
                    return Aislados;
                }
                Aislados = true;
                return Aislados;
            }
            j = 0;
            i++;
            acum = 0;
            acum2 = 0;
            aux = 0;
        }
        return Aislados;
    }

    //Crea una lista donde cada nodo tendrá las características de los nodos del grafo
    public void GrafoComoLista(Graphics g, int Matriz[][]) {
        int i = 0, eso = 0;
        ListaNodos p;
        int infectado;
        miListaNodos = null;

        while (i < cantidadNodos) {
            ListaNodos q;
            if ((modoGrafo == 0) || (modoGrafo == 1)) {
                Persona tempp = new Persona(0, modoGrafo);
                Nodo tempn = new Nodo(i + 1, tempp);
                q = new ListaNodos(tempn);

                //q.mascarilla = mascarilla;
            } else {
                eso = (int) (Math.random() * 2);
                System.out.println("soy eso "+ eso);
                Persona tempp = new Persona(0, eso);
                Nodo tempn = new Nodo(i + 1, tempp);
                q = new ListaNodos(tempn);
                //q.mascarilla = (int) (Math.random() * 2);
            }
            
            q.linkIncidentes = null;
            //1 significa que la persona está contagiada
            //0 significa que la persona no está contagiada
            if (miListaNodos == null) {
                miListaNodos = q;
            } else {
                p = miListaNodos;
                while (p.link != null) {
                    p = p.link;
                }
                p.link = q;
                q.link = null;
            }
            i++;
        }
        System.out.println("Grafo como lista");
        ListaNodos aux;
        aux = miListaNodos;
        while (aux != null) {
            System.out.println(aux.minodo.id);
            aux = aux.link;
        }
        System.out.println("Listo el grafo como lista");     
        modoGrafo = 1;        
        ListaDeAdyacencia(Matriz);
        infectado = PrimerInfectado(Matriz);       
        ActualizaInfectados(g, infectado, Matriz);
        
    }

    //Función que da al azar el primer infectado
    int PrimerInfectado(int Matriz[][]) {
        int infectado = 0, j = 0, acum = 0;
        boolean sw1 = false;

        while (!sw1) {
            infectado = (int) (Math.random() * cantidadNodos) + 1;
            while (j < cantidadNodos) {
                acum = Matriz[infectado - 1][j] + acum;
                j++;
            }
            if (acum == 0) {
                j = 0;
            } else {
                sw1 = true;
            }
        }
        System.out.println(infectado);
        return infectado;
    }

    //Función que actualiza la lista con los infectados
    public void ActualizaInfectados(Graphics g, int infectado, int Matriz[][]) {
        ListaNodos p;

        System.out.println("entro afuera");
        p = miListaNodos;
        while ((p != null) && (p.minodo.id != infectado)) {
            p = p.link;
        }
        if ((p != null) && (p.minodo.id == infectado)) {
            System.out.println("entro");
            p.minodo.miPersona.enfermo = 1;
        }
        if (sw == true) {
            System.out.println("vo a dibujar");
            sw = false;
            Graficar.GraficarInicio(g, Matriz);
        }
    }

    //Crea una multilista con los grafos y sus conexiones a partir de la lista ya creada
    public void ListaDeAdyacencia(int Matriz[][]) {
        ListaNodos p, aux1;
        int i = 0, j = 0;

        p = miListaNodos;
        //p.linkIncidentes = null;
        while (i < cantidadNodos) {
            System.out.println("Entro a while a 1");
            while (j < cantidadNodos) {
                System.out.println("Entro al while 2");
                if (Matriz[i][j] > 0) {
                    System.out.println("Entro al if");
                    Persona tempp = new Persona(0, modoGrafo);
                    System.out.println("Creé a la persona");
                    Nodo tempn = new Nodo(j + 1, tempp);
                    System.out.println("Creé al nodo");
                    ListaNodos q = new ListaNodos(tempn);
                    System.out.println("Creé al nodo pa la lista");
                    while ((p != null) && (p.minodo.id != (i + 1))) {
                        p = p.link;
                        System.out.println("Busco el nodo");
                    }
                    if ((p != null) && (p.linkIncidentes == null)) {
                        p.linkIncidentes = q;
                        q.linkIncidentes = null;
                        System.out.println("Asigno Incidentes");
                    } else {
                        System.out.println("Entré al else");
                        if ((p != null) && (p.linkIncidentes != null)) {
                            aux1 = p.linkIncidentes;
                            while (aux1.linkIncidentes != null) {                              
                                aux1 = aux1.linkIncidentes;
                            }
                            aux1.linkIncidentes = q;
                            q.linkIncidentes = null;
                        }
                        System.out.println("Asigno Incidentes pero en el else");
                    }
                }
                j++;
            }
            j = 0;
            i++;
        }
        String infectados;
        ListaNodos q, aux;
        q = miListaNodos;
        System.out.println("Escribiré la multilista");
        while (q != null) {
            infectados = q.minodo.id + "";
            if (q.linkIncidentes != null) {
                aux = q.linkIncidentes;
                while (aux != null) {
                    infectados = infectados + (aux.minodo.id + "");
                    aux = aux.linkIncidentes;
                }
                System.out.println(infectados);
            }
            q = q.link;
        }
        System.out.println("Multilista escrita (el primero es el nodo principal y del segundo en adelante son los que puede infectar)");
    }

    //Se encarga de generar las iteraciones en simulador y actualizar 
    public void Iteracion(Graphics g, int Matriz[][]) {
        System.out.println("hola");
        ListaNodos p, q, t;
        p = miListaNodos;
        System.out.println("p: "+p.minodo.id);
        while (p != null) {
            while ((p != null) && (p.minodo.miPersona.enfermo == 0)) {
                p = p.link;
            }
            if (p != null) {
                ProximosEnfermos(g, p, Matriz);
            }
            if ((p != null) && (p.link != null)) {
                p = p.link;
            } else {
                p = null;
            }
        }
        System.out.println("Llego al final");
        q = Infectados;
        while (q != null) {
            System.out.println("entro aja");
            ActualizaInfectados(g, q.minodo.id, Matriz);
            q = q.link;

        }
    }

    //Calcula el próximo contagiado en caso de que lo haya según las probabilidades dadas por el lab
    public void ProximosEnfermos(Graphics g, ListaNodos p, int Matriz[][]) {
        ListaNodos aux;

        if (p.linkIncidentes != null) {
            aux = p.linkIncidentes;

            while (aux != null) {
                while ((aux != null) && (aux.minodo.miPersona.enfermo == 1)) {
                    aux = aux.linkIncidentes;
                }
                if (aux != null) {
                    CalculaProbabilidades(g, p, aux, Matriz);
                    aux = aux.linkIncidentes;
                }
            }
        }
    }

    ////Calcula mediante la probabilidad definida previamiente si el posible infectado es infectado o no
    public void CalculaProbabilidades(Graphics g, ListaNodos p, ListaNodos aux, int Matriz[][]) {
        System.out.println("entro probabilidades"+aux.minodo.id);
        if ((p.minodo.miPersona.mascarilla == 0) && (aux.minodo.miPersona.mascarilla == 0) && (Matriz[p.minodo.id - 1][aux.minodo.id - 1] > 2)) {
            ListaNodos q, t;
            int prob;
            prob = (int) (Math.random() * 100 + 1);
            if (prob <= 80) {
                t = aux;
                q = Infectados;
                if (q != null) {
                    while (q.link != null) {
                        q = q.link;
                    }
                    q.link = t;
                    t.link = null;
                    //ActualizaInfectados(g, aux.minodo.id, Matriz);
                } else {
                    Infectados = t;
                }
                //ActualizaInfectados(g, aux.minodo.id, Matriz);
            }
        } else if ((p.minodo.miPersona.mascarilla == 0) && (aux.minodo.miPersona.mascarilla == 0) && (Matriz[p.minodo.id - 1][aux.minodo.id - 1] <= 2)) {
            ListaNodos q, t;
            int prob;
            prob = (int) (Math.random() * 100 + 1);
            if (prob <= 90) {
                t = aux;
                q = Infectados;
                if (q != null) {
                    while ((q != null) && (q.link != null)) {
                        q = q.link;
                    }
                    if (q != null) {
                        q.link = t;
                        t.link = null;
                    }
                    //ActualizaInfectados(g, aux.minodo.id, Matriz);
                } else {
                    Infectados = t;
                }
                //ActualizaInfectados(g, aux.minodo.id, Matriz);
            }
        } else if ((p.minodo.miPersona.mascarilla == 0) && (aux.minodo.miPersona.mascarilla == 1) && (Matriz[p.minodo.id - 1][aux.minodo.id - 1] > 2)) {
            ListaNodos q, t;
            int prob;
            prob = (int) (Math.random() * 100 + 1);
            if (prob <= 40) {
                t = aux;
                q = Infectados;
                if (q != null) {
                    while ((q != null) && (q.link != null)) {
                        q = q.link;
                    }
                    if (q != null) {
                        q.link = t;
                        t.link = null;
                    }
                    //ActualizaInfectados(g, aux.minodo.id, Matriz);
                } else {
                    Infectados = t;
                }
                //ActualizaInfectados(g, aux.minodo.id, Matriz);
            }
        } else if ((p.minodo.miPersona.mascarilla == 0) && (aux.minodo.miPersona.mascarilla == 1) && (Matriz[p.minodo.id - 1][aux.minodo.id - 1] <= 2)) {
            ListaNodos q, t;
            int prob;
            prob = (int) (Math.random() * 100 + 1);
            if (prob <= 60) {
                t = aux;
                q = Infectados;
                if (q != null) {
                    while ((q != null) && (q.link != null)) {
                        q = q.link;
                    }
                    if (q != null) {
                        q.link = t;
                        t.link = null;
                    }
                    //ActualizaInfectados(g, aux.minodo.id, Matriz);
                } else {
                    Infectados = t;
                }
                //ActualizaInfectados(g, aux.minodo.id, Matriz);
            }
        } else if ((p.minodo.miPersona.mascarilla == 1) && (aux.minodo.miPersona.mascarilla == 0) && (Matriz[p.minodo.id - 1][aux.minodo.id - 1] > 2)) {
            ListaNodos q, t;
            int prob;
            prob = (int) (Math.random() * 100 + 1);
            if (prob <= 30) {
                t = aux;
                q = Infectados;
                if (q != null) {
                    while ((q != null) && (q.link != null)) {
                        q = q.link;
                    }
                    if (q != null) {
                        q.link = t;
                        t.link = null;
                    }
                    //ActualizaInfectados(g, aux.minodo.id, Matriz);
                } else {
                    Infectados = t;
                }
                //ActualizaInfectados(g, aux.minodo.id, Matriz);
            }
        } else if ((p.minodo.miPersona.mascarilla == 1) && (aux.minodo.miPersona.mascarilla == 0) && (Matriz[p.minodo.id - 1][aux.minodo.id - 1] <= 2)) {
            ListaNodos q, t;
            int prob;
            prob = (int) (Math.random() * 100 + 1);
            if (prob <= 40) {
                t = aux;
                q = Infectados;
                if (q != null) {
                    while ((q != null) && (q.link != null)) {
                        q = q.link;
                    }
                    if (q != null) {
                        q.link = t;
                        t.link = null;
                    }
                    //ActualizaInfectados(g, aux.minodo.id, Matriz);
                } else {
                    Infectados = t;
                }
                //ActualizaInfectados(g, aux.minodo.id, Matriz);
            }
        } else if ((p.minodo.miPersona.mascarilla == 1) && (aux.minodo.miPersona.mascarilla == 1) && (Matriz[p.minodo.id - 1][aux.minodo.id - 1] > 2)) {
            ListaNodos q, t;
            int prob;
            prob = (int) (Math.random() * 100 + 1);
            if (prob <= 20) {
                t = aux;
                q = Infectados;
                if (q != null) {
                    while ((q != null) && (q.link != null)) {
                        q = q.link;
                    }
                    if (q != null) {
                        q.link = t;
                        t.link = null;
                    }
                    //ActualizaInfectados(g, aux.minodo.id, Matriz);
                } else {
                    Infectados = t;
                }
                //ActualizaInfectados(g, aux.minodo.id, Matriz);
            }
        } else if ((p.minodo.miPersona.mascarilla == 1) && (aux.minodo.miPersona.mascarilla == 1) && (Matriz[p.minodo.id - 1][aux.minodo.id - 1] <= 2)) {
            ListaNodos q, t;
            int prob;
            prob = (int) (Math.random() * 100 + 1);
            if (prob <= 30) {
                t = aux;
                q = Infectados;
                if (q != null) {
                    while ((q != null) && (q.link != null)) {
                        q = q.link;
                    }
                    if (q != null) {
                        q.link = t;
                        t.link = null;
                    }
                    //ActualizaInfectados(g, aux.minodo.id, Matriz);
                } else {
                    Infectados = t;
                }
            }
        }
        System.out.println(aux.minodo.id);
    }

    public boolean TieneMascarilla(int num) {
        boolean sw2 = false;
        ListaNodos p = miListaNodos;
        while (p != null) {
            if (p.minodo.id == num) {
                if (p.minodo.miPersona.mascarilla == 1) {
                    sw2 = true;
                }
            }
            p = p.link;
        }
        return sw2;
    }

    public void CaminoMasPeligroso(int n) {
        nodosDibujados p = Graficar.misNodosDibujados;
        ni = n;
        nodom = 0;
        caminomenor = "";
        m = Integer.MAX_VALUE;
        while (p != null) {
            if (Graficar.PersonaEnferma(p.numero)) {
                dijkstra(Adyacencia, p.numero - 1);
            }
            p = p.link;
        }

    }

    static int ni;
    static final int noAdyacentes = -1;
    static int nodom;
    static String caminomenor;
    static int m = Integer.MAX_VALUE;

    void dijkstra(int[][] adjacencia, int vi) {
        int nVertices = adjacencia[0].length;

        int[] distanciasCortas = new int[nVertices];

        boolean[] arreglo = new boolean[nVertices];

        for (int Index = 0; Index < nVertices; Index++) {
            distanciasCortas[Index] = Integer.MAX_VALUE;
            arreglo[Index] = false;
        }

        distanciasCortas[vi] = 0;

        int[] parents = new int[nVertices];

        parents[vi] = noAdyacentes;

        for (int i = 1; i < nVertices; i++) {

            int nearest = -1;
            int shortestDistance = Integer.MAX_VALUE;
            for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
                if (!arreglo[vertexIndex] && distanciasCortas[vertexIndex] < shortestDistance) {
                    nearest = vertexIndex;
                    shortestDistance = distanciasCortas[vertexIndex];
                }
            }

            arreglo[nearest] = true;

            for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
                int edgeDistance = adjacencia[nearest][vertexIndex];

                if (edgeDistance > 0 && ((shortestDistance + edgeDistance) < distanciasCortas[vertexIndex])) {
                    parents[vertexIndex] = nearest;
                    distanciasCortas[vertexIndex] = shortestDistance
                            + edgeDistance;
                }
            }
        }
        if (sw1) {
            print(vi, distanciasCortas, parents);
        } else {
            print2(vi, distanciasCortas, parents);
        }

    }

    static boolean sw1 = true;
    static int proxNodo;

    //Próximo a infectar del grafo
    void print2(int startVertex, int[] distances, int[] parents) {
        int nVertices = distances.length;
        System.out.print("Vertex\t Distance\tPath2");
        int menor = Integer.MAX_VALUE;
        for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
            if ((vertexIndex != startVertex)) {

                System.out.print("\n" + startVertex + " -> ");
                System.out.print(vertexIndex + " \t\t ");
                System.out.print(distances[vertexIndex] + "\t\t");

                if ((distances[vertexIndex] < menor) && !(Graficar.PersonaEnferma(vertexIndex + 1))) {
                    System.out.println("entre");
                    menor = distances[vertexIndex];
                    proxNodo = vertexIndex + 1;
                    System.out.println("aja" + proxNodo);
                }

            }
        }

    }

    //Camino de mayor probabilidad de infección
    void print(int startVertex, int[] distances, int[] parents) {
        int nVertices = distances.length;
        System.out.print("Vertex\t Distance\tPath");

        for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
            if ((vertexIndex != startVertex)) {

                System.out.print("\n" + startVertex + " -> ");
                System.out.print(vertexIndex + " \t\t ");
                System.out.print(distances[vertexIndex] + "\t\t");

                if (vertexIndex == ni) {
                    if (distances[vertexIndex] < m) {
                        m = distances[vertexIndex];
                        nodom = vertexIndex + 1;
                        caminomenor = "";
                        printPath(vertexIndex, parents);

                    }
                }

            }
        }

    }

    void printPath(int currentVertex, int[] parents) {
        if (currentVertex == noAdyacentes) {
            return;
        }
        printPath(parents[currentVertex], parents);
        caminomenor = caminomenor + (currentVertex + 1) + " ";
    }

    void proxInfectar(int n) {
        ni = n;
        nodom = 0;
        caminomenor = "";
        m = Integer.MAX_VALUE;
        dijkstra(Adyacencia, n - 1);

    }

}
