package pt.ipvc.backend.data.db.repository;

import pt.ipvc.backend.data.db.entity.Equipa;

import javax.persistence.Query;
import java.util.List;

public class EquipaRepository extends Repository {
    @Override
    public Object find(Long id) {
        return _entityManager.find(Equipa.class, id);
    }

    @Override
    public Object update(Object object) {
        Equipa objectToUpdate = (Equipa) find(((Equipa) object).getId());
        _entityManager.getTransaction().begin();
        _entityManager.getTransaction().commit();
        return objectToUpdate;
    }

    public List findAll() {
        try {
            _entityManager.getTransaction().begin();
            Query query = _entityManager.createQuery("SELECT e FROM Equipa AS e");
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Sem equipas");
            return null;
        }
    }

    public Object find(String nome) {
        try {
            _entityManager.getTransaction().begin();
            Query query = _entityManager.createQuery("SELECT e FROM Equipa AS e " +
                    "WHERE e.nome = '" + nome + "'");
            return query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Sem Equipa");
            return null;
        }
    }
}
