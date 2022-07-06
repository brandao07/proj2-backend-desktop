package pt.ipvc.backend;


import pt.ipvc.backend.data.db.entity.users.Utilizador;
import pt.ipvc.backend.data.misc.LocalRepository;
import pt.ipvc.backend.services.EquipasBLL;
import pt.ipvc.backend.services.ModalidadeBLL;
import pt.ipvc.backend.services.RecintoBLL;
import pt.ipvc.backend.services.TipoRecintoBLL;
import pt.ipvc.backend.services.users.AdministradorBLL;
import pt.ipvc.backend.services.users.UtilizadorBLL;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //não eliminar
        LocalRepository.paises_e_cidades();
        LocalRepository.associacoes_portuguesas();

        //AdministradorBLL.criarAdministrador("admin1", "123");
        System.out.println(UtilizadorBLL.find(8L));

        Utilizador user = UtilizadorBLL.getUtilizador("admin1");
        user.setPassword("1234");

        //System.out.println(user);

        UtilizadorBLL.updateUtilizador(user);

    }
}