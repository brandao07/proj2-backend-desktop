package pt.ipvc.backend.bll;

import pt.ipvc.backend.entity.UtilizadoresEntity;

import java.util.ArrayList;

public class UtilizadorBLL {

    //validar o tamanho dos dados, se o email contem o caracter '@' e termina em '.pt' ou '.com' ou '.org e se a password contem uma letra maiscula'
    public static boolean validarDados(String username, String password, String email){
        if (!validarUsername(username)) return false;
        if (!validarEmail(email)) return false;

        if(username.length() <= 15 && password.length() <= 30 && email.length() <= 30) return false;
        if(!email.contains("@")) return false;
        if(!email.contains(".com") || !email.contains(".pt") || !email.contains(".org")) return false;

        return true;
    }


    public static boolean validarEmail(String email){
        ArrayList<UtilizadoresEntity> utilizadores = UtilizadoresEntity.readAll();
        for (UtilizadoresEntity utilizador : utilizadores) {
            if (utilizador.getEmail().equals(email)) return false;
        }
        if (email.length() >= 50) return false;
        return true;
    }

    public static boolean validarUsername(String username){
        ArrayList<UtilizadoresEntity> utilizadores = UtilizadoresEntity.readAll();
        for (UtilizadoresEntity utilizador : utilizadores) {
            if (utilizador.getUsername().equals(username)) return false;
        }
        if (username.length()>= 25) return false;
        return true;
    }

    public static boolean validarPasswords(String password, String password_repetida){
        if(!password.equals(password_repetida)) return false;
        if (password.length()>=25) return false;
        return true;
    }

    public static boolean validarCaracteresPassword(String password){
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
        if(contador_caracteres >= 1 && contador_numeros >= 1) return true;
        return false;
    }



    public static boolean validarLogin(String username , String password){
        ArrayList<UtilizadoresEntity> utilizadores = UtilizadoresEntity.readAll();

        for (UtilizadoresEntity utilizador : utilizadores) {
            if (utilizador.getEmail().equals(username) && utilizador.getPassword().equals(password)) return true;
            if (utilizador.getUsername().equals(username) && utilizador.getPassword().equals(password)) return true;
        }
        return false;
    }

}