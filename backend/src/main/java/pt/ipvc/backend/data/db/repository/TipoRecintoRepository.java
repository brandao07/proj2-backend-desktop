package pt.ipvc.backend.data.db.repository;

import pt.ipvc.backend.data.db.entity.TipoRecinto;

import javax.persistence.Query;
import java.util.List;

public class TipoRecintoRepository extends Repository {

    @Override
    public Object find(Long id) {
        return _entityManager.find(TipoRecinto.class, id);
    }

    @Override
    public void update(Object object) {
        _entityManager = _emf.createEntityManager();
        TipoRecinto objectToUpdate = (TipoRecinto) find(((TipoRecinto) object).getId());
        objectToUpdate.setNome(((TipoRecinto) object).getNome());
        _entityManager.getTransaction().commit();
    }

    public List findAll() {
        try {
            _entityManager.getTransaction().begin();
            Query query = _entityManager.createQuery("SELECT tr FROM TipoRecinto AS tr");
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Sem tipos recinto");
            return null;
        }
    }

    public Object find(String nome) {
        try {
            _entityManager.getTransaction().begin();
            Query query = _entityManager.createQuery("SELECT tr FROM TipoRecinto AS tr " +
                    "WHERE tr.nome = '" + nome + "'");
            return query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Sem Tipo Recinto");
            return null;
        }
    }
}
