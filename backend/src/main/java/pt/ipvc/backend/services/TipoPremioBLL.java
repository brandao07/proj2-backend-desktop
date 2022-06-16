package pt.ipvc.backend.services;

import pt.ipvc.backend.data.db.entity.TipoPremio;
import pt.ipvc.backend.data.db.repository.TipoPremioRepository;

public class TipoPremioBLL {

    private static final TipoPremioRepository tipoPremioRepository = new TipoPremioRepository();

    public static TipoPremio getTipoPremio(String nome) {
        return (TipoPremio) tipoPremioRepository.find(nome);
    }
}
