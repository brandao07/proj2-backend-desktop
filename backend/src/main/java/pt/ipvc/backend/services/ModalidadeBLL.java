package pt.ipvc.backend.services;

import pt.ipvc.backend.data.db.entity.Modalidade;
import pt.ipvc.backend.data.db.entity.Posicao;
import pt.ipvc.backend.data.db.repository.ModalidadeRepository;

import java.util.List;


public class ModalidadeBLL {

    private final static ModalidadeRepository modalidadeRepository = new ModalidadeRepository();

    public static void criarModalidade(String nome) {
        Modalidade modalidade = new Modalidade();
        modalidade.setNome(nome);
        if (modalidadeRepository.add(modalidade) == null) {
            System.out.println("Erro ao criar modalidade");
            return;
        }
        System.out.println("Modalidade criada com sucesso!");
    }

    /**
     * @return de todas as modalidades
     */
    public static List getModalidades() {
        return modalidadeRepository.findAll();
    }

    public static Modalidade getModalidade(String nome) {
        return (Modalidade) modalidadeRepository.find(nome);
    }

    public static void updateModalidade(Modalidade modalidade) {
        modalidadeRepository.update(modalidade);
    }

    public static void removerModalidade(String nome) {
        modalidadeRepository.delete(ModalidadeBLL.getModalidade(nome));
    }

    public static void addPosicao(Modalidade modalidade, Posicao posicao) {
        modalidadeRepository.addPosicao(modalidade, posicao);
    }

    public static void removePosicao(Modalidade modalidade, Posicao posicao) {
        modalidadeRepository.removePosicao(modalidade, posicao);
    }
}
