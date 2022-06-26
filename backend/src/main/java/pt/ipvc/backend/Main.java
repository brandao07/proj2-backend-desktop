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
import pt.ipvc.backend.services.users.UtilizadorBLL;
import pt.ipvc.backend.services.util.Encrypt;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        //não eliminar
        LocalRepository.paises_e_cidades();
        LocalRepository.associacoes_portuguesas();

        System.out.println(UtilizadorBLL.getDataCount());


        List infoData = UtilizadorBLL.getDataCount();
        Map<String, Integer> dataMap = new HashMap<>();
        String data = "";
        String count = "";

        for (Object item : infoData){
            data = item.toString();
            data = data.substring((data.indexOf("=") + 1));
            data = data.substring(0, data.indexOf(","));

            count = item.toString();
            count = count.substring((count.indexOf("count=") + 1));
            count = count.substring(0, count.indexOf("}"));
            count = count.replace("ount=", "");

            dataMap.put(data, Integer.valueOf(count));

        }

        System.out.println(dataMap);

    }
}