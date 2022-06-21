package pt.ipvc.backend;


import pt.ipvc.backend.data.db.entity.Modalidade;
import pt.ipvc.backend.data.db.entity.Posicao;
import pt.ipvc.backend.services.ModalidadeBLL;
import pt.ipvc.backend.services.PosicaoBLL;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Posicao posicao = PosicaoBLL.getPosicao("teste");
        Posicao posicao2 = PosicaoBLL.getPosicao("teste2");
        Modalidade modalidade = ModalidadeBLL.getModalidade("modalidadeTeste");
        modalidade.getPosicoes().add(posicao2);
        modalidade.getPosicoes().add(posicao);
    }
}