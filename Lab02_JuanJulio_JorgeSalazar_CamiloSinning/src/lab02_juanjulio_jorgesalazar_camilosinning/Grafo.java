package lab02_juanjulio_jorgesalazar_camilosinning;

import Listas.ListaNodos;
import java.awt.Graphics;

public class Grafo {

    int cantidadNodos;
    int modoGrafo;
    static ListaNodos miListaNodos; //respesentando un grafo como lista
    ListaNodos Infectados;
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
        int[][] Adyacencia = new int[cantidadNodos][cantidadNodos];
        while (i < cantidadNodos) {
            while (j < cantidadNodos) {
                if (i == j) {
                    Adyacencia[i][j] = 0;
                } else {
                    Adyacencia[i][j] = (int) (Math.random() * 2);
                }
                //Un 0 en la matriz significa que no están relacionados y un número entre 1 si existe una arista de i a j
                if (Adyacencia[i][j] == 1) {
                    Adyacencia[i][j] = (int) (Math.random() * 5) + 1;
                }
                j++;
            }
            j = 0;
            i++;
        }
        return Adyacencia;
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
        int i = 0;
        ListaNodos p;
        int infectado;
        miListaNodos = null;

        while (i < cantidadNodos) {
            ListaNodos q;
            if (modoGrafo == 0 || modoGrafo == 1) {
                Persona tempp = new Persona(0, modoGrafo);
                Nodo tempn = new Nodo(i + 1, tempp);
                q = new ListaNodos(tempn);

                //q.mascarilla = mascarilla;
            } else {
                Persona tempp = new Persona(0, (int) (Math.random() * 2));
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
        ListaDeAdyacencia(Matriz);
        infectado = PrimerInfectado(Matriz);
        ActualizaInfectados(g, infectado, Matriz);
    }

    //Función que da al azar el primer infectado
    int PrimerInfectado(int Matriz[][]) {
        int infectado = 0, j = 0, acum = 0;
        boolean sw = false;

        while (sw == false) {
            while (j < cantidadNodos) {
                infectado = (int) (Math.random() * cantidadNodos) + 1;
                acum = Matriz[infectado - 1][j] + acum;
                j++;
            }
            if (acum == 0) {
                j = 0;
            } else {
                sw = true;
            }
        }
        System.out.println(infectado);
        return infectado;
    }

    //Función que actualiza la lista con los infectados
    public void ActualizaInfectados(Graphics g, int infectado, int Matriz[][]) {
        ListaNodos p;

        p = miListaNodos;
        while (p.minodo.id != infectado) {
            p = p.link;
        }
        p.minodo.miPersona.enfermo = 1;
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
                                System.out.println("Me quedo por estúpido");
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
        ListaNodos p, q;
        p = miListaNodos;

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
        /*q = Infectados;
        while (q != null) {
            ActualizaInfectados(g, q.minodo.id, Matriz);
        }*/
//        Graficar.GraficarInicio(g, Matriz);
    }

    //Calcula el próximo contagiado en caso de que lo haya según las probabilidades dadas por el lab
    public void ProximosEnfermos(Graphics g, ListaNodos p, int Matriz[][]) {
        ListaNodos aux;
//        System.out.println(p.minodo.id);
//        System.out.println(p.linkIncidentes.minodo.id);
        if (p.linkIncidentes != null) {
            aux = p.linkIncidentes;

            while (aux != null) {
                while ((aux != null) && (aux.minodo.miPersona.enfermo == 1)) {
                    aux = aux.linkIncidentes;
                }
                if (aux != null) {
                    CalculaProbabilidades(g, p, aux, Matriz);
                    aux = aux.linkIncidentes;
                    /*if (p.link != null) {
                    p = p.link;
                    while (p != null && p.minodo.miPersona.enfermo == 0) {
                        p = p.link;
                    }
                    if (p == null) {
                        //Se terminó la simulación
                        //Se puede crear un JOptionPane o algo
                    } else {
                        ProximosEnfermos(g, p, Matriz);
                    }
                } else {
                    //Se terminó la simulación
                    //Se puede crear un JOptionPane o algo
                }*/
                }
            }
        }
        /*else {
            if (p.link != null) {
                p = p.link;
                while (p != null && p.minodo.miPersona.enfermo == 0) {
                    p = p.link;
                }
                if (p == null) {
                    //Se terminó la simulación
                    //Se puede crear un JOptionPane o algo
                } else {
                    if (p.linkIncidentes != null) {
                        aux = p.linkIncidentes;
                        while (aux != null && aux.minodo.miPersona.enfermo == 1) {
                            aux = aux.linkIncidentes;
                        }
                        if (aux == null) {
                            if (p.link != null) {
                                p = p.link;
                                while (p != null && p.minodo.miPersona.enfermo == 0) {
                                    p = p.link;
                                }
                                if (p == null) {
                                    //Se terminó la simulación
                                    //Se puede crear un JOptionPane o algo
                                } else {
                                    ProximosEnfermos(g, p, Matriz);
                                }
                            } else {
                                //Se terminó la simulación
                                //Se puede crear un JOptionPane o algo
                            }
                        } else {
                            CalculaProbabilidades(g, p, aux, Matriz);
                        }
                    }
                }
            }
        }*/
    }

    ////Calcula mediente la probabilidad definida previamiente si el posible infectado es infectado o no
    public void CalculaProbabilidades(Graphics g, ListaNodos p, ListaNodos aux, int Matriz[][]) {
        System.out.println(aux.minodo.id);
        if ((p.minodo.miPersona.mascarilla == 0) && (aux.minodo.miPersona.mascarilla == 0) && (Matriz[p.minodo.id - 1][aux.minodo.id - 1] > 2)) {
            //ListaNodos q;
            int prob;
            prob = (int) (Math.random() * 100 + 1);
            if (prob <= 80) {
                ActualizaInfectados(g, aux.minodo.id, Matriz);
                /*ListaNodos k = Infectados;
                q = Infectados;
                k.minodo.id = aux.minodo.id;
                if (Infectados == null) {
                    Infectados = k;
                } else {
                    while (q.link != null) {
                        q = q.link;
                    }
                    q.link = k;
                    k.link = null;
                }*/
            }
            /*
            if (aux.linkIncidentes != null) {

                aux = aux.linkIncidentes;
                while (aux != null && aux.minodo.miPersona.enfermo == 1) {
                    aux = aux.linkIncidentes;
                }
                if (aux != null) {
                    CalculaProbabilidades(g, p, aux, Matriz);
                }
            }*/
        } else if ((p.minodo.miPersona.mascarilla == 0) && (aux.minodo.miPersona.mascarilla == 0) && (Matriz[p.minodo.id - 1][aux.minodo.id - 1] <= 2)) {
            //ListaNodos q;
            int prob;
            prob = (int) (Math.random() * 100 + 1);
            if (prob <= 90) {
                ActualizaInfectados(g, aux.minodo.id, Matriz);
                /*ListaNodos k = Infectados;
                q = Infectados;
                k.minodo.id = aux.minodo.id;
                if (Infectados == null) {
                    Infectados = k;
                } else {
                    while (q.link != null) {
                        q = q.link;
                    }
                    q.link = k;
                    k.link = null;
                }*/
                //Se mete en una lista para los nuevos infectados en la iteración actual
            }
            /*
            if (aux.linkIncidentes != null) {
                aux = aux.linkIncidentes;
                while (aux != null && aux.minodo.miPersona.enfermo == 1) {
                    aux = aux.linkIncidentes;
                }
                CalculaProbabilidades(g, p, aux, Matriz);
            }*/
        } else if ((p.minodo.miPersona.mascarilla == 0) && (aux.minodo.miPersona.mascarilla == 1) && (Matriz[p.minodo.id - 1][aux.minodo.id - 1] > 2)) {
            //ListaNodos q;
            int prob;
            prob = (int) (Math.random() * 100 + 1);
            if (prob <= 40) {
                ActualizaInfectados(g, aux.minodo.id, Matriz);
                /*ListaNodos k = Infectados;
                q = Infectados;
                k.minodo.id = aux.minodo.id;
                if (Infectados == null) {
                    Infectados = k;
                } else {
                    while (q.link != null) {
                        q = q.link;
                    }
                    q.link = k;
                    k.link = null;
                }*/
                //Se mete en una lista para los nuevos infectados en la iteración actual
            }/*
            if (aux.linkIncidentes != null) {
                aux = aux.linkIncidentes;
                while (aux != null && aux.minodo.miPersona.enfermo == 1) {
                    aux = aux.linkIncidentes;
                }
                CalculaProbabilidades(g, p, aux, Matriz);
            }*/
        } else if ((p.minodo.miPersona.mascarilla == 0) && (aux.minodo.miPersona.mascarilla == 1) && (Matriz[p.minodo.id - 1][aux.minodo.id - 1] <= 2)) {
            //ListaNodos q;
            int prob;
            prob = (int) (Math.random() * 100 + 1);
            if (prob <= 60) {
                ActualizaInfectados(g, aux.minodo.id, Matriz);
                /*ListaNodos k = Infectados;
                q = Infectados;
                k.minodo.id = aux.minodo.id;
                if (Infectados == null) {
                    Infectados = k;
                } else {
                    while (q.link != null) {
                        q = q.link;
                    }
                    q.link = k;
                    k.link = null;
                }*/
                //Se mete en una lista para los nuevos infectados en la iteración actual
            }/*
            if (aux.linkIncidentes != null) {
                aux = aux.linkIncidentes;
                while (aux != null && aux.minodo.miPersona.enfermo == 1) {
                    aux = aux.linkIncidentes;
                }
                CalculaProbabilidades(g, p, aux, Matriz);
            }*/
        } else if ((p.minodo.miPersona.mascarilla == 1) && (aux.minodo.miPersona.mascarilla == 0) && (Matriz[p.minodo.id - 1][aux.minodo.id - 1] > 2)) {
            //ListaNodos q;
            int prob;
            prob = (int) (Math.random() * 100 + 1);
            if (prob <= 30) {
                ActualizaInfectados(g, aux.minodo.id, Matriz);
                /*ListaNodos k = Infectados;
                q = Infectados;
                k.minodo.id = aux.minodo.id;
                if (Infectados == null) {
                    Infectados = k;
                } else {
                    while (q.link != null) {
                        q = q.link;
                    }
                    q.link = k;
                    k.link = null;
                }*/
                //Se mete en una lista para los nuevos infectados en la iteración actual
            }/*
            if (aux.linkIncidentes != null) {
                aux = aux.linkIncidentes;
                while (aux != null && aux.minodo.miPersona.enfermo == 1) {
                    aux = aux.linkIncidentes;
                }
                CalculaProbabilidades(g, p, aux, Matriz);
            }*/
        } else if ((p.minodo.miPersona.mascarilla == 1) && (aux.minodo.miPersona.mascarilla == 0) && (Matriz[p.minodo.id - 1][aux.minodo.id - 1] <= 2)) {
            //ListaNodos q;
            int prob;
            prob = (int) (Math.random() * 100 + 1);
            if (prob <= 40) {
                ActualizaInfectados(g, aux.minodo.id, Matriz);
                /*ListaNodos k = Infectados;
                q = Infectados;
                k.minodo.id = aux.minodo.id;
                if (Infectados == null) {
                    Infectados = k;
                } else {
                    while (q.link != null) {
                        q = q.link;
                    }
                    q.link = k;
                    k.link = null;
                }*/
                //Se mete en una lista para los nuevos infectados en la iteración actual
            }/*
            if (aux.linkIncidentes != null) {
                aux = aux.linkIncidentes;
                while (aux != null && aux.minodo.miPersona.enfermo == 1) {
                    aux = aux.linkIncidentes;
                }
                CalculaProbabilidades(g, p, aux, Matriz);
            }*/
        } else if ((p.minodo.miPersona.mascarilla == 1) && (aux.minodo.miPersona.mascarilla == 1) && (Matriz[p.minodo.id - 1][aux.minodo.id - 1] > 2)) {
            //ListaNodos q;
            int prob;
            prob = (int) (Math.random() * 100 + 1);
            if (prob <= 20) {
                ActualizaInfectados(g, aux.minodo.id, Matriz);
                /*ListaNodos k = Infectados;
                q = Infectados;
                k.minodo.id = aux.minodo.id;
                if (Infectados == null) {
                    Infectados = k;
                } else {
                    while (q.link != null) {
                        q = q.link;
                    }
                    q.link = k;
                    k.link = null;
                }*/
                //Se mete en una lista para los nuevos infectados en la iteración actual
            }/*
            if (aux.linkIncidentes != null) {
                aux = aux.linkIncidentes;
                while (aux != null && aux.minodo.miPersona.enfermo == 1) {
                    aux = aux.linkIncidentes;
                }
                CalculaProbabilidades(g, p, aux, Matriz);
            }*/
        } else if ((p.minodo.miPersona.mascarilla == 1) && (aux.minodo.miPersona.mascarilla == 1) && (Matriz[p.minodo.id - 1][aux.minodo.id - 1] <= 2)) {
            //ListaNodos q;
            int prob;
            prob = (int) (Math.random() * 100 + 1);
            if (prob <= 30) {
                ActualizaInfectados(g, aux.minodo.id, Matriz);
                /*ListaNodos k = Infectados;
                q = Infectados;
                k.minodo.id = aux.minodo.id;
                if (Infectados == null) {
                    Infectados = k;
                } else {
                    while (q.link != null) {
                        q = q.link;
                    }
                    q.link = k;
                    k.link = null;
                }*/
                //Se mete en una lista para los nuevos infectados en la iteración actual
            }/*
            if (aux.linkIncidentes != null) {
                aux = aux.linkIncidentes;
                while (aux != null && aux.minodo.miPersona.enfermo == 1) {
                    aux = aux.linkIncidentes;
                }
                CalculaProbabilidades(g, p, aux, Matriz);
            }*/
        }
        System.out.println(aux.minodo.id);
    }

}
