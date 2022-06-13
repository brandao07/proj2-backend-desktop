package pt.ipvc.backend.data.db.repository;

import pt.ipvc.backend.data.db.entity.Equipa;

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
}
