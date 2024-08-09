package com.ingsystemcix.backgammon.functions;

import java.util.Map;

/**
 *
 * @author IngSystemCix
 */
public class CredentialValidation {
    public boolean validation(Map<String, String> users, String user, String pass) {
        // Verifica si el usuario y la contraseña coinciden en el mapa de usuarios
        for (Map.Entry<String, String> entry : users.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if ((key != null && key.equals(user)) && (value != null && value.equals(pass))) {
                return true;
            }
        }
        return false;
    }
}
