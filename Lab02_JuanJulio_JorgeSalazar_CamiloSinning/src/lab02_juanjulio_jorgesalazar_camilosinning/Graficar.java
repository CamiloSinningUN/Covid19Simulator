
package lab02_juanjulio_jorgesalazar_camilosinning;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;

class NodoGrafico{
    int num;
    boolean Dibujado = false;
    int x;
    int y;
}
public class Graficar {
    
     static NodoGrafico[][] matriz = null;
     
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
     
    public static void GraficarPeso(Graphics g, int diametro, int x, int y, int num){
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
        GraficarPeso(g,15,(x1 - x0)/3+x0,(y1 - y0)/3+y0+5,peso);
        
    }

    
    public static NodoGrafico[][] Matriz_Matriz(int Matriz[][]){
        NodoGrafico[][] matrizn = new NodoGrafico[Matriz.length][Matriz.length];
        for (int i = 0; i < Matriz.length; i++) {
            for (int j = 0; j < Matriz.length; j++) {
                NodoGrafico n = new NodoGrafico();
                n.num = Matriz[i][j];
                matrizn[i][j] = n;
            }
            
        }
        return matrizn;
    }
    public static void GraficarInicio(Graphics g, int Matriz[][]) {
        //matriz temp
        matriz = Matriz_Matriz(Matriz);
        System.out.println("Conversion de matriz");
         for (NodoGrafico[] matriz1 : matriz) {
             for (int j = 0; j < matriz.length; j++) {
                 System.out.print(matriz1[j].num);
             }
             System.out.println("");
         }       
        System.out.println("");
        
        int EntradaSalida = 0;
        int Entrada = 0;
        int Salida = 0;
        for (int i = 0; i < Matriz.length; i++) {
            if ((Matriz[0][i] != 0) && (Matriz[i][0] != 0)) {
                EntradaSalida++;
            } else if ((Matriz[0][i] != 0) && (Matriz[i][0] == 0)) {
                Entrada++;
            } else if ((Matriz[0][i] == 0) && (Matriz[i][0] != 0)) {
                Salida++;
            }
        }
        int Particiones = EntradaSalida + Salida + Entrada; //depende de si es el mismo nodo el de salida y entrada o no

        //dibujar nodo 1     
        int Radio = 40;
        int x = 300;//j.getSize().width / 2;
        int y = 300;//j.getSize().height / 2;
        GraficarNodo(g, Radio, x, y, 1);

        //conexiones
        int L = 50;
        double grados = 2 * Math.PI / Particiones;
        double GradoActual = 0;
        for (int i = 0; i < Matriz.length; i++) {
            if ((Matriz[0][i] != 0) && (Matriz[i][0] != 0)) {
                EntradaSalida(g,x,y,Matriz[0][i],Matriz[i][0],Radio,i+1,L,GradoActual);               
                GradoActual = GradoActual + grados;
            } else if ((Matriz[0][i] != 0) && (Matriz[i][0] == 0)) {
                Salida(g,x,y,Matriz[0][i],Radio,i+1,L,GradoActual);
                GradoActual = GradoActual + grados;
            } else if ((Matriz[0][i] == 0) && (Matriz[i][0] != 0)) {
                Entrada(g,x,y,Matriz[i][0],Radio,i+1,L,GradoActual);
                GradoActual = GradoActual + grados;
            }
        }

    }

    public static void EntradaSalida(Graphics g, int xn, int yn, int peso1, int peso2, int Radio, int nodo, int L, double GradoActual) {
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
    }
}
