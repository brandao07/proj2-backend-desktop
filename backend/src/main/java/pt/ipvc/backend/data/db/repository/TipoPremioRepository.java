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
        start();
        _entityManager.getTransaction().begin();
        TipoPremio objectToUpdate = (TipoPremio) find(((TipoPremio) object).getId());
        objectToUpdate.setNome(((TipoPremio) object).getNome());
        _entityManager.getTransaction().commit();
    }

    public Object find(String nome) {
        try {
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
            Query query = _entityManager.createQuery("SELECT tp FROM TipoPremio AS tp");
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Sem tipos premio");
            return null;
        }
    }
}
