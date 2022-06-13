package pt.ipvc.backend.data.db.repository;

import pt.ipvc.backend.data.db.entity.Modalidade;

import javax.persistence.Query;
import java.util.List;

public class ModalidadeRepository extends Repository {

    @Override
    public Object find(Long id) {
        return _entityManager.find(Modalidade.class, id);
    }

    @Override
    public Object update(Object object) {
        Modalidade objectToUpdate = (Modalidade) find(((Modalidade) object).getId());
        _entityManager.getTransaction().begin();
        _entityManager.getTransaction().commit();
        return objectToUpdate;
    }

    public List findAll() {
        try {
            _entityManager.getTransaction().begin();
            Query query = _entityManager.createQuery("SELECT m FROM Modalidade AS m");
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Sem modalidades");
            return null;
        }
    }
}
