package pt.ipvc.backend.services;

import pt.ipvc.backend.data.db.entity.Atleta;
import pt.ipvc.backend.data.db.entity.Prova;
import pt.ipvc.backend.data.db.repository.ProvaRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class ProvaBLL {

    private static final ProvaRepository provaRepository = new ProvaRepository();

    public static void criarProva(LocalDate dataInicio, String competicao, String equipaCasa, String equipaFora, String recinto, String arbitro) {
        Prova prova = new Prova(Date.valueOf(dataInicio));
        prova.setCompeticao(CompeticaoBLL.getCompeticao(competicao));
        prova.setEquipaCasa(EquipasBLL.getEquipa(equipaCasa));
        prova.setEquipaFora(EquipasBLL.getEquipa(equipaFora));
        prova.setRecinto(RecintoBLL.getRecinto(recinto));
        prova.setArbitro(ArbitroBLL.getArbitro(arbitro));
        if (provaRepository.add(prova) == null) {
            System.out.println("Erro ao criar prova");
            return;
        }
        System.out.println("Prova criada com sucesso!");
    }

    public static Prova getProva(Long id) {
        return (Prova) provaRepository.find(id);
    }

    public static void updateProva(Prova prova) {
        provaRepository.update(prova);
    }

    public static void removerProva(Long id) {
        provaRepository.delete(ProvaBLL.getProva(id));
    }

    public static List getProvaEquipasNome(Long id) {
        return provaRepository.findAllProvaEquipasNome(id);
    }

}
