package pt.ipvc.backend.services;

import pt.ipvc.backend.data.db.entity.Recinto;
import pt.ipvc.backend.data.db.entity.TipoRecinto;
import pt.ipvc.backend.data.db.repository.RecintoRepository;

import java.util.List;

public class RecintoBLL {

    private final static RecintoRepository recintoRepository = new RecintoRepository();

    public static void criarRecinto(Recinto recinto) {
        if (recintoRepository.add(recinto) != null) {
            System.out.println("Erro ao criar recinto");
            return;
        }
        System.out.println("Recinto criado com sucesso!");
    }

    public static List getRecintos() {
        return recintoRepository.findAll();
    }

    public static Recinto getRecinto(String nome) {
        return (Recinto) recintoRepository.find(nome);
    }


    public static void addTipo(Recinto recinto, TipoRecinto tipoRecinto) {
        recintoRepository.addTipoRecinto(recinto, tipoRecinto);
    }

    public static void removeTipo(Recinto recinto, TipoRecinto tipoRecinto) {
        recintoRepository.removeTipoRecinto(recinto, tipoRecinto);
    }
    
    public static void updateRecinto(Recinto recinto) {
        recintoRepository.update(recinto);
    }

    public static void removerRecinto(String nome) {
        recintoRepository.delete(RecintoBLL.getRecinto(nome));
    }
}
