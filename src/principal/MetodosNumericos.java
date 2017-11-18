package principal;


import interfaz.Menu;
import metodos.*;
import interfaz.MenuInterface;

public class MetodosNumericos {

    @SuppressWarnings("Convert2Lambda")
    public static void main(String[] args) {

        Menu menu = new Menu();

        menu.setTitulo("Metodos Numericos");

        menu.agregar("Interpolacion de Lagrange", new MenuInterface() {
            
            public void ejecutar() {
            
                Lagrange metodo = new Lagrange();
                
                metodo.consola();
            
            }
        
        });
        
        menu.agregar("Interpolacion de Newton", new MenuInterface() {
            
            public void ejecutar() {
            
                Newton metodo = new Newton();
                metodo.consola();
            
            }
        
        });
        
        menu.agregar("Interpolacion de Neville", new MenuInterface() {
        
            public void ejecutar() {
            
                Neville metodo = new Neville();
                metodo.consola();
            
            }
        
        });

        menu.mostrar();

    }

}