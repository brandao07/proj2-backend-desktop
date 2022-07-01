package pt.ipvc.backend.data.db.repository;

import pt.ipvc.backend.data.db.entity.Arbitro;
import pt.ipvc.backend.models.ArbitroNomeModalidade;

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
        objectToUpdate.setGenero(((Arbitro) object).getGenero());
        objectToUpdate.setDataNascimento(((Arbitro) object).getDataNascimento());
        objectToUpdate.setModalidade(((Arbitro) object).getModalidade());
        objectToUpdate.setNaturalidade(((Arbitro) object).getNaturalidade());
        objectToUpdate.setNacionalidade(((Arbitro) object).getNacionalidade());
        _entityManager.getTransaction().commit();
    }

    public List findAllArbitrosNomePesquisa(String pesquisa) {
        try {
            Query query = _entityManager.createQuery("SELECT NEW pt.ipvc.backend.models.ArbitroNomeModalidade(a.nome, a.genero, a.dataNascimento, a.nacionalidade, a.naturalidade, m.nome, a.id) FROM Arbitro AS a INNER JOIN Modalidade as m ON m.id = a.modalidade.id WHERE a.nome LIKE CONCAT('%',?1,'%') ", ArbitroNomeModalidade.class);
            query.setParameter(1, pesquisa);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Sem arbitros");
            return null;
        }
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

    public Object findById(long id) {
        try {
            Query query = _entityManager.createQuery("SELECT a FROM Arbitro AS a " +
                    "WHERE a.id = '" + id + "'");
            return query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Sem arbitro");
            return null;
        }
    }

    public List findAllArbitroNomeModalidade() {
        try {
            Query query = _entityManager.createQuery("SELECT NEW pt.ipvc.backend.models.ArbitroNomeModalidade(a.nome, a.genero, a.dataNascimento, a.associacao, a.categoria, a.nacionalidade, m.nome, a.id) FROM Arbitro AS a INNER JOIN Modalidade as m ON m.id = a.modalidade.id", ArbitroNomeModalidade.class);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Sem Árbitros!!!!!");
            return null;
        }
    }
}
