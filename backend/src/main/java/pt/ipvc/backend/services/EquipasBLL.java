package pt.ipvc.backend.services;

import org.jetbrains.annotations.NotNull;
import pt.ipvc.backend.data.db.entity.Clube;
import pt.ipvc.backend.data.db.entity.Competicao;
import pt.ipvc.backend.data.db.entity.Equipa;
import pt.ipvc.backend.data.db.entity.Modalidade;
import pt.ipvc.backend.data.db.entity.users.Gestor;
import pt.ipvc.backend.data.db.repository.EquipaRepository;
import pt.ipvc.backend.services.users.UtilizadorBLL;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class EquipasBLL {

    private final static EquipaRepository equipasRepository = new EquipaRepository();

    public static Equipa criarEquipa(String nome, @NotNull Clube clube) {

        Equipa equipa = new Equipa(nome);
        equipa.setClube(ClubeBLL.getClube(clube.getNome()));
        if (equipasRepository.add(equipa) == null) {
            System.out.println("Erro ao criar equipa");
            return null;
        }
        System.out.println("Equipa criada com sucesso!");
        return equipa;
    }


    public static List getEquipas() {
        return equipasRepository.findAll();
    }

    public static Equipa getEquipa(String nome) {
        return (Equipa) equipasRepository.find(nome);
    }

    public static void updateEquipa(Equipa equipa) {
        equipasRepository.update(equipa);
    }

    public static void addModalidade(Equipa equipa, Modalidade modalidade) {
        equipasRepository.addModalidade(equipa, modalidade);
    }

    public static void removeModalidade(Equipa equipa, Modalidade modalidade) {
        equipasRepository.addModalidade(equipa, modalidade);
    }

    public static void removerEquipa(String nome) {
        equipasRepository.delete(EquipasBLL.getEquipa(nome));
    }





}
