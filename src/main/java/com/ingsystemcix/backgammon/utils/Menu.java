package com.ingsystemcix.backgammon.utils;

/**
 *
 * @author IngSystemCix
 */
public class Menu {
    public void bannerGame () {
        System.out.println("\n------- BIENVENIDO A BACKGAMMON -------");
    }
    public void menuPrincipal () {
        System.out.println("""
                           Seleccione una de las siguiente opciones:
                           1. Registrar Jugador
                           2. Establecer turnos
                           3. Iniciar el Backgammon
                           0. Salir
                           """);
    }
}
