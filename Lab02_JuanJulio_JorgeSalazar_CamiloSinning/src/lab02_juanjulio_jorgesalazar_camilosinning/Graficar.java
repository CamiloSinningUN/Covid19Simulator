package lab02_juanjulio_jorgesalazar_camilosinning;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;

class nodosDibujados {

    int numero;
    int x;
    int y;
    nodosDibujados link;

    public nodosDibujados(int numero, int x, int y) {
        this.numero = numero;
        this.x = x;
        this.y = y;
    }

}

public class Graficar {

    static int[][] matrizAdyacencia = null;
    static nodosDibujados misNodosDibujados = null;

    public static void insertarNodo(int num, int x, int y) {
        System.out.println("Inserte");
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

    public static boolean dibujado(int num) {
        boolean sw;
        nodosDibujados p = misNodosDibujados;
        while ((p != null) && (p.numero != num)) {
            p = p.link;
        }
        sw = !(p == null || p.numero != num);
        return sw;
    }

    public static int posicionX(int num) {
        int x = 0;
        nodosDibujados p = misNodosDibujados;
        while ((p != null) && (p.numero != num)) {
            p = p.link;
        }
        if ((p != null) && (p.numero == num)) {
            x = p.x;
            System.out.println("x: " + x);
        }

        return x;
    }

    public static int posicionY(int num) {
        int y = 0;
        nodosDibujados p = misNodosDibujados;
        while ((p != null) && (p.numero != num)) {
            p = p.link;
        }
        if ((p != null) && (p.numero == num)) {
            y = p.y;
            System.out.println("y: " + y);
        }

        return y;
    }

    public static void GraficarNodo(Graphics g, int diametro, int x, int y, int num) {
        // dibujar circulo
        g.setColor(Color.blue);
        g.drawOval(x - diametro / 2, y - diametro / 2, diametro, diametro);
        // poner texto en el circulo
        FontMetrics fm = g.getFontMetrics();
        double textWidth = fm.getStringBounds(num + "", g).getWidth();
        g.setColor(Color.black);
        g.drawString(num + "", (int) (x - textWidth / 2), (int) (y + fm.getMaxAscent() / 2));
    }

    public static void GraficarPeso(Graphics g, int diametro, int x, int y, int num) {
        // dibujar circulo
        g.setColor(Color.white);
        g.fillOval(x - diametro / 2, y - diametro / 2, diametro, diametro);
        // poner texto en el circulo
        FontMetrics fm = g.getFontMetrics();
        double textWidth = fm.getStringBounds(num + "", g).getWidth();
        g.setColor(Color.red);
        g.drawString(num + "", (int) (x - textWidth / 2), (int) (y + fm.getMaxAscent() / 2));
    }

    public static void DibujarFlecha(Graphics g, int peso, int x0, int y0, int x1, int y1) {
        double alfa = Math.atan2(y1 - y0, x1 - x0);
        g.setColor(Color.black);
        //linea principal
        g.drawLine(x0, y0, x1, y1);
        //flecha
        int k = 8;
        int xa = (int) (x1 - k * Math.cos(alfa + 1));
        int ya = (int) (y1 - k * Math.sin(alfa + 1));
        g.drawLine(xa, ya, x1, y1);
        xa = (int) (x1 - k * Math.cos(alfa - 1));
        ya = (int) (y1 - k * Math.sin(alfa - 1));
        g.drawLine(xa, ya, x1, y1);

        //peso
        g.setColor(Color.red);
        GraficarPeso(g, 15, (x1 - x0) / 3 + x0, (y1 - y0) / 3 + y0 + 5, peso);

    }

    static int[][] Matriz_MatrizAdyacencia(int Matriz[][]) {
        int[][] matrizn = new int[Matriz.length][Matriz.length];
        for (int i = 0; i < Matriz.length; i++) {
            System.arraycopy(Matriz[i], 0, matrizn[i], 0, Matriz.length);
        }
        return matrizn;
    }

    public static void GraficarInicio(Graphics g, int Matriz[][]) {
        //matriz temp
        matrizAdyacencia = Matriz_MatrizAdyacencia(Matriz);

        //particiones
        int EntradaSalida = 0;
        int Entrada = 0;
        int Salida = 0;
        for (int i = 0; i < matrizAdyacencia.length; i++) {
            if ((matrizAdyacencia[0][i] != 0) && (matrizAdyacencia[i][0] != 0)) {
                EntradaSalida++;
            } else if ((matrizAdyacencia[0][i] != 0) && (matrizAdyacencia[i][0] == 0)) {
                Entrada++;
            } else if ((matrizAdyacencia[0][i] == 0) && (matrizAdyacencia[i][0] != 0)) {
                Salida++;
            }
        }
        int Particiones = EntradaSalida + Salida + Entrada; //depende de si es el mismo nodo el de salida y entrada o no

        //dibujar nodo 1     
        int Radio = 40;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = screenSize.width / 2 - 30 - 90;
        int y = screenSize.height / 2 - 130;
        GraficarNodo(g, Radio, x, y, 1);
        insertarNodo(1, x, y);

        //conexiones
        int L = 50;
        double grados = 2 * Math.PI / Particiones;
        double GradoActual = Math.random() * 2 * Math.PI;
        for (int i = 0; i < matrizAdyacencia.length; i++) {
            if ((matrizAdyacencia[0][i] != 0) && (matrizAdyacencia[i][0] != 0)) {
                EntradaSalida(g, x, y, matrizAdyacencia[0][i], matrizAdyacencia[i][0], Radio, i + 1, L, GradoActual);
                matrizAdyacencia[0][i] = 0;
                matrizAdyacencia[i][0] = 0;
                //GradoActual = GradoActual + grados;
                GradoActual = AnguloAleatorio(g,Radio,L,x,y);
            } else if ((matrizAdyacencia[0][i] != 0) && (matrizAdyacencia[i][0] == 0)) {
                Salida(g, x, y, matrizAdyacencia[0][i], Radio, i + 1, L, GradoActual);
                matrizAdyacencia[0][i] = 0;
                //GradoActual = GradoActual + grados;
                GradoActual = AnguloAleatorio(g,Radio,L,x,y);
            } else if ((matrizAdyacencia[0][i] == 0) && (matrizAdyacencia[i][0] != 0)) {
                Entrada(g, x, y, matrizAdyacencia[i][0], Radio, i + 1, L, GradoActual);
                matrizAdyacencia[i][0] = 0;
                //GradoActual = GradoActual + grados;
                GradoActual = AnguloAleatorio(g,Radio,L,x,y);
            }
        }

        // GraficarSobrante(g,Radio);
    }

    public static double AnguloAleatorio(Graphics g,int Radio, int L, int xn, int yn) {
        //con numero grandes no sirve
        boolean sw = true;
        double Grados = 0;
        int i = 0;
        while(sw){
            Grados = Math.random() * 2 * Math.PI;
            
            nodosDibujados p = misNodosDibujados;
            boolean sw1 = true;
            while (p != null) {
                double xf = xn + Radio * Math.cos(Grados) + L * Math.cos(Grados) + Radio * Math.cos(Grados);
                double yf = yn + Radio * Math.sin(Grados) + L * Math.sin(Grados) + Radio * Math.sin(Grados);
                g.setColor(Color.black);
                g.fillOval((int)xf, (int)yf, 5, 5);
                g.setColor(Color.green);
                g.fillOval(p.x, p.y, 8, 8);
                double distancia = Math.sqrt(Math.pow(xf-p.x, 2)+Math.pow(yf-p.y, 2));
                //System.out.println("distancia: "+distancia);
                if(distancia<Radio){
                    sw1 = false;                    
                }
                p = p.link;
                
            }
            if(sw1 == true){
                sw = false;
            } else if(i>100000){
                sw = false;
                System.out.println("imposible");
            }
            i++;
        }
        
        
        return Grados;
    }

    /*public static void GraficarSobrante(Graphics g, int Radio) {
        
        //encontrar grado a grafo que se quiere y L
        for (int i = 1; i < matrizAdyacencia.length; i++) {
            for (int j = 1; j < matrizAdyacencia.length; j++) {
                if ((matrizAdyacencia[i][j] != 0) && (matrizAdyacencia[j][i] != 0)) {
                    if (dibujado(j + 1) && dibujado(i+1)) {
                       
                        double L = Math.sqrt(Math.pow(posicionX(j+1)-posicionX(i+1), 2)+Math.pow(posicionY(i+1)-posicionY(j+1), 2));
                        System.out.println("L:"+L);
                        double theta = Math.acos((posicionX(j+1)-posicionX(i+1))/(L+2*Radio));
                        System.out.println("theta:"+theta);
                        EntradaSalida(g,posicionX(i+1),posicionY(i+1),matrizAdyacencia[j][i],matrizAdyacencia [i][j],Radio,i+1, L,theta);                      
                    } 
                    
                    matrizAdyacencia[i][j] = 0;
                    matrizAdyacencia[j][i] = 0;
                    //GradoActual = GradoActual + grados;
                } else if ((matrizAdyacencia[i][j] != 0) && (matrizAdyacencia[j][i] == 0)) {
                    if (dibujado(j + 1)) {
                        
                    } else {

                    }
                    //Salida(g,x,y,matrizAdyacencia [0][i],Radio,i+1,L,GradoActual);
                    matrizAdyacencia[i][j] = 0;
                    //GradoActual = GradoActual + grados;
                } else if ((matrizAdyacencia[i][j] == 0) && (matrizAdyacencia[j][i] != 0)) {
                    //verificar si el nodo ya existe
                    //Entrada(g,x,y,matrizAdyacencia[i][0],Radio,i+1,L,GradoActual);
                    matrizAdyacencia[j][i] = 0;
                    //GradoActual = GradoActual + grados;
                }
            }
        }

    }*/
    public static void EntradaSalida(Graphics g, int xn, int yn, int peso1, int peso2, int Radio, int nodo, double L, double GradoActual) {
        double xi = xn + Radio * Math.cos(GradoActual);
        double yi = yn + Radio * Math.sin(GradoActual);
        double xf = xi + L * Math.cos(GradoActual);
        double yf = yi + L * Math.sin(GradoActual);

        //flechaSalida
        DibujarFlecha(g, peso1, (int) xi, (int) yi, (int) xf, (int) yf);
        //flecha entrada
        DibujarFlecha(g, peso2, (int) xf, (int) yf, (int) xi, (int) yi);

        //Nodo
        GraficarNodo(g, Radio, (int) (xf + Radio * Math.cos(GradoActual)), (int) (yf + Radio * Math.sin(GradoActual)), nodo);
        insertarNodo(nodo, (int) (xf + Radio * Math.cos(GradoActual)), (int) (yf + Radio * Math.sin(GradoActual)));
    }

    public static void Salida(Graphics g, int xn, int yn, int peso, int Radio, int nodo, int L, double GradoActual) {
        double xi = xn + Radio * Math.cos(GradoActual);
        double yi = yn + Radio * Math.sin(GradoActual);
        double xf = xi + L * Math.cos(GradoActual);
        double yf = yi + L * Math.sin(GradoActual);

        //flechaSalida
        DibujarFlecha(g, peso, (int) xi, (int) yi, (int) xf, (int) yf);

        //Nodo
        GraficarNodo(g, Radio, (int) (xf + Radio * Math.cos(GradoActual)), (int) (yf + Radio * Math.sin(GradoActual)), nodo);
        insertarNodo(nodo, (int) (xf + Radio * Math.cos(GradoActual)), (int) (yf + Radio * Math.sin(GradoActual)));
    }

    public static void Entrada(Graphics g, int xn, int yn, int peso, int Radio, int nodo, int L, double GradoActual) {
        double xi = xn + Radio * Math.cos(GradoActual);
        double yi = yn + Radio * Math.sin(GradoActual);
        double xf = xi + L * Math.cos(GradoActual);
        double yf = yi + L * Math.sin(GradoActual);

        //flecha entrada
        DibujarFlecha(g, peso, (int) xf, (int) yf, (int) xi, (int) yi);

        //Nodo
        GraficarNodo(g, Radio, (int) (xf + Radio * Math.cos(GradoActual)), (int) (yf + Radio * Math.sin(GradoActual)), nodo);
        insertarNodo(nodo, (int) (xf + Radio * Math.cos(GradoActual)), (int) (yf + Radio * Math.sin(GradoActual)));
    }
}
