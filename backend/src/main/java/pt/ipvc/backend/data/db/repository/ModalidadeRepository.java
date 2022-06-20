package pt.ipvc.backend.data.db.repository;

import pt.ipvc.backend.data.db.entity.Modalidade;

import javax.persistence.Query;
import java.util.List;

public class ModalidadeRepository extends Repository {

    @Override
    public Object find(Long id) {
        return _entityManager.find(Modalidade.class, id);
    }

    @Override
    public void update(Object object) {
        _entityManager = _emf.createEntityManager();
        Modalidade objectToUpdate = (Modalidade) find(((Modalidade) object).getId());
        objectToUpdate.setNome(((Modalidade) object).getNome());
        _entityManager.getTransaction().commit();
    }

    public List findAll() {
        try {
            Query query = _entityManager.createQuery("SELECT m FROM Modalidade AS m");
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Sem modalidades");
            return null;
        }
    }

    public Object find(String nome) {
        try {
            Query query = _entityManager.createQuery("SELECT m FROM Modalidade AS m " +
                    "WHERE m.nome = '" + nome + "'");
            return query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Sem modalidade");
            return null;
        }
    }
}
