package pt.ipvc.backend.services;

import pt.ipvc.backend.data.db.entity.Equipa;
import pt.ipvc.backend.data.db.entity.Modalidade;
import pt.ipvc.backend.data.db.repository.EquipaRepository;

import java.util.List;

public class EquipasBLL {

    private final static EquipaRepository equipasRepository = new EquipaRepository();

    public static void criarEquipa(String nome) {

        Equipa equipa = new Equipa(nome);
        if (equipasRepository.add(equipa) == null) {
            System.out.println("Erro ao criar equipa");
            return;
        }
        System.out.println("Equipa criada com sucesso!");
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
