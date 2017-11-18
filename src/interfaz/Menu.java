package interfaz;

import java.util.*;
import metodos.MetodosComunes;

public class Menu extends Consola {

    ArrayList<MenuItem> lista = new ArrayList<>();

    private String titulo = "";

    public String getTitulo() {

        return titulo;

    }

    public void setTitulo(String titulo) {

        this.titulo = titulo;

    }

    public Menu() {

        super();

    }

    /**
     * agrega un item al menu
     *
     * @param titulo titulo del item
     * @param callback funcion del item
     */
    public void agregar(String titulo, MenuInterface callback) {

        lista.add(new MenuItem(titulo, callback));

    }

    /**
     * agrega un submenu al menu
     *
     * @param titulo titulo del submenu
     * @param menu menu
     */
    @SuppressWarnings("Convert2Lambda")
    public void agregar(String titulo, final Menu menu) {

        final Menu menuPrincipal = this;

        if (menu.getTitulo().equals("")) {
      
            menu.setTitulo(titulo);
        
        }

        menu.agregar("Regresar", new MenuInterface() {
        
            public void ejecutar() {
            
                menuPrincipal.mostrar();
            }
        
        });

        lista.add(new MenuItem(titulo, new MenuInterface() {

            public void ejecutar() {

                menu.mostrar();

            }

        }).setMenu(true));

    }

    /**
     * muestra el menu
     */
    public void mostrar() {

        this.limpiarPantalla();

        int eleccion = 0;
        
        Scanner in = new Scanner(this.entrada);
        
        MetodosComunes base = new MetodosComunes();

        if (!this.getTitulo().equals("")) {
        
            int ancho_titulo = this.titulo.length();
            
            ancho_titulo = ancho_titulo < 60 ? 60 : ancho_titulo;
            
            System.out.println(base.repetir("-", ancho_titulo));
            System.out.println("-" + base.redondear(this.titulo, ancho_titulo - 2, true) + "-");
            System.out.println(base.repetir("-", ancho_titulo));
        
        }
        
        @SuppressWarnings("UnusedAssignment")
        int i = 0;
        
        for (i = 0; i < lista.size(); ++i) {
        
            MenuItem subItem = lista.get(i);
            
            System.out.printf("[%d] %s \n", i + 1, subItem.getTexto());
        
        }

        System.out.printf("[%d] %s \n", i + 1, "Salir");

        System.out.println();

        System.out.print("Selecciona una Opcion:");

        try {

            eleccion = in.nextInt();

        } catch (Exception exception) {

            System.out.println(exception.getMessage());
        
        }

        if (eleccion == lista.size() + 1) {

            System.out.println("");
            System.out.println(base.repetir("*", 60));
            System.out.println(base.redondear("Adios, que tengas buen dia...", 60, true));
            System.out.println(base.repetir("*", 60));

            System.exit(1);

        } else if (eleccion > lista.size() || eleccion < 1) {

            System.out.println("");
            System.out.println(base.repetir("*", 60));
            System.out.println(base.redondear("Opcion Invalida, Presione Enter Para ir al Menu...", 60, true));

            System.out.println(base.repetir("*", 60));

            in.nextLine();
            in.nextLine();

            this.mostrar();

        } else {

            MenuItem miItem = lista.get(eleccion - 1);
            
            MenuInterface mc = miItem.getMenuCallback();

            this.limpiarPantalla();

            if (!miItem.isMenu() && !miItem.getTexto().equals("Regresar")) {

                System.out.println(base.repetir("-", 60));
                System.out.println(base.redondear(miItem.getTexto(), 60, true));
                System.out.println(base.repetir("-", 60));

                System.out.println("");
            }

            mc.ejecutar();

            System.out.println("");

            System.out.println(base.repetir("*", 60));
            System.out.println(base.redondear("Presione ENTER para ir al menu...", 60, true));
            System.out.println(base.repetir("*", 60));

            in.nextLine();
            in.nextLine();
            this.mostrar();

        }

    }

}