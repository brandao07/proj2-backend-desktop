package pt.ipvc.backend.services;

import pt.ipvc.backend.data.db.entity.Competicao;
import pt.ipvc.backend.data.db.entity.Modalidade;
import pt.ipvc.backend.data.db.entity.users.Gestor;
import pt.ipvc.backend.data.db.repository.CompeticaoRepository;
import pt.ipvc.backend.services.users.UtilizadorBLL;

import java.sql.Date;
import java.time.LocalDate;
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

    public static void criarCompeticao(String nome, String genero, LocalDate dataInicio, LocalDate dataFim, Modalidade modalidade) {

        Competicao competicao = new Competicao(nome, Date.valueOf(dataInicio), Date.valueOf(dataFim), genero, modalidade);
        competicao.setGestor((Gestor) UtilizadorBLL.getUserSession());
        if (competicaoRepository.add(competicao) != null) {
            System.out.println("Erro ao criar competicao");
            return;
        }
        System.out.println("Competicao criada com sucesso!");
    }
}
