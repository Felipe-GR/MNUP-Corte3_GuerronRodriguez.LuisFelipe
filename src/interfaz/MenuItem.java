package interfaz;

public class MenuItem {

    private MenuInterface funcion;

    private String texto;

    private boolean menu = false;

    public boolean isMenu() {
        
        return menu;
    
    }

    public MenuItem setMenu(boolean menu) {
    
        this.menu = menu;
        
        return this;
    
    }

   /**
    * agrega un item al menu
    * 
    * @param texto texto del menu
    * @param funcion funcion del menu
    */
    public MenuItem(String texto, MenuInterface funcion) {

        this.funcion = funcion;
        this.texto = texto;

    }

    
    public MenuInterface getMenuCallback() {

        return this.funcion;

    }

    public String getTexto() {

        return this.texto;

    }
    
}