package metodos;

import funcion.*;
import java.math.*;
import org.apache.commons.math3.fraction.*;

public class MetodosComunes {

    public Funcion funcion;

    public boolean fraccion = false;

    public int decimales = 4;
    public int espaciado = 12;

    public void usarFracciones(boolean fraccion) {

        this.fraccion = fraccion;

    }

    protected double getInterpolacion(double xa, double xb) {

        @SuppressWarnings("UnusedAssignment")
        double interpolacion = 0;

        double fxa = funcion.evaluar(xa);
        double fxb = funcion.evaluar(xb);

        interpolacion = xb - ((xb - xa) * fxb) / (fxb - fxa);

        return interpolacion;

    }

    public String redondear(double numero, int ancho, boolean centrado) {

        return this.redondear(numero, this.decimales, ancho, centrado);

    }

    public String redondear(double numero, boolean centrado) {
        
        return this.redondear(numero, this.decimales, this.espaciado, centrado);
    
    }

    public String redondear(double numero) {
    
        return this.redondear(numero, this.decimales, this.espaciado);
    
    }

    public double redondear(double numero, int decimales) {
    
        return Double.parseDouble(this.redondear(numero, decimales, 0));
    
    }

    public String redondear(double numero, int decimales, int ancho) {
    
        return this.redondear(numero, decimales, ancho, false);
    
    }

    public String redondear(double numero, int decimales, int ancho, boolean centrado) {

        @SuppressWarnings("UnusedAssignment")
        String cadena = "";
        
        try {
        
            if (this.fraccion) {
            
                @SuppressWarnings("LocalVariableHidesMemberVariable")
                Fraction fraccion = new Fraction(numero);
                cadena = fraccion.toString();
            
            } else {

                BigDecimal bd = new BigDecimal(numero);
                bd = bd.setScale(decimales, BigDecimal.ROUND_HALF_UP);
                double resultado = bd.doubleValue();
                cadena = resultado + "";

            }
        
        } catch (FractionConversionException e) {
        
            cadena = "0";
        
        }

        return this.redondearString(cadena, ancho, centrado);
    
    }

    public double truncar(double decimal, int numeroDecimales) {
    
        decimal = decimal * (Math.pow(10, numeroDecimales));
        decimal = java.lang.Math.floor(decimal);
        decimal = decimal / Math.pow(10, numeroDecimales);

        return decimal;
    
    }

    public String redondearString(String cadena, int ancho, boolean centrado) {

        @SuppressWarnings("UnusedAssignment")
        String str = "";

        @SuppressWarnings("UnusedAssignment")
        String str2 = "";

        if (cadena.length() < ancho) {

            if (centrado) {

                int repetir = ancho - cadena.length();

                int mitad = Integer.parseInt(repetir / 2 + "");

                str = this.repetir(" ", mitad);
                str2 = this.repetir(" ", repetir - mitad);

                cadena = str2 + cadena + str;

            } else {

                int repetir = ancho - cadena.length();

                str = this.repetir(" ", repetir);
                cadena = cadena + str;

            }

        }

        return cadena;

    }

    public String redondear(String cadena) {

        return this.redondearString(cadena, this.espaciado, false);

    }

    public String redondear(String cadena, boolean centrado) {

        return this.redondearString(cadena, this.espaciado, centrado);

    }

    public String redondear(String cadena, int ancho) {

        return this.redondearString(cadena, ancho, false);

    }

    public String redondear(String cadena, int ancho, boolean centrado) {

        return this.redondearString(cadena, ancho, centrado);

    }

    public String redondearString(String cadena) {

        return this.redondearString(cadena, this.espaciado, false);

    }

    public String redondearString(String cadena, boolean centrado) {

        return this.redondearString(cadena, this.espaciado, centrado);

    }

    public String repetir(String str, int times) {

        String repetir = "";

        for (int i = 0; i < times; i++) {

            repetir = repetir + str;

        }

        return repetir;

    }

    public void consola() {

        System.out.println("No se Implemento la consola para este metodo");

    }

    public int getEspaciado(double[][] matriz) {

        int tamano = 0;

        for (double[] matriz1 : matriz) {
    
            for (int j = 0; j < matriz1.length; j++) {
            
                int redondeado = (this.redondear(matriz1[j])).length();
                
                tamano = redondeado > tamano ? redondeado : tamano;
            
            }
        
        }

        return tamano + 2;
    
    }

    /**
     * reporta coordenadas X,Y
     *
     * @param x vector X
     * @param y vector Y
     */
    public void reportarcoordenadas(double[] x, double[] y) {

        int n = x.length;
        double[][] coordenadas = new double[2][n];

        for (int j = 0; j < n; j++) {

            coordenadas[0][j] = x[j];
            coordenadas[1][j] = y[j];

        }

        int ancho = this.getEspaciado(coordenadas);

        System.out.println("");

        System.out.print("\n");

        for (int i = 0; i < coordenadas.length; i++) {

            if (i == 0) {
    
                System.out.print(this.redondear("X", 5, true));
            
            } else {
            
                System.out.print(this.redondear("f(x)", 5, true));
            
            }

            this.reportarFilacoordenadas(coordenadas[i], ancho);

        }

        System.out.println("");

    }

    /**
     * reporta una fila de coordenadas
     *
     * @param fila vector
     * @param ancho espaciado entre valores
     */
    public void reportarFilacoordenadas(double[] fila, int ancho) {

        System.out.print("[");

        int n = fila.length;

        for (int i = 0; i < n; i++) {

            double numero = fila[i];

            if (i != 0) {

                System.out.print("|" + this.redondear(numero, ancho, true));

            } else {

                System.out.print(this.redondear(numero, ancho, true));

            }

        }

        System.out.print("]");
        System.out.print("\n");

    }

}