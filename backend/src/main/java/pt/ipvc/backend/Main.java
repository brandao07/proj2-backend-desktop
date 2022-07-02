package pt.ipvc.backend;


import pt.ipvc.backend.data.db.entity.*;
import pt.ipvc.backend.data.db.repository.EquipaRepository;
import pt.ipvc.backend.data.db.repository.ModalidadeRepository;
import pt.ipvc.backend.data.db.repository.TipoRecintoRepository;
import pt.ipvc.backend.data.misc.LocalRepository;
import pt.ipvc.backend.models.ArbitroNomeModalidade;
import pt.ipvc.backend.models.AtletaNomeEquipa_Modalidade;
import pt.ipvc.backend.models.PremioNomeTipoPremio;
import pt.ipvc.backend.models.ProvaNomeEquipas;
import pt.ipvc.backend.services.*;
import pt.ipvc.backend.services.users.AdministradorBLL;
import pt.ipvc.backend.services.users.UtilizadorBLL;
import pt.ipvc.backend.services.util.Encrypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //não eliminar
        LocalRepository.paises_e_cidades();
        LocalRepository.associacoes_portuguesas();
        List<Atleta> teste = AtletaBLL.atletasSemEquipa("Futebol");
        List<String> olo = new ArrayList<>();

        for(Atleta a : teste){
            olo.add(a.getNome());
            System.out.println("Nome: "+ a.getNome());
        }
        System.out.println("Lista de strings:" + olo);


    }
}