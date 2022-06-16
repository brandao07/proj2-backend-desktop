package pt.ipvc.backend.services;

import pt.ipvc.backend.data.db.entity.TipoPremio;
import pt.ipvc.backend.data.db.entity.TipoRecinto;
import pt.ipvc.backend.data.db.repository.TipoRecintoRepository;

import java.util.List;

public class TipoRecintoBLL {

    private static final TipoRecintoRepository tipoRecintoRepository = new TipoRecintoRepository();

    public static void criarTipoRecinto(String nome) {
        TipoRecinto tipoRecinto = new TipoRecinto();
        tipoRecinto.setNome(nome);

        if (tipoRecintoRepository.add(tipoRecinto) != null) {
            System.out.println("Erro ao criar tipoRecinto");
            return;
        }
        System.out.println("tipoRecinto criado com sucesso!");
    }

    public static List getTiposRecinto() {
        return tipoRecintoRepository.findAll();
    }

    public static TipoRecinto getTipoRecinto(String nome) {
        return (TipoRecinto) tipoRecintoRepository.find(nome);
    }

    public static TipoRecinto updateTipoRecinto(TipoPremio tipoRecinto) {
        return (TipoRecinto) tipoRecintoRepository.update(tipoRecinto);
    }

}
