package pt.ipvc.backend.services;

import org.jetbrains.annotations.NotNull;
import pt.ipvc.backend.data.db.entity.*;
import pt.ipvc.backend.data.db.repository.PremioRepository;

import java.util.List;

public class PremioBLL {

    private static final PremioRepository premioRepository = new PremioRepository();

    public static void criarPremio(@NotNull Premio premio, String nomeCompeticao, String tipoPremio) {
        Competicao c = CompeticaoBLL.getCompeticao(nomeCompeticao);
        TipoPremio tp = TipoPremioBLL.getTipoPremio(tipoPremio);
        premio.setCompeticao(c);
        premio.setTipoPremio(tp);
        if (premioRepository.add(premio) == null) {
            System.out.println("Erro ao criar premio");
            return;
        }
        System.out.println("Premio criado com sucesso!");
    }

    public static List getPremio(String nomeCompeticao) {
        return premioRepository.findCompeticao(nomeCompeticao);
    }

    public static void updatePremio(Premio premio) {
        premioRepository.update(premio);
    }

//    public static Premio getPremioId(Long id) {
//        return (Premio) premioRepository.getPremioTeste(id);
//    }
//
//    public static void removerPremio(Long id) {
//       premioRepository.delete(PremioBLL.getPremioId(id));
//    }

    public static List getPremioNomeTipoPremio(Competicao competicao) {
        return premioRepository.findPremioNomeTipoPremio(competicao);
    }

}
