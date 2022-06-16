package pt.ipvc.backend.services;

import org.jetbrains.annotations.NotNull;
import pt.ipvc.backend.data.db.entity.Competicao;
import pt.ipvc.backend.data.db.entity.Premio;
import pt.ipvc.backend.data.db.entity.TipoPremio;
import pt.ipvc.backend.data.db.repository.PremioRepository;

public class PremioBLL {

    private static final PremioRepository premioRepository = new PremioRepository();

    public static void criarPremio(@NotNull Premio premio, String nomeCompeticao, String tipoPremio) {
        Competicao c = CompeticaoBLL.getCompeticao(nomeCompeticao);
        TipoPremio tp = TipoPremioBLL.getTipoPremio(tipoPremio);
        premio.setCompeticao(c);
        premio.setTipoPremio(tp);
        if (premioRepository.add(premio) != null) {
            System.out.println("Erro ao criar premio");
            return;
        }
        System.out.println("Premio criado com sucesso!");
    }
}
