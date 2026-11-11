package Arreglo_Tienda.modelo;

import javax.swing.*;
import java.util.Scanner;

public class MainTienda {
    private static Scanner sc = new Scanner(System.in);
    private static  AppStore tienda = new AppStore();

    public static void main(String[] args){
        int opc;
        tienda.adicionarDatos();
        do{
            menu();
            opc = Integer.parseInt(sc.nextLine());
            switch (opc){
                case 1:{
                    ingresarJuegos();

                }break;
                case 2:{
                    mostrarInformacion();

                }break;
                case 3:{
                    buscarJuego();
                }break;
                case 4:{
                    comprar();
                }break;
                case 5:{
                    vender();
                }break;
                case 6:{
                    masVendido();
                }break;
                case 7:{
                    System.out.print("Gracias por usar nuestro servicio");
                }break;
                default:
                    System.out.print("No es una opcion valida");
            }

        }while(opc != 7);
    }

    public static void menu(){
        System.out.println("**********AppStore Udla**********");
        System.out.println("1. Ingresar Juego");
        System.out.println("2. Mostrar juegos");
        System.out.println("3. Buscar juego");
        System.out.println("4. Comprar");
        System.out.println("5. Vender");
        System.out.println("6. Juego más vendido");
        System.out.println("7. Salir");
        System.out.print("Ingrese una opcion: ");

    }
    public static void ingresarJuegos(){
        System.out.println("*******Ingreso de datos********");
        System.out.print("Ingrese el nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese la categoria: ");
        String categoria = sc.nextLine();
        System.out.print("Ingrese el tamanio: ");
        int tamanio = Integer.parseInt(sc.nextLine());
        System.out.print("Ingrese el precio: ");
        double precio = Double.parseDouble(sc.nextLine());
        System.out.print("Ingrese el stock inicial: ");
        int licenciasDisponibles = Integer.parseInt(sc.nextLine());
        if(tienda.agregarJuego(new Juego(nombre, categoria, tamanio, precio, licenciasDisponibles))){
            JOptionPane.showMessageDialog(null, "El juego se ha adicionado con exito ");
        }else{
            JOptionPane.showMessageDialog(null, "El juego no pudo ser guardado");
        }

    }
    public static void mostrarInformacion(){
        if(tienda.getIndice() >= 0){
            Juego juegos[] = tienda.getJuegos();
            for(int i = 0; i <= tienda.getIndice(); i++){
                System.out.println(juegos[i]);
            }

        }else{
            System.out.println("No hay juegos que mostrar");
        }
    }

    public static void buscarJuego(){
        System.out.print("Ingrese el nombre del juego: ");
        Juego j = tienda.buscarJuego(sc.nextLine());
        if(j != null){
            System.out.println(j);
        }else{
            System.out.println("Juego no encontrado");
        }
    }

    public static void comprar(){
        System.out.print("Ingrese el nombre del juego: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese la cantidad de licencias a comprar: ");
        int cantidad = Integer.parseInt(sc.nextLine());
        if(tienda.comprar(nombre, cantidad)){
            System.out.println("Compra realizada con exito");
        }else{
            System.out.println("No se pudo realizar la compra");
        }
    }

    public static void vender(){
        System.out.print("Ingrese el nombre del juego: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese la cantidad de licencias a vender: ");
        int cantidad = Integer.parseInt(sc.nextLine());
        double total = tienda.vender(nombre, cantidad);
        if(total > 0){
            System.out.println("Venta realizada. Total a pagar: $" + total);
        }else{
            System.out.println("No se pudo vender (no existe o no hay licencias suficientes)");
        }
    }

    public static void masVendido(){
        String nombre = tienda.masVendido();
        if(nombre.isEmpty()){
            System.out.println("No hay juegos registrados");
        }else if(tienda.buscarJuego(nombre).getLicenciasVendidas() == 0){
            System.out.println("Aun no hay ventas registradas");
        }else{
            System.out.println("Juego mas vendido: " + nombre);
        }
    }
}