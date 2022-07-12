package pt.ipvc.backend;


import pt.ipvc.backend.data.db.entity.Posicao;
import pt.ipvc.backend.data.db.entity.users.Utilizador;
import pt.ipvc.backend.data.misc.LocalRepository;
import pt.ipvc.backend.models.PosicaoModalidade;
import pt.ipvc.backend.services.*;
import pt.ipvc.backend.services.users.AdministradorBLL;
import pt.ipvc.backend.services.users.UtilizadorBLL;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<PosicaoModalidade> list = PosicaoBLL.getPosicoesModalidade();

        for (PosicaoModalidade posicao : list){
            System.out.println(posicao.toString());
        }
    }
}