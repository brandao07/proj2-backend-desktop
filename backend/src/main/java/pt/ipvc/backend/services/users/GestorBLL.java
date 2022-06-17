package pt.ipvc.backend.services.users;

import org.jetbrains.annotations.Nullable;
import pt.ipvc.backend.data.db.entity.users.Gestor;
import pt.ipvc.backend.data.db.repository.users.GestorRepository;
import pt.ipvc.backend.services.util.Encrypt;
import pt.ipvc.backend.services.util.PasswordGenerator;

public class GestorBLL {

    private static final GestorRepository gestorRepository = new GestorRepository();

    /**
     * @return password para dar ao gestor
     */
    @Nullable
    public static String criarGestor(String username) {
        String password = PasswordGenerator.generatePassword(8);
        Gestor gestor = new Gestor(username, Encrypt.encrypt(password), username.concat("@ipvc.pt"));
        if (gestorRepository.add(gestor) != null) {
            System.out.println("Administrador criado com sucesso!");
            return password;
        }
        System.out.println("Username ou email ja existentes!");
        return null;
    }

    public static Gestor getGestor(String username) {
        return (Gestor) gestorRepository.find(username);
    }

    public static void updateGestor(Gestor gestor) {
        gestorRepository.update(gestor);
    }
}
