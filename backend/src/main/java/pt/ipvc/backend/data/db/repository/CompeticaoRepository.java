package pt.ipvc.backend.data.db.repository;

import pt.ipvc.backend.data.db.entity.Competicao;

public class CompeticaoRepository extends Repository {
    @Override
    public Object find(Long id) {
        return _entityManager.find(Competicao.class, id);
    }

    @Override
    public Object update(Object object) {
        Competicao objectToUpdate = (Competicao) find(((Competicao) object).getId());
        _entityManager.getTransaction().begin();
        _entityManager.getTransaction().commit();
        return objectToUpdate;
    }
}
