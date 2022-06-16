package pt.ipvc.backend.data.db.repository;

import pt.ipvc.backend.data.db.entity.Arbitro;

import javax.persistence.Query;
import java.util.List;

public class ArbitroRepository extends Repository {
    @Override
    public Object find(Long id) {
        return _entityManager.find(Arbitro.class, id);
    }

    @Override
    public Object update(Object object) {
        Arbitro objectToUpdate = (Arbitro) find(((Arbitro) object).getId());
        _entityManager.getTransaction().begin();
        _entityManager.getTransaction().commit();
        return objectToUpdate;
    }

    public List findAll() {
        try {
            _entityManager.getTransaction().begin();
            Query query = _entityManager.createQuery("SELECT a FROM Arbitro AS a");
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Sem arbitros");
            return null;
        }
    }

    public Object find(String nome) {
        try {
            _entityManager.getTransaction().begin();
            Query query = _entityManager.createQuery("SELECT a FROM Arbitro AS a " +
                    "WHERE a.nome = '" + nome + "'");
            return query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Sem arbitro");
            return null;
        }
    }
}
