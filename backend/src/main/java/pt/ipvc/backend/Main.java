package pt.ipvc.backend;

import pt.ipvc.backend.data.db.entity.Competicao;
import pt.ipvc.backend.data.db.entity.TipoPremio;
import pt.ipvc.backend.data.db.repository.CompeticaoRepository;
import pt.ipvc.backend.data.db.repository.PremioRepository;
import pt.ipvc.backend.data.db.repository.TipoPremioRepository;
import pt.ipvc.backend.services.CompeticaoBLL;

public class Main {
    public static void main(String[] args) {
        TipoPremioRepository tipoPremioRepository = new TipoPremioRepository();
        PremioRepository premioRepository = new PremioRepository();
        CompeticaoRepository competicaoRepository = new CompeticaoRepository();
        Competicao c = CompeticaoBLL.getCompeticao("teste");
        TipoPremio tp = (TipoPremio) tipoPremioRepository.find(1L);
    }
}