package pt.ipvc.backend.data.db.repository;

import pt.ipvc.backend.data.db.entity.TipoPremio;

import javax.persistence.Query;

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

    public Object find(String nome) {
        try {
            _entityManager.getTransaction().begin();
            Query query = _entityManager.createQuery("SELECT tp FROM TipoPremio AS tp " +
                    "WHERE tp.nome = '" + nome + "'");
            return query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Sem Tipo Premio");
            return null;
        }
    }
}
