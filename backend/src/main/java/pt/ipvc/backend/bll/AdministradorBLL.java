package pt.ipvc.backend.bll;

import pt.ipvc.backend.entity.AdministradoresEntity;
import pt.ipvc.backend.entity.UtilizadoresEntity;

public class AdministradorBLL {
    /**
     * Metódo para criar um Administrador
     * @param username Recebe input do utilizador
     * @param email Recebe input do utilizador
     * @param password Recebe input do utilizador
     */
    public static void criarAdminstrador(String username, String email, String password){
        UtilizadoresEntity.create(username, password, email);
        AdministradoresEntity.create(UtilizadoresEntity.readByUsername(username));
    }

    /**
     * Metódo para verificar se o utilizador é administrador
     * @param username Valida input do utilizador
     * @return boolean True - é administrador
     */
    public static boolean existeAdministrador(String username){
       return AdministradoresEntity.join(UtilizadoresEntity.readByUsername(username));
    }
}
