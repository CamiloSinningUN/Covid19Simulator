package lab02_juanjulio_jorgesalazar_camilosinning;

import Listas.ListaNodos;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;

class nodosDibujados {

    int numero;
    double x;
    double y;
    nodosDibujados link;

    public nodosDibujados(int numero, double x, double y) {
        this.numero = numero;
        this.x = x;
        this.y = y;
    }

}

public class Graficar {

    static int[][] matrizAdyacencia = null;
    static nodosDibujados misNodosDibujados = null;
    static int Radio = 40;
    static int L;

    //Devuelve el tamaño de la lista de nodos dibujados
    public static int TamañoLista() {
//        System.out.println("TamañoLista");
        nodosDibujados p = misNodosDibujados;
        int i = 0;
        while (p != null) {
            i++;
            p = p.link;
        }
        return i;
    }

    //Inserta nodos a la lista de nodos dibujados
    public static void insertarNodo(int num, double x, double y) {
//        System.out.println("InsertarNodo");
        nodosDibujados p = misNodosDibujados;
        if (p == null) {
            nodosDibujados n = new nodosDibujados(num, x, y);
            misNodosDibujados = n;
        } else {
            while (p.link != null) {
                p = p.link;
            }
            nodosDibujados n = new nodosDibujados(num, x, y);
            p.link = n;
        }
    }

    //Te dice si un nodo ya fue dibujado
    public static boolean dibujado(int num) {
//        System.out.println("dibujado");
        boolean sw = false;
        nodosDibujados p = misNodosDibujados;
        while ((p != null) && (p.numero != num)) {
            p = p.link;
        }
        if ((p != null) && (p.numero == num)) {
            sw = true;
        }
        return sw;
    }

    //te devuelve la posicion en x de un nodo ya dibujado
    public static double posicionX(int num) {
//        System.out.println("posicionX");
        double x = 0;
        nodosDibujados p = misNodosDibujados;
        while ((p != null) && (p.numero != num)) {
            p = p.link;
        }
        if ((p != null) && (p.numero == num)) {
            x = p.x;
        }
        return x;
    }

    //te devuelve la posicion en y de un nodo ya dibujado
    public static double posicionY(int num) {
//        System.out.println("posicionY");
        double y = 0;
        nodosDibujados p = misNodosDibujados;
        while ((p != null) && (p.numero != num)) {
            p = p.link;
        }
        if ((p != null) && (p.numero == num)) {
            y = p.y;
        }

        return y;
    }

