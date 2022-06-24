package pt.ipvc.backend;


import pt.ipvc.backend.data.db.entity.*;
import pt.ipvc.backend.data.db.repository.EquipaRepository;
import pt.ipvc.backend.data.db.repository.ModalidadeRepository;
import pt.ipvc.backend.data.db.repository.TipoRecintoRepository;
import pt.ipvc.backend.data.misc.LocalRepository;
import pt.ipvc.backend.models.ArbitroNomeModalidade;
import pt.ipvc.backend.models.AtletaNomeEquipa_Modalidade;
import pt.ipvc.backend.services.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        //não eliminar
        LocalRepository.paises_e_cidades();
        LocalRepository.associacoes_portuguesas();

//        Modalidade modalidade = new Modalidade();
//        modalidade.setNome("Futanal");
//        ModalidadeRepository repositorio = new ModalidadeRepository();
//        repositorio.add(modalidade);





        //kapa.setNome("Dillaz");
        //ArbitroBLL.updateArbitro(kapa);

        //AtletaBLL.criarAtleta("uhger", "FEWD", "CDCEC", LocalDate.parse("1111-11-11"), 23, 233, "CSDCSC", "DCDSCS");
        List<AtletaNomeEquipa_Modalidade> teste = AtletaBLL.getAtletaNomeEquipa_Modalidade();

        for (AtletaNomeEquipa_Modalidade atleta : teste){
            System.out.println(atleta.toString());
        }



    //ArbitroBLL.getArbitro("Dillaz").setModalidade(null);
    //ArbitroBLL.updateArbitro(ArbitroBLL.getArbitro("Dillaz"));
        //ArbitroBLL.criarArbitro("KAPPA JOTA", "Associação Conitas", LocalDate.parse("1990-06-06"), "Trans", "Pesados", "Tuga", "Futanal");
        //ArbitroBLL.removerArbitro("KAPPA JOTA");




















    }
}