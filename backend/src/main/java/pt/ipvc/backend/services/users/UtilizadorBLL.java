package pt.ipvc.backend.services.users;

import pt.ipvc.backend.data.db.entity.users.Utilizador;
import pt.ipvc.backend.data.db.repository.users.UtilizadorRepository;

import java.util.List;

public class UtilizadorBLL {

    private static final UtilizadorRepository utilizadorRepository = new UtilizadorRepository();
    private static Utilizador userSession;

    public static Utilizador getUserSession() {
        return userSession;
    }

    public static void setUserSession(Utilizador userSession) {
        UtilizadorBLL.userSession = userSession;
    }

    public static Object validarLogin(String username, String password) {
        return userSession = (Utilizador) utilizadorRepository.find(username, password);
    }

    public static Utilizador getUtilizador(String username) {
        return (Utilizador) utilizadorRepository.findUser(username);
    }

    public static List getUtilizadores() {
        return utilizadorRepository.findAll();
    }

    public static void removerUtilizador(String username) {
        utilizadorRepository.delete(UtilizadorBLL.getUtilizador(username));
    }
}