package pt.ipvc.backend.data.db.repository;

import pt.ipvc.backend.data.db.entity.Atleta;

import javax.persistence.Query;
import java.util.List;

public class AtletaRepository extends Repository {
    @Override
    public Object find(Long id) {
        return _entityManager.find(Atleta.class, id);
    }

    @Override
    public Object update(Object object) {
        Atleta objectToUpdate = (Atleta) find(((Atleta) object).getId());
        _entityManager.getTransaction().begin();
        _entityManager.getTransaction().commit();
        return objectToUpdate;
    }

    public List findAll() {
        try {
            _entityManager.getTransaction().begin();
            Query query = _entityManager.createQuery("SELECT a FROM Atleta AS a");
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Sem atletas");
            return null;
        }
    }

    public Object find(String nome) {
        try {
            _entityManager.getTransaction().begin();
            Query query = _entityManager.createQuery("SELECT a FROM Atleta AS a " +
                    "WHERE a.nome = '" + nome + "'");
            return query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Sem Atleta");
            return null;
        }
    }
}
