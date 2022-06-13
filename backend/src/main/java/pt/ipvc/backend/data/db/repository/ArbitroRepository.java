package pt.ipvc.backend.data.db.repository;

import pt.ipvc.backend.data.db.entity.Arbitro;

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
}
