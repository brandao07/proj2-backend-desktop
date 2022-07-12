package pt.ipvc.backend.services;

import pt.ipvc.backend.data.db.entity.Posicao;
import pt.ipvc.backend.data.db.repository.PosicaoRepository;

import java.util.List;

public class PosicaoBLL {
    private static final PosicaoRepository posicaoRepository = new PosicaoRepository();

    public static void criarPosicao(String nome) {
        Posicao posicao = new Posicao();
        posicao.setNome(nome);
        if (posicaoRepository.add(posicao) == null) {
            System.out.println("Erro ao criar Posicao");
            return;
        }
        System.out.println("Posicao criada com sucesso!");
    }

    public static List getPosicoes() {
        return posicaoRepository.findAll();
    }



    public static Posicao getPosicao(String nome) {
        return (Posicao) posicaoRepository.find(nome);
    }

    public static void updatePosicao(Posicao posicao) {
        posicaoRepository.update(posicao);
    }

    public static void removerPosicao(String nome) {
        posicaoRepository.delete(PosicaoBLL.getPosicao(nome));
    }

    public static List getPosicoesModalidade() {
        return posicaoRepository.getPosicoesModalidade();
    }
    
}
