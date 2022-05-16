package pt.ipvc.backend.bll;

import pt.ipvc.backend.entity.AdministradoresEntity;
import pt.ipvc.backend.entity.UtilizadoresEntity;

public class AdministradorBLL {
    public static void criarAdminstrador(String username, String email, String password){
        UtilizadoresEntity.create(username, password, email);
        AdministradoresEntity.create(UtilizadoresEntity.readByUsername(username));
    }

    public static boolean existeAdministrador(String username){
       return AdministradoresEntity.join(UtilizadoresEntity.readByUsername(username));
    }
}
