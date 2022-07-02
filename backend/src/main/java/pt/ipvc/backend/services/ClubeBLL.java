package pt.ipvc.backend.services;

import pt.ipvc.backend.data.db.entity.Clube;
import pt.ipvc.backend.data.db.entity.Equipa;
import pt.ipvc.backend.data.db.repository.ClubeRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class ClubeBLL {

    private final static ClubeRepository clubesRepository = new ClubeRepository();

    public static void criarClube(String nome, String sigla, String pais, String cidade, LocalDate data, String contato, String image){

        Clube clube = new Clube(nome, sigla, pais, cidade, Date.valueOf(data), contato, image);

        if (clubesRepository.add(clube) == null) {
            System.out.println("Erro ao criar clube");
            return;
        }
        System.out.println("Clube criado com sucesso!");
    }

    public static List getClubes() {
        return clubesRepository.findAll();
    }

    public static Clube getClube(String nome) {
        return (Clube) clubesRepository.find(nome);
    }

    public static void updateClube(Clube clube) {
        clubesRepository.update(clube);
    }

    public static void removerClube(String nome) {
        clubesRepository.delete(EquipasBLL.getEquipa(nome));
    }


}
