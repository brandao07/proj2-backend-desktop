package pt.ipvc.backend.services;


import pt.ipvc.backend.data.db.entity.Arbitro;
import pt.ipvc.backend.data.db.repository.ArbitroRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class ArbitroBLL {

    private static final ArbitroRepository arbitroRepository = new ArbitroRepository();

    public static void criarArbitro(String nome, String associacao, LocalDate dataNascimento, String genero,
                                    String categoria, String nacionalidade, String modalidade) {
        Arbitro arbitro = new Arbitro(nome, genero, Date.valueOf(dataNascimento), nacionalidade,
                associacao, categoria);
        arbitro.setModalidade(ModalidadeBLL.getModalidade(modalidade));
        if (arbitroRepository.add(arbitro) == null) {
            System.out.println("Erro ao criar arbitro");
            return;
        }
        System.out.println("Arbitro criado com sucesso!");
    }

    public static Arbitro getArbitro(String nome) {
        return (Arbitro) arbitroRepository.find(nome);
    }

    public static List getArbitros() {
        return arbitroRepository.findAll();
    }

    public static void updateArbitro(Arbitro arbitro) {
        arbitroRepository.update(arbitro);
    }

    public static void removerArbitro(String nome) {
        arbitroRepository.delete(ArbitroBLL.getArbitro(nome));
    }

    public static List getArbitroNomeModalidade() {
        return arbitroRepository.findAllArbitroNomeModalidade();
    }

    public static Arbitro getArbitroId(long id){
        return (Arbitro) arbitroRepository.find(id);

    }
}
