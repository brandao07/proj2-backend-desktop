package pt.ipvc.backend.services;

import pt.ipvc.backend.data.db.entity.TipoPremio;
import pt.ipvc.backend.data.db.repository.TipoPremioRepository;

import java.util.List;

public class TipoPremioBLL {

    private static final TipoPremioRepository tipoPremioRepository = new TipoPremioRepository();

    public static void criarTipoRecinto(String nome) {
        TipoPremio tipoPremio = new TipoPremio();
        tipoPremio.setNome(nome);
        if (tipoPremioRepository.add(tipoPremio) == null) {
            System.out.println("Erro ao criar tipoPremio");
            return;
        }
        System.out.println("tipoPremio criado com sucesso!");
    }

    public static List getTiposPremio() {
        return tipoPremioRepository.findAll();
    }

    public static TipoPremio getTipoPremio(String nome) {
        return (TipoPremio) tipoPremioRepository.find(nome);
    }

    public static void updateTipoPremio(TipoPremio tipoPremio) {
        tipoPremioRepository.update(tipoPremio);
    }

    public static void removerTipoPremio(String nome) {
        tipoPremioRepository.delete(TipoPremioBLL.getTipoPremio(nome));
    }
}
