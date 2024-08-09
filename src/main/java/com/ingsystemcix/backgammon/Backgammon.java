package com.ingsystemcix.backgammon;

import com.ingsystemcix.backgammon.functions.CredentialValidation;
import com.ingsystemcix.backgammon.functions.Dado;
import com.ingsystemcix.backgammon.utils.Board;
import com.ingsystemcix.backgammon.utils.Menu;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author IngSystemCix
 */
public class Backgammon {
    public static void main(String[] args) {
        Board board = new Board();
        Menu menu = new Menu();
        CredentialValidation credentialValidation = new CredentialValidation();
        Dado dado = new Dado();
        
        Scanner sc = new Scanner(System.in);
        
        // Variables
        int op = -1;
        String key;
        String value;
        String piece = null;
        
        Map<String, String> register = new HashMap<>();
        List<Boolean> verify = new ArrayList<>();
        
        do {            
            menu.bannerGame();
            menu.menuPrincipal();
            try {
                System.out.print("Ingrese la opción deseada: ");
                op = sc.nextInt(); sc.nextLine();
                
                switch (op) {
                    case 0 -> System.out.println("\nGracias por haber jugado Backgammon.\n");
                    case 1 -> {
                        System.out.print("Ingrese su usuario: ");
                        key = sc.next(); sc.nextLine();
                        System.out.print("Ingrese su contraseña: ");
                        value = sc.next(); sc.nextLine();
                        register.put(key, value);
                        System.out.printf("\nUsuario '%s' creado exitosamente.\n", key);
                    }
                    case 2 -> {
                        if (register.size() == 2) {
                            String user;
                            String pass;
                            Map<String, Integer> dados = new HashMap<>();
                            for (int i = 1; i <= register.size(); i++) {
                                do {                            
                                    System.out.printf("\nJugador %d\n", i);
                                    System.out.print("Ingrese su usuario: ");
                                    user = sc.next(); sc.nextLine();
                                    System.out.print("Ingrese su contraseña: ");
                                    pass = sc.next(); sc.nextLine();
                                    if (!credentialValidation.validation(register, user, pass)) {
                                        System.out.println("¡Datos incorrectos!");
                                        System.out.println("Por favor, vuelva a ingresar su usuario.");
                                    }
                                } while (!credentialValidation.validation(register, user, pass));
                                verify.add(Boolean.TRUE);
                            }
                            for (Map.Entry<String, String> entry : register.entrySet()) {
                                String usr = entry.getKey();
                                int dado1 = dado.get();
                                int dado2 = dado.get();
                                System.out.printf("Dados para '%s': %d, %d\n", usr, dado1, dado2);
                                dados.put(usr, dado1 + dado2);
                            }

                            Map.Entry<String, Integer> maxEntry = dados.entrySet()
                                .stream()
                                .max(Map.Entry.comparingByValue())
                                .orElse(null);

                            String[] selectPiece = new String[2];

                            if (maxEntry != null) {
                                String usr = maxEntry.getKey();
                                System.out.printf("Jugador '%s', elige la ficha [X u O]: ", usr);
                                piece = sc.next().toUpperCase(); sc.nextLine();
                                selectPiece[0] = piece;
                                selectPiece[1] = ("X".equals(piece)) ? "O" : "X";
                            }

                            int cont = 0;

                            for (Map.Entry<String, String> entry : register.entrySet()) {
                                String usr = entry.getKey();
                                System.out.printf("\nEl jugador '%s' jugará con la ficha '%s'.\n", usr, selectPiece[cont]);
                                cont++;
                            }
                        } else {
                            System.out.println("\nDebes almenos registrar dos usuarios.\n");
                        }
                    }
                    case 3 -> {
                        if (!register.isEmpty() || !verify.isEmpty()) {
                            board.printBoard(piece, 15, 0, 15, 0);
                        } else {
                            System.out.println("No ha registrado ningun usuario o no ha validado credenciales.");
                        }
                    }
                    default -> System.out.println("\nPor favor ingrese una opción válida.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nError: Debes ingresar un número válido para la opción.\n");
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("\nError: Ha ocurrido un problema inesperado. Por favor, intente nuevamente.\n");
            }
        } while (op != 0);
        
        sc.close();
    }
}