    //Grafica un nodo en coordenadas dadas
    public static void GraficarNodo(Graphics g, int diametro, double x, double y, int num) {
//      System.out.println("GraficarNodo");
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.blue);
        if (PersonaEnferma(num)) {
            g.setColor(Color.red);
            g.drawOval((int) x - diametro / 2, (int) y - diametro / 2, diametro, diametro);
        } else {
            g.setColor(Color.blue);
            g.drawOval((int) x - diametro / 2, (int) y - diametro / 2, diametro, diametro);
        }
        FontMetrics fm = g.getFontMetrics();
        double textWidth = fm.getStringBounds(num + "", g).getWidth();
        g.setColor(Color.black);
        g.drawString(num + "", (int) (x - textWidth / 2), (int) (y + fm.getMaxAscent() / 2));
    }

    //te devuelve si la persona esta o no enferma
    public static boolean PersonaEnferma(int num) {
//        System.out.println("PersonaEnferma");
        boolean sw = false;
        ListaNodos p = Grafo.miListaNodos;
        while (p != null) {
            if (p.minodo.id == num) {
                if (p.minodo.miPersona.enfermo == 1) {
                    sw = true;
                }
            }
            p = p.link;
        }
        return sw;
    }

    //Grafica el peso de las aristas en coordenas dadas
    public static void GraficarPeso(Graphics g, int diametro, double x, double y, int num) {
//        System.out.println("GraficarPeso");
        g.setColor(Color.white);
        g.fillOval((int) x - diametro / 2, (int) y - diametro / 2, diametro, diametro);
        FontMetrics fm = g.getFontMetrics();
        double textWidth = fm.getStringBounds(num + "", g).getWidth();
        g.setColor(Color.red);
        g.drawString(num + "", (int) (x - textWidth / 2), (int) y + fm.getMaxAscent() / 2);
    }

    //Grafica flecha con coordenadas dadas
    public static void DibujarFlecha(Graphics g, int peso, double x0, double y0, double x1, double y1) {
//        System.out.println("DibujarFlecha");
        //flecha
        double alfa = Math.atan2(y1 - y0, x1 - x0);
        g.setColor(Color.black);
        g.drawLine((int) x0, (int) y0, (int) x1, (int) y1);
        int k = 8;
        double xa = (x1 - k * Math.cos(alfa + 1));
        double ya = (y1 - k * Math.sin(alfa + 1));
        g.drawLine((int) xa, (int) ya, (int) x1, (int) y1);
        xa = (x1 - k * Math.cos(alfa - 1));
        ya = (y1 - k * Math.sin(alfa - 1));
        g.drawLine((int) xa, (int) ya, (int) x1, (int) y1);

        //peso
        g.setColor(Color.red);
        GraficarPeso(g, 15, (x1 - x0) / 3 + x0, (y1 - y0) / 3 + y0 + 5, peso);

    }

    //convierte la matriz entregada aleatoriamente a una matriz estatcia
    static int[][] Matriz_MatrizAdyacencia(int Matriz[][]) {
//        System.out.println("Matriz_MatrizAdyacencia");
        int[][] matrizn = new int[Matriz.length][Matriz.length];
        for (int i = 0; i < Matriz.length; i++) {
            System.arraycopy(Matriz[i], 0, matrizn[i], 0, Matriz.length);
        }
        return matrizn;
    }

    //Genera una posicion aleatoria
    public static double[] GenerarPosicion() {
//        System.out.println("GenerarPosicion");
        double[] posicion = {0, 0};
        boolean sw = true;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double x = 0;
        double y = 0;
        while (sw) {
            x = (Math.random() * screenSize.height);
            y = (Math.random() * screenSize.width);
            if (RangoPermitido(x, y)) {
                sw = false;
            }
        }
        posicion[0] = x;
        posicion[1] = y;
        return posicion;
    }

    //Grafica nodos adyacentes a 1
    public static void GraficarInicio(Graphics g, int Matriz[][]) {
//        System.out.println("GraficarInicio");
        //Inicializar
        matrizAdyacencia = null;
        matrizAdyacencia = Matriz_MatrizAdyacencia(Matriz);
        misNodosDibujados = null;

        //dibujar nodo 1     
        double[] Posicion = GenerarPosicion();
        double x = Posicion[0];
        double y = Posicion[1];
        GraficarNodo(g, Radio, x, y, 1);
        insertarNodo(1, x, y);

        //conexiones
        double GradoActual = AnguloAleatorio(Posicion[0], Posicion[1]);
        for (int i = 0; i < matrizAdyacencia.length; i++) {
            if ((matrizAdyacencia[0][i] != 0) && (matrizAdyacencia[i][0] != 0)) {
                EntradaSalida(g, x, y, matrizAdyacencia[0][i], matrizAdyacencia[i][0], i + 1, GradoActual);
                matrizAdyacencia[0][i] = 0;
                matrizAdyacencia[i][0] = 0;
                //GradoActual = GradoActual + grados;
                GradoActual = AnguloAleatorio(x, y);
            } else if ((matrizAdyacencia[0][i] != 0) && (matrizAdyacencia[i][0] == 0)) {
                Salida(g, x, y, matrizAdyacencia[0][i], i + 1, GradoActual);
                matrizAdyacencia[0][i] = 0;
                //GradoActual = GradoActual + grados;
                GradoActual = AnguloAleatorio(x, y);
            } else if ((matrizAdyacencia[0][i] == 0) && (matrizAdyacencia[i][0] != 0)) {
                Entrada(g, x, y, matrizAdyacencia[i][0], i + 1, GradoActual);
                matrizAdyacencia[i][0] = 0;
                //GradoActual = GradoActual + grados;
                GradoActual = AnguloAleatorio(x, y);
            }
        }
        ConexionesDibujados(g);
    }

    //Genera el angulo de partida de la flecha
    public static double AnguloAleatorio(double xn, double yn) {
//        System.out.println("AnguloAleatorio");
        boolean sw = true;
        double Grados = 0;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sx = screenSize.width;
        int sy = screenSize.height;
        int i = 0;
        while (sw) {
            Grados = Math.random() * 2 * Math.PI;
            L = (int) (Math.random() * (sx - 60 - 90 * 2 - Radio));
            nodosDibujados p = misNodosDibujados;
            boolean sw1 = true;
            while (p != null) {
                double xf = xn + Radio * Math.cos(Grados) + L * Math.cos(Grados) + Radio * Math.cos(Grados);
                double yf = yn + Radio * Math.sin(Grados) + L * Math.sin(Grados) + Radio * Math.sin(Grados);
                double distancia = Math.sqrt(Math.pow(xf - p.x, 2) + Math.pow(yf - p.y, 2));
                if ((Radio * 4 >= distancia) || !(RangoPermitido(xf, yf))) {
                    sw1 = false;
                }
                p = p.link;
            }
            if (sw1 == true) {
                sw = false;
            } else if (i > 921600) {
                sw = false;
                System.out.println("Imposible");
            }
            i++;
        }
        return Grados;
    }

    //Verifica que la matriz estatica esta llena de ceros
    public static boolean MatrizCeros() {
//        System.out.println("MatrizCeros");
        boolean sw = true;
        for (int[] matrizAdyacencia1 : matrizAdyacencia) {
            for (int j = 0; j < matrizAdyacencia.length; j++) {
                if (matrizAdyacencia1[j] != 0) {
                    sw = false;
                }
            }
        }
        return sw;
    }

    //Dibuja las el resto de nodos y sus conexiones
    public static void ConexionesDibujados(Graphics g) {
//        System.out.println("ConexionesDibujados");
        double GradoActual;
        boolean sw = true;
        while (sw) {
            for (int i = 0; i < matrizAdyacencia.length; i++) {
                for (int j = 0; j < matrizAdyacencia.length; j++) {
                    if ((matrizAdyacencia[i][j] != 0) && (matrizAdyacencia[j][i] != 0)) {
                        if ((dibujado(j + 1)) && (dibujado(i + 1))) {
                            double distancia = +Math.sqrt(Math.pow(posicionX(j + 1) - posicionX(i + 1), 2) + Math.pow(posicionY(i + 1) - posicionY(j + 1), 2));
                            L = (int) (distancia - 2 * Radio);
                            double theta;
                            if (posicionX(i + 1) < posicionX(j + 1)) {
                                theta = Math.asin((-posicionY(i + 1) + posicionY(j + 1)) / (distancia));
                            } else {
                                theta = Math.asin((posicionY(i + 1) - posicionY(j + 1)) / (distancia)) + Math.PI;
                            }
                            FlechaEntradaSalida(g, posicionX(i + 1), posicionY(i + 1), matrizAdyacencia[j][i], matrizAdyacencia[i][j], theta);
                            matrizAdyacencia[i][j] = 0;
                            matrizAdyacencia[j][i] = 0;
                        } else if (dibujado(i + 1)) {
                            L = 150;
                            GradoActual = AnguloAleatorio(posicionX(i + 1), posicionY(i + 1));
                            EntradaSalida(g, posicionX(i + 1), posicionY(i + 1), matrizAdyacencia[j][i], matrizAdyacencia[i][j], j + 1, GradoActual);
                            matrizAdyacencia[i][j] = 0;
                            matrizAdyacencia[j][i] = 0;
                        }

                    } else if ((matrizAdyacencia[i][j] != 0) && (matrizAdyacencia[j][i] == 0)) {
                        if ((dibujado(j + 1)) && (dibujado(i + 1))) {
                            double distancia = +Math.sqrt(Math.pow(posicionX(j + 1) - posicionX(i + 1), 2) + Math.pow(posicionY(i + 1) - posicionY(j + 1), 2));
                            L = (int) (distancia - 2 * Radio);
                            double theta;
                            if (posicionX(i + 1) < posicionX(j + 1)) {
                                theta = Math.asin((-posicionY(i + 1) + posicionY(j + 1)) / (distancia));
                            } else {
                                theta = Math.asin((posicionY(i + 1) - posicionY(j + 1)) / (distancia)) + Math.PI;
                            }
                            FlechaSalida(g, posicionX(i + 1), posicionY(i + 1), matrizAdyacencia[i][j], theta);
                            matrizAdyacencia[i][j] = 0;
                        } else if (dibujado(i + 1)) {
                            L = 150;
                            GradoActual = AnguloAleatorio(posicionX(i + 1), posicionY(i + 1));
                            Salida(g, posicionX(i + 1), posicionY(i + 1), matrizAdyacencia[i][j], j + 1, GradoActual);
                            matrizAdyacencia[i][j] = 0;
                        }

                    } else if ((matrizAdyacencia[i][j] == 0) && (matrizAdyacencia[j][i] != 0)) {
                        if ((dibujado(j + 1)) && (dibujado(i + 1))) {
                            double distancia = +Math.sqrt(Math.pow(posicionX(j + 1) - posicionX(i + 1), 2) + Math.pow(posicionY(i + 1) - posicionY(j + 1), 2));
                            L = (int) (distancia - 2 * Radio);
                            double theta;
                            if (posicionX(i + 1) < posicionX(j + 1)) {
                                theta = Math.asin((-posicionY(i + 1) + posicionY(j + 1)) / (distancia));
                            } else {
                                theta = Math.asin((posicionY(i + 1) - posicionY(j + 1)) / (distancia)) + Math.PI;
                            }
                            FlechaEntrada(g, posicionX(i + 1), posicionY(i + 1), matrizAdyacencia[j][i], theta);
                            matrizAdyacencia[j][i] = 0;
                        } else if (dibujado(i + 1)) {
                            L = 150;
                            GradoActual = AnguloAleatorio(posicionX(i + 1), posicionY(i + 1));
                            Entrada(g, posicionX(i + 1), posicionY(i + 1), matrizAdyacencia[j][i], j + 1, GradoActual);
                            matrizAdyacencia[j][i] = 0;
                        }

                    }
                }
            }
            if (MatrizCeros()) {
                sw = false;
            }
        }
        //Remarcar nodos
        Repintar(g);
    }

    public static void Repintar(Graphics g) {
//        System.out.println("Repintar");
        nodosDibujados p = misNodosDibujados;
        while (p != null) {
            g.setColor(Color.white);
            g.fillOval((int) p.x - Radio / 2, (int) p.y - Radio / 2, Radio, Radio);
            GraficarNodo(g, Radio, p.x, p.y, p.numero);
            p = p.link;
        }
    }

    //Grafica flecha de conexion bidireccional
    public static void FlechaEntradaSalida(Graphics g, double xn, double yn, int peso1, int peso2, double GradoActual) {
//        System.out.println("FlecaEntradaSalida");
        double xi = xn + Radio * Math.cos(GradoActual);
        double yi = yn + Radio * Math.sin(GradoActual);
        double xf = xi + L * Math.cos(GradoActual);
        double yf = yi + L * Math.sin(GradoActual);

        //flechaSalida
        DibujarFlecha(g, peso1, xi, yi, xf, yf);
        //flecha entrada
        DibujarFlecha(g, peso2, xf, yf, xi, yi);
    }

    //Grafica nodo con conexion bidireccional 
    public static void EntradaSalida(Graphics g, double xn, double yn, int peso1, int peso2, int nodo, double GradoActual) {
//        System.out.println("EntradaSalida");
        FlechaEntradaSalida(g, xn, yn, peso1, peso2, GradoActual);
        double xf = xn + Radio * Math.cos(GradoActual) + L * Math.cos(GradoActual) + Radio * Math.cos(GradoActual);
        double yf = yn + Radio * Math.sin(GradoActual) + L * Math.sin(GradoActual) + Radio * Math.sin(GradoActual);

        //Nodo
        GraficarNodo(g, Radio, xf, yf, nodo);
        insertarNodo(nodo, xf, yf);
    }

    //Grafica flecha de conexion de salida
    public static void FlechaSalida(Graphics g, double xn, double yn, int peso1, double GradoActual) {
//        System.out.println("FlechaSalida");
        double xi = xn + Radio * Math.cos(GradoActual);
        double yi = yn + Radio * Math.sin(GradoActual);
        double xf = xi + L * Math.cos(GradoActual);
        double yf = yi + L * Math.sin(GradoActual);

        //flechaSalida
        DibujarFlecha(g, peso1, xi, yi, xf, yf);
    }

    //Grafica nodo con conexion de salida
    public static void Salida(Graphics g, double xn, double yn, int peso, int nodo, double GradoActual) {
//        System.out.println("Salida");
        FlechaSalida(g, xn, yn, peso, GradoActual);
        double xf = xn + Radio * Math.cos(GradoActual) + L * Math.cos(GradoActual) + Radio * Math.cos(GradoActual);
        double yf = yn + Radio * Math.sin(GradoActual) + L * Math.sin(GradoActual) + Radio * Math.sin(GradoActual);

        //Nodo
        GraficarNodo(g, Radio, xf, yf, nodo);
        insertarNodo(nodo, xf, yf);
    }

    //Grafica flecha de conexion de Entrada
    public static void FlechaEntrada(Graphics g, double xn, double yn, int peso1, double GradoActual) {
//        System.out.println("FlechaEntrada");
        double xi = xn + Radio * Math.cos(GradoActual);
        double yi = yn + Radio * Math.sin(GradoActual);
        double xf = xi + L * Math.cos(GradoActual);
        double yf = yi + L * Math.sin(GradoActual);

        //flecha entrada
        DibujarFlecha(g, peso1, xf, yf, xi, yi);
    }

    //Grafica nodo con conexion de Entrada
    public static void Entrada(Graphics g, double xn, double yn, int peso, int nodo, double GradoActual) {
//       System.out.println("Entrada");
        FlechaEntrada(g, xn, yn, peso, GradoActual);
        double xf = xn + Radio * Math.cos(GradoActual) + L * Math.cos(GradoActual) + Radio * Math.cos(GradoActual);
        double yf = yn + Radio * Math.sin(GradoActual) + L * Math.sin(GradoActual) + Radio * Math.sin(GradoActual);

        //Nodo
        GraficarNodo(g, Radio, xf, yf, nodo);
        insertarNodo(nodo, xf, yf);
    }

    //Devuelve si la posicion dada esta en el rango permitido
    public static boolean RangoPermitido(double x, double y) {
//        System.out.println("RangoPermitido");
        boolean sw = true;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sx = screenSize.width;
        int sy = screenSize.height;
        if ((x < 0 + Radio) || (y < 0 + Radio) || (y > sy - 50 - 130 - Radio) || (x > sx - 60 - 90 * 2 - Radio)) {
            sw = false;
        }
        return sw;
    }
}
