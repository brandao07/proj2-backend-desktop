package pt.ipvc.backend.services;

import pt.ipvc.backend.data.db.entity.users.Administrador;
import pt.ipvc.backend.data.db.repository.users.AdministradorRepository;
import pt.ipvc.backend.services.util.Encrypt;

public class AdministradorBLL {

    private static final AdministradorRepository administradorRepository = new AdministradorRepository();

    public static void criarAdministrador(String username, String password) {
        Administrador administrador = new Administrador(username, Encrypt.encrypt(password), username.concat("@ipvc.pt"));
        if (administradorRepository.add(administrador) != null) {
            System.out.println("Administrador criado com sucesso!");
            return;
        }
        System.out.println("Username ou email ja existentes!");
    }

    public static Administrador getAdministrador(String username) {
        return (Administrador) administradorRepository.find(username);
    }

    public static void criarAdminstrador(String username, String email, String password) {
    }

    public static boolean existeAdministrador(String username) {
        return false;
    }
}
