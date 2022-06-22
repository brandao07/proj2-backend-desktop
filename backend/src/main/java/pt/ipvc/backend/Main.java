package pt.ipvc.backend;


import pt.ipvc.backend.data.db.entity.*;
import pt.ipvc.backend.data.db.repository.EquipaRepository;
import pt.ipvc.backend.data.db.repository.ModalidadeRepository;
import pt.ipvc.backend.data.db.repository.TipoRecintoRepository;
import pt.ipvc.backend.data.misc.LocalRepository;
import pt.ipvc.backend.services.*;

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




        Recinto recinto = RecintoBLL.getRecinto("Estádio da Luz");
        TipoRecinto objeto= TipoRecintoBLL.getTipoRecinto("Estádio");
        RecintoBLL.addTipo(recinto, objeto);




















    }
}