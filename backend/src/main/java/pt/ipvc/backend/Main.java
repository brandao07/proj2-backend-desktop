package pt.ipvc.backend;


import pt.ipvc.backend.data.db.entity.Equipa;
import pt.ipvc.backend.data.db.entity.Modalidade;
import pt.ipvc.backend.data.db.entity.Posicao;
import pt.ipvc.backend.data.db.repository.EquipaRepository;
import pt.ipvc.backend.data.db.repository.ModalidadeRepository;
import pt.ipvc.backend.data.misc.LocalRepository;
import pt.ipvc.backend.models.CompeticaoNomeModalidade;
import pt.ipvc.backend.services.CompeticaoBLL;
import pt.ipvc.backend.services.EquipasBLL;
import pt.ipvc.backend.services.ModalidadeBLL;
import pt.ipvc.backend.services.PosicaoBLL;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        //não eliminar
        LocalRepository.paises_e_cidades();
        LocalRepository.associacoes_portuguesas();

        List<CompeticaoNomeModalidade> teste = CompeticaoBLL.getCompeticoesModalidadeNome();
        for (CompeticaoNomeModalidade m : teste) {
            System.out.println(m.toString());
        }
    }
}