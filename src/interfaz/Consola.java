package interfaz;

import funcion.Funcion;
import java.awt.Robot;
import java.io.*;

public class Consola {

    InputStream entrada;

    public Consola() {

        this.entrada = System.in;

    }

    /**
     * retorna un numero entero que se ingresara por consola
     *
     * @return numero entero
     */
    public int getEntero() {

        return this.getEntero("");

    }

    /**
     * retorna un numero entero que se ingresara por consola
     *
     * @param mensaje mensaje para mostrar al usuario en consola
     * @return numero entero
     */
    public int getEntero(String mensaje) {

        return this.getEntero(mensaje, 0);

    }

    /**
     * retorna un numero entero que se ingresara por consola
     *
     * @param mensaje mensaje para mostrar al usuario en consola
     * @param defecto valor por defecto en caso no ingresara un valor valido
     * @return numero entero
     */
    public int getEntero(String mensaje, int defecto) {

        mensaje = mensaje.equals("") ? mensaje : mensaje + " [" + defecto + "]";
        
        int numero = (int) this.getNumero(mensaje, defecto);

        return numero;
    
    }

    /**
     * retorna un numero que se ingresara por consola
     *
     * @return numero entero
     */
    public double getNumero() {
    
        return this.getNumero("");
    
    }

    /**
     * retorna true o false al ingresar por consola SI o NO respectivamente
     *
     * @param mensaje mensaje para mostrar al usuario en consola
     * @return true/false
     */
    public boolean getBoolean(String mensaje) {

        boolean opcion;

        mensaje = mensaje.equals("") ? mensaje : mensaje + " SI/NO [NO]";

        String respuesta = this.getCadena(mensaje);

        opcion = respuesta.equalsIgnoreCase("SI");

        return opcion;

    }

    /**
     * retorna un numero que se ingresara por consola
     *
     * @param mensaje mensaje para mostrar al usuario en consola
     * @return numero
     */
    public double getNumero(String mensaje) {

        return this.getNumero(mensaje, 0);

    }

    /**
     * retorna un numero que se ingresara por consola
     *
     * @param mensaje mensaje para mostrar al usuario en consola
     * @param defecto valor por defecto en caso sea invalido el valor ingresado
     * @return numero
     */
    @SuppressWarnings("UseSpecificCatch")
    public double getNumero(String mensaje, double defecto) {

        double numero;

        try {

            Funcion funcion = new Funcion(this.getCadena(mensaje), false);
            numero = funcion.evaluar(true);

        } catch (Exception exception) {

            if (defecto != 0) {

                return defecto;

            } else {

                System.out.println("**Error: Valor Incorrecto, Intente Nuevamente**");
                System.out.println("");
                return this.getNumero(mensaje, defecto);

            }

        }

        return numero;

    }

    /**
     * retorna una cadena que se ingresara por consola
     *
     * @return cadena
     */
    public String getCadena() {

        return this.getCadena("");

    }

    /**
     * retorna una cadena que se ingresara por consola
     *
     * @param mensaje mensaje para mostrar al usuario en consola
     * @return cadena
     */
    public String getCadena(String mensaje) {

        String cadena = "";

        if (!"".equals(mensaje)) {

            System.out.print(mensaje + ": ");

        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {

            cadena = br.readLine();

        } catch (IOException exception) {
   
            System.out.println(exception.getMessage());

        }

        return cadena;

    }

    /**
     * retorna una matriz con coordenadas X,Y
     *
     * @param cantidad cantidad de coordenadas
     * @return matriz
     */
    public double[][] getCoordenadas(int cantidad) {

        System.out.println("");

        double[][] matriz = new double[2][cantidad];

        for (int i = 0; i < cantidad; i++) {

            System.out.println("Los Los Datos para I[" + i + "]:");

            for (int j = 0; j < 2; j++) {

                System.out.print("I[" + i + "][" + ((j == 0) ? "X" : "Y") + "]:");
                matriz[j][i] = this.getNumero();

            }

            System.out.println("");

        }

        return matriz;

    }

   /**
     * limpia la pantalla
     *
     */
    @SuppressWarnings("UseSpecificCatch")
    public void limpiarPantalla() {

        try {
            
            Robot robot = new Robot();
            robot.keyPress(17);
            robot.keyPress(76);
            robot.keyRelease(17);
            robot.keyRelease(76);

            Thread.sleep(100);

        } catch (Exception exception) {
     
            System.out.println(exception.getMessage());

        }

    }

}
