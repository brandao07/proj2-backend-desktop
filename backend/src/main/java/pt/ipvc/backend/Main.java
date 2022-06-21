package pt.ipvc.backend;


import pt.ipvc.backend.data.db.entity.Modalidade;
import pt.ipvc.backend.data.db.entity.Posicao;
import pt.ipvc.backend.services.ModalidadeBLL;
import pt.ipvc.backend.services.PosicaoBLL;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Posicao posicao1 = PosicaoBLL.getPosicao("teste");
        Posicao posicao2 = PosicaoBLL.getPosicao("teste2");
        Modalidade modalidade = ModalidadeBLL.getModalidade("modalidadeTeste");
        posicao1.setModalidade(modalidade);
        posicao2.setModalidade(modalidade);

        PosicaoBLL.updatePosicao(posicao1);
        PosicaoBLL.updatePosicao(posicao2);
    }
}