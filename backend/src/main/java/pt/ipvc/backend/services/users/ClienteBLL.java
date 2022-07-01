package pt.ipvc.backend.services.users;

import org.jetbrains.annotations.Nullable;
import pt.ipvc.backend.data.db.entity.users.Cliente;
import pt.ipvc.backend.data.db.repository.users.ClienteRepository;
import pt.ipvc.backend.services.util.Encrypt;

public class ClienteBLL {

    private static final ClienteRepository clienteRepository = new ClienteRepository();

    @Nullable
    public static Object criarCliente(String username, String password, String email) {
        Cliente cliente = new Cliente(username, Encrypt.encrypt(password), email);
        if (clienteRepository.add(cliente) != null) {
            System.out.println("Cliente criado com sucesso!");
            return cliente;
        }
        System.out.println("Username ou email ja existentes!");
        return null;
    }
}
