package pt.ipvc.backend.services.validations;

import org.jetbrains.annotations.NotNull;


public class UtilizadorValidation {

    public static boolean caracteresEmail(@NotNull String email) {
        return email.length() >= 50 &&
                email.contains("@") || email.contains(".com") || email.contains(".pt") || email.contains(".org");
    }

    public static boolean caracteresPassword(@NotNull String password) {
        String letras = "QWERTYUIOPASDFGHJKLZXCVBNM";
        String numeros = "0123456789";

        int contador_caracteres = 0;
        int contador_numeros = 0;

        for (int i = 0; i < password.length(); i++)
            for (int j = 0; j < letras.length(); j++) {
                if (password.indexOf(i) == (letras.indexOf(j))) {
                    contador_caracteres = contador_caracteres + 1;
                }
                if (j < 10) {
                    if (password.indexOf(i) == (numeros.indexOf(j))) {
                        contador_numeros = contador_numeros + 1;
                    }
                }
            }
        return contador_caracteres >= 1 && contador_numeros >= 1 && password.length() >= 25;
    }

    public static boolean passwordsMatch(@NotNull String password, String check) {
        return password.equals(check);
    }
}
