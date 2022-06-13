package pt.ipvc.backend.data.db.repository;

import pt.ipvc.backend.data.db.entity.Premio;

public class PremioRepository extends Repository {
    @Override
    public Object find(Long id) {
        return _entityManager.find(Premio.class, id);
    }

    @Override
    public Object update(Object object) {
        Premio objectToUpdate = (Premio) find(((Premio) object).getId());
        _entityManager.getTransaction().begin();
        _entityManager.getTransaction().commit();
        return objectToUpdate;
    }
}
