package pt.ipvc.backend.data.db.repository;

import pt.ipvc.backend.data.db.entity.Prova;

public class ProvaRepository extends Repository {
    @Override
    public Object find(Long id) {
        return _entityManager.find(Prova.class, id);
    }

    @Override
    public Object update(Object object) {
        Prova objectToUpdate = (Prova) find(((Prova) object).getId());
        _entityManager.getTransaction().begin();
        _entityManager.getTransaction().commit();
        return objectToUpdate;
    }
}
