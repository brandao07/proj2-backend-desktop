package pt.ipvc.backend.data.db.repository;

import pt.ipvc.backend.data.db.entity.Competicao;

import javax.persistence.Query;
import java.util.List;

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

    public List findCompeticoesModalidade(String modalidade) {
        try {
            _entityManager.getTransaction().begin();
            Query query = _entityManager.createQuery("SELECT c FROM Competicao AS c " +
                    "INNER JOIN Modalidade as m " +
                    "ON m.id = c.modalidade.id " +
                    "WHERE m.nome ='" + modalidade + "'");
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Sem competicoes");
            return null;
        }
    }

    public Object find(String nome) {
        try {
            _entityManager.getTransaction().begin();
            Query query = _entityManager.createQuery("SELECT c FROM Competicao AS c " +
                    "WHERE c.nome = '" + nome + "'");
            return query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Sem competicao");
            return null;
        }
    }

    public List findAllActive() {
        try {
            _entityManager.getTransaction().begin();
            Query query = _entityManager.createQuery("SELECT c FROM Competicao AS c " +
                    "WHERE c.dataFim > current_date");
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Sem competicoes");
            return null;
        }
    }
}
