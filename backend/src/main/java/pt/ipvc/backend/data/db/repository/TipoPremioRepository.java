package pt.ipvc.backend.data.db.repository;

import pt.ipvc.backend.data.db.entity.TipoPremio;

import javax.persistence.Query;
import java.util.List;

public class TipoPremioRepository extends Repository {
    @Override
    public Object find(Long id) {
        return _entityManager.find(TipoPremio.class, id);
    }

    @Override
    public void update(Object object) {
        _entityManager = _emf.createEntityManager();
        TipoPremio objectToUpdate = (TipoPremio) find(((TipoPremio) object).getId());
        objectToUpdate.setNome(((TipoPremio) object).getNome());
        _entityManager.getTransaction().commit();
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

    public List findAll() {
        try {
            _entityManager.getTransaction().begin();
            Query query = _entityManager.createQuery("SELECT tp FROM TipoPremio AS tp");
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Sem tipos premio");
            return null;
        }
    }
}
