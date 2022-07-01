package pt.ipvc.backend;


import pt.ipvc.backend.services.users.AdministradorBLL;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //não eliminar
        AdministradorBLL.criarAdministrador("teste", "teste");

    }
}