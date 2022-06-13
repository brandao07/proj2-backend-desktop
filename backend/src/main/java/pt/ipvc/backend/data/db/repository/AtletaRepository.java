package pt.ipvc.backend.data.db.repository;

import pt.ipvc.backend.data.db.entity.Atleta;

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
}
