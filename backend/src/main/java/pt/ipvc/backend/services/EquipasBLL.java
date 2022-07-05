package pt.ipvc.backend.services;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pt.ipvc.backend.data.db.entity.Clube;
import pt.ipvc.backend.data.db.entity.Equipa;
import pt.ipvc.backend.data.db.entity.Modalidade;
import pt.ipvc.backend.data.db.repository.EquipaRepository;

import java.util.List;

public class EquipasBLL {

    private final static EquipaRepository equipasRepository = new EquipaRepository();

    @Nullable
    public static Equipa criarEquipa(String nome, @NotNull Clube clube, @NotNull Modalidade modalidade) {

        Equipa equipa = new Equipa(nome);
        equipa.setClube(ClubeBLL.getClube(clube.getNome()));
        equipa.setModalidade(ModalidadeBLL.getModalidade(modalidade.getNome()));
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

    public static List findEquipa(String pesquisa) {
        return equipasRepository.findEquipa(pesquisa);
    }



    public static void updateEquipa(Equipa equipa) {
        equipasRepository.update(equipa);
    }

    public static void removerEquipa(String nome) {
        equipasRepository.delete(EquipasBLL.getEquipa(nome));
    }

    public static Equipa getEquipa(Long id) {
        return (Equipa) equipasRepository.find(id);
    }

    public static List getProvasCompeticao(Long idCompeticao, Long idEquipa) {
        return equipasRepository.findProvasCompeticao(idCompeticao, idEquipa);
    }

    public static List getProvasSemCompeticao(Long idCompeticao, Long idEquipa) {
        return equipasRepository.findProvasCompeticaoNOT(idCompeticao, idEquipa);
    }

    public static List equipasConsultar() {
        return equipasRepository.equipaInfo();
    }

}