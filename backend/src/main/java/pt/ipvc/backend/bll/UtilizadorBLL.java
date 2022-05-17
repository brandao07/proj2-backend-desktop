package pt.ipvc.backend.bll;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pt.ipvc.backend.entity.UtilizadoresEntity;

import java.util.ArrayList;

public class UtilizadorBLL {

    /**
     * Metódo para validar a repetição de emails.
     * @param email Valida input do utilizador
     * @return boolean Em caso de falso existem 2 emails iguais.
     */
    public static boolean validarEmail(String email){
        ArrayList<UtilizadoresEntity> utilizadores = UtilizadoresEntity.readAll();
        for (UtilizadoresEntity utilizador : utilizadores)
            if (utilizador.getEmail().equals(email)) return false;
        return true;
    }

    /**
     * Metódo para validar os caracteres do email
     * @param email Valida input do utilizador
     * @return boolean Em caso de falso, os caracteres do email não são válidos
     */
    public static boolean validarCaracteresEmail(@NotNull String email){
        return email.length() >= 50 && email.contains("@") || email.contains(".com") || email.contains(".pt") || email.contains(".org");
    }

    /**
     * Metódo para validar a repetição de usernames.
     * @param username Valida input do utilizador
     * @return boolean Em caso de falso existem 2 usernames iguais.
     */
    public static boolean validarUsername(String username){
        ArrayList<UtilizadoresEntity> utilizadores = UtilizadoresEntity.readAll();
        for (UtilizadoresEntity utilizador : utilizadores)
            if (utilizador.getUsername().equals(username)) return false;
        return true;
    }

    /**
     * Metódo para validar os caracteres do username
     * @param username Valida input do utilizador
     * @return boolean Em caso de falso, os caracteres do username não são válidos
     */
    public static boolean validarCaracteresUsername(@NotNull String username){
        return username.length()>= 25;
    }
    /**
     * Metódo para validar se as passwords coincidem.
     * @param password Valida input do utilizador
     * @return boolean Em caso de falso as passwords nao coincidem.
     */
    public static boolean validarPasswords(@NotNull String password, String password_repetida){
        return password.equals(password_repetida);
    }

    /**
     * Metódo para validar os caracteres da password
     * @param password Valida input do utilizador
     * @return boolean Em caso de falso, os caracteres da password não são válidos
     */
    public static boolean validarCaracteresPassword(@NotNull String password){
        String letras = "QWERTYUIOPASDFGHJKLZXCVBNM";
        String numeros = "0123456789";

        int contador_caracteres = 0;
        int contador_numeros = 0;

        for (int i = 0; i < password.length(); i++)
            for (int j = 0; j < letras.length(); j++){
                if(password.indexOf(i) == (letras.indexOf(j))){
                    contador_caracteres = contador_caracteres + 1;
                }
                if (j < 10){
                    if(password.indexOf(i) == (numeros.indexOf(j))){
                        contador_numeros = contador_numeros + 1;
                    }
                }
            }
        return contador_caracteres >= 1 && contador_numeros >= 1 && password.length()>=25;
    }

    /**
     * Metódo para validar o Login
     * @param value Input do utilizador(Email ou Username)
     * @param password Input do utilizador
     * @return boolean Em caso de falso, as credenciais são invalidas
     */
    public static boolean validarLogin(String value , String password){
        ArrayList<UtilizadoresEntity> utilizadores = UtilizadoresEntity.readAll();

        for (UtilizadoresEntity utilizador : utilizadores) {
            if (utilizador.getEmail().equals(value) && utilizador.getPassword().equals(password)) return true;
            if (utilizador.getUsername().equals(value) && utilizador.getPassword().equals(password)) return true;
        }
        return false;
    }

    /**
     * Metódo para obter o utilizador logado
     * @param value Email ou Username do utilizador
     * @return do utilizador ativo
     */
    @Nullable
    public static UtilizadoresEntity utilizadorAtivo(String value){
        UtilizadoresEntity utilizador = null;
        ArrayList<UtilizadoresEntity> utilizadores = UtilizadoresEntity.readAll();

        for (UtilizadoresEntity  utilizador_ativo: utilizadores) {
            assert false;
            if (utilizador.getEmail().equals(value)){
                utilizador = utilizador_ativo;
                return utilizador;
            }
            if (utilizador.getUsername().equals(value)){
                utilizador = utilizador_ativo;
                return utilizador;
            }
        }

        return null;
    }
}