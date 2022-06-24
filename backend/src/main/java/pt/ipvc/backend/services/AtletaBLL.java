package pt.ipvc.backend.services;

import pt.ipvc.backend.data.db.entity.Atleta;
import pt.ipvc.backend.data.db.repository.AtletaRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class AtletaBLL {

    private static final AtletaRepository atletaRepository = new AtletaRepository();

    public static void criarAtleta(String nome, String genero, String nacionalidade, LocalDate dataNascimento,
                                   double peso, double altura, String equipa, String posicao) {
        Atleta atleta = new Atleta(nome, genero, Date.valueOf(dataNascimento), peso, altura, nacionalidade, posicao);
        atleta.setEquipa(EquipasBLL.getEquipa(equipa));
        if (atletaRepository.add(atleta) == null) {
            System.out.println("Erro ao criar atleta");
            return;
        }
        System.out.println("Atleta criado com sucesso!");
    }

    public static Atleta getAtleta(String nome) {
        return (Atleta) atletaRepository.find(nome);
    }

    public static List getAtletas() {
        return atletaRepository.findAll();

    }

    public static void updateAtleta(Object atleta) {
        atletaRepository.update(atleta);
    }

    public static void removerAtleta(String nome) {
        atletaRepository.delete(AtletaBLL.getAtleta(nome));
    }

    public static List getAtletaNomeEquipa_Modalidade() {
        return atletaRepository.findAllAtletaNomeEquipaModalidade();
    }
}
