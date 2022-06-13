package pt.ipvc.backend.services;

import pt.ipvc.backend.data.db.entity.users.Utilizador;
import pt.ipvc.backend.data.db.repository.users.UtilizadorRepository;

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
        userSession = (Utilizador) utilizadorRepository.find(username, password);
        return userSession;
    }

    public static Object getUtilizador(String username) {
        return utilizadorRepository.find(username);
    }

}