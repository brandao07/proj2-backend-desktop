package pt.ipvc.backend.data.db.repository;

import pt.ipvc.backend.data.db.entity.Arbitro;

import javax.persistence.Query;
import java.util.List;

public class ArbitroRepository extends Repository {
    @Override
    public Object find(Long id) {
        return _entityManager.find(Arbitro.class, id);
    }

    @Override
    public void update(Object object) {
        start();
        _entityManager.getTransaction().begin();
        Arbitro objectToUpdate = (Arbitro) find(((Arbitro) object).getId());
        objectToUpdate.setNome(((Arbitro) object).getNome());
        objectToUpdate.setCategoria(((Arbitro) object).getCategoria());
        objectToUpdate.setGenero(((Arbitro) object).getGenero());
        objectToUpdate.setDataNascimento(((Arbitro) object).getDataNascimento());
        objectToUpdate.setModalidade(((Arbitro) object).getModalidade());
        objectToUpdate.setAssociacao(((Arbitro) object).getAssociacao());
        objectToUpdate.setNacionalidade(((Arbitro) object).getNacionalidade());
        _entityManager.getTransaction().commit();
    }

    public List findAll() {
        try {
            Query query = _entityManager.createQuery("SELECT a FROM Arbitro AS a");
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Sem arbitros");
            return null;
        }
    }

    public Object find(String nome) {
        try {
            Query query = _entityManager.createQuery("SELECT a FROM Arbitro AS a " +
                    "WHERE a.nome = '" + nome + "'");
            return query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Sem arbitro");
            return null;
        }
    }
}
