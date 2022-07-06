package pt.ipvc.backend;


import pt.ipvc.backend.data.misc.LocalRepository;
import pt.ipvc.backend.services.EquipasBLL;
import pt.ipvc.backend.services.ModalidadeBLL;
import pt.ipvc.backend.services.RecintoBLL;
import pt.ipvc.backend.services.TipoRecintoBLL;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //não eliminar
        LocalRepository.paises_e_cidades();
        LocalRepository.associacoes_portuguesas();

    }
}