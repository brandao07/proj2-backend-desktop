package pt.ipvc.backend.data.db.repository;

import pt.ipvc.backend.data.db.entity.TipoPremio;

public class TipoPremioRepository extends Repository {
    @Override
    public Object find(Long id) {
        return _entityManager.find(TipoPremio.class, id);
    }

    @Override
    public Object update(Object object) {
        TipoPremio objectToUpdate = (TipoPremio) find(((TipoPremio) object).getId());
        _entityManager.getTransaction().begin();
        _entityManager.getTransaction().commit();
        return objectToUpdate;
    }
}
