package pt.ipvc.backend.data.db.repository;

import pt.ipvc.backend.data.db.entity.Prova;
import pt.ipvc.backend.models.ProvaNomeEquipas;

import javax.persistence.Query;
import java.util.List;

public class ProvaRepository extends Repository {
    @Override
    public Object find(Long id) {
        return _entityManager.find(Prova.class, id);
    }

    @Override
    public void update(Object object) {
        start();
        _entityManager.getTransaction().begin();
        Prova objectToUpdate = (Prova) find(((Prova) object).getId());
        objectToUpdate.setRecinto(((Prova) object).getRecinto());
        objectToUpdate.setArbitro(((Prova) object).getArbitro());
        objectToUpdate.setEquipaFora(((Prova) object).getEquipaFora());
        objectToUpdate.setEquipaCasa(((Prova) object).getEquipaCasa());
        objectToUpdate.setCompeticao(((Prova) object).getCompeticao());
        objectToUpdate.setDataInicio(((Prova) object).getDataInicio());
        objectToUpdate.setResultadoEquipaCasa(((Prova) object).getResultadoEquipaCasa());
        objectToUpdate.setResultadoEquipaFora(((Prova) object).getResultadoEquipaFora());
        _entityManager.getTransaction().commit();
    }

    public List findAllProvaEquipasNome(Long id_parametro) {
        try {
            Query query = _entityManager.createQuery("SELECT NEW pt.ipvc.backend.models.ProvaNomeEquipas(" +
                    "p.resultadoEquipaCasa, p.dataInicio, r.nome, e.nome, f.nome, p.resultadoEquipaFora, a.nome, p.id) " +
                    "FROM Prova AS p INNER JOIN Recinto as r ON r.id = p.recinto.id " +
                    "INNER JOIN Equipa as e ON e.id = p.equipaCasa.id " +
                    "INNER JOIN Equipa as f ON f.id = p.equipaFora.id " +
                    "INNER JOIN Arbitro as a ON a.id = p.arbitro.id " +
                    "where p.competicao.id = '" + id_parametro + "'", ProvaNomeEquipas.class);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Sem Dados!");
            return null;
        }
    }
}
