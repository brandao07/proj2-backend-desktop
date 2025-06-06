package pt.ipvc.backend.services;

import org.jetbrains.annotations.NotNull;
import pt.ipvc.backend.data.db.entity.Competicao;
import pt.ipvc.backend.data.db.entity.Equipa;
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

    public static List getCompeticoes() {
        return competicaoRepository.findAll();
    }

    public static Competicao getCompeticao(String nome) {
        return (Competicao) competicaoRepository.find(nome);
    }

    public static List getCompeticoesActive(String modalidade) {
        return competicaoRepository.findCompeticoesModalidadeActive(modalidade);
    }

    public static List getCompeticoesActive() {
        return competicaoRepository.findAllCompeticoesModalidadeNomeCurrentDate();
    }

    public static List getCompeticoesGestor(Long id) {
        return competicaoRepository.findAllCompeticoesGestor(id);
    }

    public static List getCompeticoesModalidadeNome() {
        return competicaoRepository.findAllCompeticoesModalidadeNome();
    }

    public static List getCompeticoesModalidadeNomePesquisa(String pesquisa) {
        return competicaoRepository.findAllCompeticoesModalidadeNomePesquisa(pesquisa);
    }

    public static Competicao criarCompeticao(String nome, String genero, LocalDate dataInicio, LocalDate dataFim, @NotNull Modalidade modalidade) {

        Competicao competicao = new Competicao(nome, Date.valueOf(dataInicio), Date.valueOf(dataFim), genero);
        competicao.setGestor((Gestor) UtilizadorBLL.getUserSession());
        competicao.setModalidade(ModalidadeBLL.getModalidade(modalidade.getNome()));
        if (competicaoRepository.add(competicao) == null) {
            System.out.println("Erro ao criar competicao");
            return null;
        }
        System.out.println("Competicao criada com sucesso!");
        return competicao;
    }

    public static void addEquipa(Competicao competicao, Equipa equipa) {
        competicaoRepository.addEquipa(competicao, equipa);
    }

    public static void removeEquipa(Competicao competicao, Equipa equipa) {
        competicaoRepository.removeEquipa(competicao, equipa);
    }

    public static void updateCompeticao(Competicao competicao) {
        competicaoRepository.update(competicao);
    }

    public static void removerCompeticao(String nome) {
        competicaoRepository.delete(CompeticaoBLL.getCompeticao(nome));
    }

    public static Competicao getCompeticao(Long id) {
        return (Competicao) competicaoRepository.find(id);
    }
}
