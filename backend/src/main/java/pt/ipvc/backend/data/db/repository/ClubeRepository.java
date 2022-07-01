package pt.ipvc.backend.data.db.repository;

import pt.ipvc.backend.data.db.entity.Clube;
import pt.ipvc.backend.data.db.entity.Competicao;
import pt.ipvc.backend.data.db.entity.Equipa;

import javax.persistence.Query;
import java.util.List;

public class ClubeRepository extends Repository {
    @Override
    public Object find(Long id) {
        return  _entityManager.find(Clube.class, id);
    }

    @Override
    public void update(Object object) {
        start();
        _entityManager.getTransaction().begin();
        Clube objectToUpdate = (Clube) find(((Clube) object).getId());
        objectToUpdate.setNome(((Clube) object).getNome());
        _entityManager.getTransaction().commit();
    }

    public List findAll() {
        try {
            Query query = _entityManager.createQuery("SELECT c FROM Clube AS c");
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Sem clubes");
            return null;
        }
    }

    public Object find(String nome) {
        try {
            Query query = _entityManager.createQuery("SELECT c FROM Clube AS c " +
                    "WHERE c.nome = '" + nome + "'");
            return query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Sem Clube");
            return null;
        }
    }
}
