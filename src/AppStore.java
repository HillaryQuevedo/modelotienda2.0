package Arreglo_Tienda.modelo;

public class AppStore {
    private static final int MAX = 4;
    private Juego juegos[];
    private int indice;

    public AppStore(){
        juegos = new Juego[MAX];
        indice = -1;
    }
    public void adicionarDatos(){

        agregarJuego(new Juego("Fifa2026","Deportes",15,15,50));
        agregarJuego(new Juego("Roblox","Acción",50,35,30));
    }

    public Juego[] getJuegos() {
        return juegos;
    }

    public int getIndice() {
        return indice;
    }
    /* El set no se utiliza porque los datos se ingresan a demanda
    public void setJuegos(Juego juegos[]) {
        this.juegos = juegos;
    }
    */

    public boolean agregarJuego(Juego j){
        if(j != null && indice < MAX - 1){
            juegos[++indice] = j;
            return true;
        }
        return false;
    }
   /* public void agregarJuegoEx(Juego j){
        if(j != null && indice < MAX - 1){
            juegos[++indice] = j;
            return;
        }
        throw new ArrayIndexOutOfBoundsException("No existe espacio");
    }*/

    public Juego buscarJuego(String nombre){
        if(nombre != null && !nombre.trim().isEmpty()){
            for(int i = 0; i <= indice; i++){
                if(juegos[i].getNombre().equalsIgnoreCase(nombre)){
                    return juegos[i];
                }
            }
        }
        return null;
    }
    public boolean comprar(String nombre, int cantidad){
        if(nombre != null && !nombre.trim().isEmpty() && cantidad > 0){
            Juego j = buscarJuego(nombre);
            if(j != null){
                return j.comprar(cantidad);
            }
        }
        return false;
    }

    public double vender(String nombre, int cantidad){
        //no voy a comprobar los parámetros pq el buscar ya lo hace y el
        //vender de juego tbn
        Juego j = buscarJuego(nombre);
        if(j != null){
            if (j.vender(cantidad)) {

                return j.getPrecio() * cantidad;
            }
        }
        return 0;
    }
    public String masVendido(){
        String nombre = "";
        int mayor;
        if(indice >= 0){
            nombre = juegos[0].getNombre();
            mayor = juegos[0].getLicenciasVendidas();
            for(Juego j : juegos){
                if(j != null){
                    if(j.getLicenciasVendidas() > mayor){
                        nombre = j.getNombre();
                        mayor = j.getLicenciasVendidas();
                    }
                }else{
                    return nombre;
                }
            }

        }
        return nombre;
    }

}