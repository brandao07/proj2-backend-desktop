package pt.ipvc.backend.data.db.repository.users;

import pt.ipvc.backend.data.db.entity.users.Gestor;
import pt.ipvc.backend.data.db.repository.Repository;

public class GestorRepository extends Repository {
    @Override
    public Object find(Long id) {
        return _entityManager.find(Gestor.class, id);
    }

    @Override
    public Object update(Object object) {
        Gestor objectToUpdate = (Gestor) find(((Gestor) object).getId());
        _entityManager.getTransaction().begin();
        _entityManager.getTransaction().commit();
        return objectToUpdate;
    }
}
