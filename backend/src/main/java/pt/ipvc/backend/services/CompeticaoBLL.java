package pt.ipvc.backend.services;

import pt.ipvc.backend.data.db.entity.Competicao;
import pt.ipvc.backend.data.db.repository.CompeticaoRepository;

import java.util.List;

public class CompeticaoBLL {

    private final static CompeticaoRepository competicaoRepository = new CompeticaoRepository();


    /**
     * @param modalidade
     * @return de todas as competicoes com x modalidade
     */
    public static List getCompeticoesModalidade(String modalidade) {
        return competicaoRepository.findCompeticoesModalidade(modalidade);
    }

    public static Competicao getCompeticao(String nome) {
        return (Competicao) competicaoRepository.find(nome);
    }

    public static List getCompeticoesActive() {
        return competicaoRepository.findAllActive();
    }
}
