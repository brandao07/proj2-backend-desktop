package pt.ipvc.backend.data.db.repository;

import pt.ipvc.backend.models.ArbitroNomeModalidade;
import pt.ipvc.backend.models.TamanhoBD;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class InfoBaseDeDadosRepository {
    protected EntityManagerFactory _emf;
    protected EntityManager _entityManager;

    public InfoBaseDeDadosRepository() {
        _emf = Persistence.createEntityManagerFactory("default");
        _entityManager = _emf.createEntityManager();
    }

    public List tamanhoBaseDeDados() {
        try {
            Query query = _entityManager.createQuery("SELECT pg_size_pretty(pg_total_relation_size('proj2'))");
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("tamanho nulo");
            return null;
        }
    }
}
