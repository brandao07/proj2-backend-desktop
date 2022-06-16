package pt.ipvc.backend.data.db.repository;

import pt.ipvc.backend.data.db.entity.Recinto;

import javax.persistence.Query;
import java.util.List;

public class RecintoRepository extends Repository {
    @Override
    public Object find(Long id) {
        return _entityManager.find(Recinto.class, id);
    }

    @Override
    public Object update(Object object) {
        Recinto objectToUpdate = (Recinto) find(((Recinto) object).getId());
        _entityManager.getTransaction().begin();
        _entityManager.getTransaction().commit();
        return objectToUpdate;
    }

    public List findAll() {
        try {
            _entityManager.getTransaction().begin();
            Query query = _entityManager.createQuery("SELECT r FROM Recinto AS r");
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Sem recintos");
            return null;
        }
    }

    public Object find(String nome) {
        try {
            _entityManager.getTransaction().begin();
            Query query = _entityManager.createQuery("SELECT r FROM Recinto AS r " +
                    "WHERE r.nome = '" + nome + "'");
            return query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Sem Recinto");
            return null;
        }
    }
}
