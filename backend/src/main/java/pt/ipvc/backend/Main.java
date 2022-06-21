package pt.ipvc.backend;


import pt.ipvc.backend.data.db.entity.Equipa;
import pt.ipvc.backend.data.db.entity.Modalidade;
import pt.ipvc.backend.data.db.entity.Posicao;
import pt.ipvc.backend.data.db.repository.EquipaRepository;
import pt.ipvc.backend.data.db.repository.ModalidadeRepository;
import pt.ipvc.backend.data.misc.LocalRepository;
import pt.ipvc.backend.services.EquipasBLL;
import pt.ipvc.backend.services.ModalidadeBLL;
import pt.ipvc.backend.services.PosicaoBLL;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //não eliminar
        LocalRepository.paises_e_cidades();
        LocalRepository.associacoes_portuguesas();

//        Modalidade modalidade = new Modalidade();
//        modalidade.setNome("Futanal");
//        ModalidadeRepository repositorio = new ModalidadeRepository();
//        repositorio.add(modalidade);




        Modalidade futanal = ModalidadeBLL.getModalidade("Salto de Cu para a Piça");
        Equipa equipa = EquipasBLL.getEquipa("Porto");
        EquipasBLL.addModalidade(equipa, futanal);
        EquipasBLL.updateEquipa(equipa);















    }
}