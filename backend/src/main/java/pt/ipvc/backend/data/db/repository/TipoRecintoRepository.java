package pt.ipvc.backend.data.db.repository;

import pt.ipvc.backend.data.db.entity.TipoRecinto;

public class TipoRecintoRepository extends Repository {

    @Override
    public Object find(Long id) {
        return _entityManager.find(TipoRecinto.class, id);
    }

    @Override
    public Object update(Object object) {
        TipoRecinto objectToUpdate = (TipoRecinto) find(((TipoRecinto) object).getId());
        _entityManager.getTransaction().begin();
        _entityManager.getTransaction().commit();
        return objectToUpdate;
    }
}
