package pt.ipvc.backend;


import pt.ipvc.backend.data.db.entity.Modalidade;
import pt.ipvc.backend.data.db.repository.ModalidadeRepository;
import pt.ipvc.backend.data.misc.LocalRepository;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //não eliminar
        LocalRepository.paises_e_cidades();
        LocalRepository.associacoes_portuguesas();


        Modalidade modalidade = new Modalidade();
        modalidade.setNome("Salto de Cu para a Piça");
        ModalidadeRepository repositorio = new ModalidadeRepository();
        repositorio.add(modalidade);
    }
}