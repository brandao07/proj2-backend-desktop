package pt.ipvc.backend.data.db.repository;

import pt.ipvc.backend.data.db.entity.Modalidade;

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
}
