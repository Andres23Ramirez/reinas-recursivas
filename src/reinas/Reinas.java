/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reinas;

import java.util.Scanner;

/**
 *
 * @author andres
 */
public class Reinas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        //Tablero 
        int[][] tablero = {
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1}
        };

        //Tablero con obstaculos representados con el número 2 
        int[][] tableroObstaculos = {
            {1, 1, 2, 1, 1, 1, 1, 1},
            {1, 2, 1, 2, 1, 2, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {2, 1, 1, 1, 2, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 2, 2, 1, 1, 1, 1, 1},
            {1, 1, 1, 2, 1, 1, 1, 1},
            {1, 1, 1, 1, 2, 1, 1, 1}
        };

        //Pedir al usuario la cordenadas
        System.out.println("Ingrese cordena X ( de 1 a 8): ");
        Scanner entrada = new Scanner(System.in);
        int x = entrada.nextInt();
        x -= 1;
        int x_incial = clonarNumero(x);

        System.out.println("Ingrese cordena y ( de 1 a 8): ");
        Scanner entrada1 = new Scanner(System.in);
        int y = entrada1.nextInt();
        y -= 1;
        int y_inicial = clonarNumero(y);

        //Se ubica la Reina en la posicion dada que es validada
        if (((x >= 0) && (x < 8)) && ((y >= 0) && (y < 8))) {
            
            int[] soluciones = {0, 0, 0, 0, 0, 0, 0, 0};

            System.out.println("Ingrese la opción que quiera resolver");
            System.out.println("1- Tablero sin obstaculos");
            System.out.println("2- Tablero con obstaculos");
            Scanner entrada2 = new Scanner(System.in);
            int opcion = entrada2.nextInt();
            
            switch(opcion){
                case 1:
                    System.out.println("");
                    System.out.println("Tablero sin obstaculos");
                    tablero[x][y] = 0;
                    mostrarTablero(tablero);
                    //Llamada al metodo recursivo
                    resolve(tablero, x, y, soluciones, 0, 0, x_incial, y_inicial);
                    break;
                
                case 2:
                    System.out.println("");
                    System.out.println("Tablero con obstaculos");
                    tableroObstaculos[x][y] = 0;
                    mostrarTablero(tableroObstaculos);
                    //Llamada al metodo recursivo
                    resolveObstaculos(tableroObstaculos, x, y, soluciones, 0, 0, x_incial, y_inicial);
                    break;
                    
                default:
                    break;
            }  
        } else {
            System.err.print("Cordenadas invalidas");
        }

    }

    /*
    @param tablero matriz que representa el tablero
    @param x representa la coordenada x
    @param y represneta la coordenada y
    @param soluciones arreglo donde se guarda las soluciones
    @param direccion indica la direccion a seguir
    @suma indica la suma que representa el resultado
    @x_inicial indica la posicion de incio en x
    @y_inicial indica la posicion de inicio en y
     */
    private static void resolve(int[][] tablero, int x, int y, int[] soluciones, int direccion, int suma, int x_incial, int y_inicial) {

        // Se escoge el caso según la dirreción dada
        switch (direccion) {
            //Recorrer hacia la derecha
            case 0:
                if (y < tablero.length - 1) {

                    y += 1;
                    suma += 1;

                    resolve(tablero, x, y, soluciones, direccion, suma, x_incial, y_inicial);

                } else {
                    soluciones[0] = suma;
                    suma = 0;
                    direccion += 1;
                    resolve(tablero, x_incial, y_inicial, soluciones, direccion, suma, x_incial, y_inicial);
                }

                break;

            //Recorrer diagonal derecha - abajo-
            case 1:
                if ((x < tablero.length - 1) && (y < tablero.length - 1)) {

                    x += 1;
                    y += 1;
                    suma += 1;

                    resolve(tablero, x, y, soluciones, direccion, suma, x_incial, y_inicial);

                } else {
                    soluciones[1] = suma;
                    suma = 0;
                    direccion += 1;
                    resolve(tablero, x_incial, y_inicial, soluciones, direccion, suma, x_incial, y_inicial);
                }

                break;

            //Recorrer abajo-
            case 2:
                if (x < tablero.length - 1) {

                    x += 1;
                    suma += 1;

                    resolve(tablero, x, y, soluciones, direccion, suma, x_incial, y_inicial);

                } else {
                    soluciones[2] = suma;
                    suma = 0;
                    direccion += 1;
                    resolve(tablero, x_incial, y_inicial, soluciones, direccion, suma, x_incial, y_inicial);
                }

                break;

            //Recorrer abajo - izquierda
            case 3:
                if ((x < tablero.length - 1) && (y > 0)) {

                    x -= 1;
                    y -= 1;
                    suma += 1;

                    resolve(tablero, x, y, soluciones, direccion, suma, x_incial, y_inicial);

                } else {
                    soluciones[3] = suma;
                    suma = 0;
                    direccion += 1;
                    resolve(tablero, x_incial, y_inicial, soluciones, direccion, suma, x_incial, y_inicial);
                }
                break;

            //Recorrer hacia la izquierda
            case 4:
                if (y > 0) {

                    y -= 1;
                    suma += 1;

                    resolve(tablero, x, y, soluciones, direccion, suma, x_incial, y_inicial);

                } else {
                    soluciones[4] = suma;
                    suma = 0;
                    direccion += 1;
                    resolve(tablero, x_incial, y_inicial, soluciones, direccion, suma, x_incial, y_inicial);
                }

                break;

            //Recorrer diagonal izquierda - arriba
            case 5:
                if ((x > 0) && (y > 0)) {

                    x -= 1;
                    y -= 1;
                    suma += 1;

                    resolve(tablero, x, y, soluciones, direccion, suma, x_incial, y_inicial);

                } else {
                    soluciones[5] = suma;
                    suma = 0;
                    direccion += 1;
                    resolve(tablero, x_incial, y_inicial, soluciones, direccion, suma, x_incial, y_inicial);
                }

                break;

            //Recorrer arriba
            case 6:
                if (x > 0) {

                    x -= 1;
                    suma += 1;

                    resolve(tablero, x, y, soluciones, direccion, suma, x_incial, y_inicial);

                } else {
                    soluciones[6] = suma;
                    suma = 0;
                    direccion += 1;
                    resolve(tablero, x_incial, y_inicial, soluciones, direccion, suma, x_incial, y_inicial);
                }

                break;

            //Recorrer abajo - izquierda
            case 7:
                if ((x > 0) && (y < tablero.length - 1)) {

                    x -= 1;
                    y += 1;
                    suma += 1;

                    resolve(tablero, x, y, soluciones, direccion, suma, x_incial, y_inicial);

                } else {
                    soluciones[7] = suma;
                    suma = 0;
                    direccion += 1;
                    resolve(tablero, x_incial, y_inicial, soluciones, direccion, suma, x_incial, y_inicial);
                }
                break;

            default:
                mostrarSoluciones(soluciones);
                break;
        }
    }

    private static void resolveObstaculos(int[][] tablero, int x, int y, int[] soluciones, int direccion, int suma, int x_incial, int y_inicial) {

        // Se escoge el caso según la dirreción dada
        switch (direccion) {
            //Recorrer hacia la derecha
            case 0:
                System.out.println("X: "+ x + " Y: "+ y + " tablero[x][y]: "+ tablero[x][y]);
                if (y < tablero.length - 1 && ( tablero[x][y + 1] != 2 ) ) {

                    y += 1;
                    suma += 1;

                    resolveObstaculos(tablero, x, y, soluciones, direccion, suma, x_incial, y_inicial);

                } else {
                    soluciones[0] = suma;
                    suma = 0;
                    direccion += 1;
                    resolveObstaculos(tablero, x_incial, y_inicial, soluciones, direccion, suma, x_incial, y_inicial);
                }

                break;

            //Recorrer diagonal derecha - abajo-
            case 1:
                if ((x < tablero.length - 1) && (y < tablero.length - 1) && ( tablero[x + 1][y + 1] != 2 ) ) {

                    x += 1;
                    y += 1;
                    suma += 1;

                    resolveObstaculos(tablero, x, y, soluciones, direccion, suma, x_incial, y_inicial);

                } else {
                    soluciones[1] = suma;
                    suma = 0;
                    direccion += 1;
                    resolveObstaculos(tablero, x_incial, y_inicial, soluciones, direccion, suma, x_incial, y_inicial);
                }

                break;

            //Recorrer abajo-
            case 2:
                if ( x < tablero.length - 1 && tablero[x + 1][y] != 2 ) {

                    x += 1;
                    suma += 1;

                    resolveObstaculos(tablero, x, y, soluciones, direccion, suma, x_incial, y_inicial);

                } else {
                    soluciones[2] = suma;
                    suma = 0;
                    direccion += 1;
                    resolveObstaculos(tablero, x_incial, y_inicial, soluciones, direccion, suma, x_incial, y_inicial);
                }

                break;

            //Recorrer abajo - izquierda
            case 3:
                if ((x < tablero.length - 1) && (y > 0) && ( tablero[x - 1][y - 1] != 2 ) ) {
                     
                    x -= 1;
                    y -= 1;
                    suma += 1;

                    resolveObstaculos(tablero, x, y, soluciones, direccion, suma, x_incial, y_inicial);

                } else {
                    soluciones[3] = suma;
                    suma = 0;
                    direccion += 1;
                    resolveObstaculos(tablero, x_incial, y_inicial, soluciones, direccion, suma, x_incial, y_inicial);
                }
                break;

            //Recorrer hacia la izquierda
            case 4:
                if (y > 0 && ( tablero[x][y - 1] != 2 ) ) {

                    y -= 1;
                    suma += 1;

                    resolveObstaculos(tablero, x, y, soluciones, direccion, suma, x_incial, y_inicial);

                } else {
                    soluciones[4] = suma;
                    suma = 0;
                    direccion += 1;
                    resolveObstaculos(tablero, x_incial, y_inicial, soluciones, direccion, suma, x_incial, y_inicial);
                }

                break;

            //Recorrer diagonal izquierda - arriba
            case 5:
                if ( (x > 0) && (y > 0) && ( tablero[x - 1][y - 1] != 2 ) ) {

                    x -= 1;
                    y -= 1;
                    suma += 1;

                    resolveObstaculos(tablero, x, y, soluciones, direccion, suma, x_incial, y_inicial);

                } else {
                    soluciones[5] = suma;
                    suma = 0;
                    direccion += 1;
                    resolveObstaculos(tablero, x_incial, y_inicial, soluciones, direccion, suma, x_incial, y_inicial);
                }

                break;

            //Recorrer arriba
            case 6:
                if ( x > 0 && ( tablero[x - 1][y] != 2 ) ) {

                    x -= 1;
                    suma += 1;

                    resolveObstaculos(tablero, x, y, soluciones, direccion, suma, x_incial, y_inicial);

                } else {
                    soluciones[6] = suma;
                    suma = 0;
                    direccion += 1;
                    resolveObstaculos(tablero, x_incial, y_inicial, soluciones, direccion, suma, x_incial, y_inicial);
                }

                break;

            //Recorrer abajo - izquierda
            case 7:
                if ( (x > 0) && (y < tablero.length - 1) && ( tablero[x -1][y + 1] != 2 ) ) {

                    x -= 1;
                    y += 1;
                    suma += 1;

                    resolveObstaculos(tablero, x, y, soluciones, direccion, suma, x_incial, y_inicial);

                } else {
                    soluciones[7] = suma;
                    suma = 0;
                    direccion += 1;
                    resolveObstaculos(tablero, x_incial, y_inicial, soluciones, direccion, suma, x_incial, y_inicial);
                }
                break;

            default:
                mostrarSoluciones(soluciones);
                break;
        }
    }

    
    /*
    Metodo para mostrar el tablero 
    @param tablero matriz que represnta el tablero
     */
    private static void mostrarTablero(int[][] tablero) {

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                System.out.print(" " + tablero[i][j]);
            }
            System.out.println("");
        }
    }

    /*
    Metodo para mostrar las soliciones
    @param soluciones resresenta la cantidad de movimientos dados en cada dirección
     */
    private static void mostrarSoluciones(int[] soluciones) {

        for (int i = 0; i < soluciones.length; i++) {

            switch (i) {

                case 0:

                    System.out.println("Hacia derecha la cantidad de movimientos es: " + soluciones[i]);

                    break;

                case 1:

                    System.out.println("Hacia derecha - abajo la cantidad de movimientos es: " + soluciones[i]);

                    break;

                case 2:

                    System.out.println("Hacia abajo la cantidad de movimientos es: " + soluciones[i]);

                    break;

                case 3:

                    System.out.println("Hacia abajo a la izquierda la cantidad de movimientos es: " + soluciones[i]);

                    break;

                case 4:

                    System.out.println("Hacia izquierda la cantidad de movimientos es: " + soluciones[i]);

                    break;

                case 5:

                    System.out.println("Hacia arriba a la izquierda la cantidad de movimientos es: " + soluciones[i]);

                    break;

                case 6:

                    System.out.println("Hacia arriba la cantidad de movimientos es: " + soluciones[i]);

                    break;

                case 7:

                    System.out.println("Hacia arriba a la derecha la cantidad de movimientos es: " + soluciones[i]);

                    break;

                default:
                    System.out.println("Dirrecion incorrecta");
                    break;
            }

        }
    }

    /*
    Metodo que clona un numero para evitar errors 
    @param x reprensta un número para tener su valor y no su referencia
     */
    private static int clonarNumero(int x) {
        int r = x;
        return r;
    }
}
