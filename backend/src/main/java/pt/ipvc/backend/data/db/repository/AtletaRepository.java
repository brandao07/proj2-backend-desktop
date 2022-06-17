package pt.ipvc.backend.data.db.repository;

import pt.ipvc.backend.data.db.entity.Atleta;

import javax.persistence.Query;
import java.util.List;

public class AtletaRepository extends Repository {
    @Override
    public Object find(Long id) {
        return _entityManager.find(Atleta.class, id);
    }

    @Override
    public void update(Object object) {
        start();
        _entityManager.getTransaction().begin();
        Atleta objectToUpdate = (Atleta) find(((Atleta) object).getId());
        objectToUpdate.setNome(((Atleta) object).getNome());
        objectToUpdate.setEquipa(((Atleta) object).getEquipa());
        objectToUpdate.setGenero(((Atleta) object).getGenero());
        objectToUpdate.setAltura(((Atleta) object).getAltura());
        objectToUpdate.setDataNascimento(((Atleta) object).getDataNascimento());
        objectToUpdate.setPeso(((Atleta) object).getPeso());
        objectToUpdate.setPosicao(((Atleta) object).getPosicao());
        objectToUpdate.setNacionalidade(((Atleta) object).getNacionalidade());
        _entityManager.getTransaction().commit();
    }

    public List findAll() {
        try {
            _entityManager.getTransaction().begin();
            Query query = _entityManager.createQuery("SELECT a FROM Atleta AS a");
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Sem atletas");
            return null;
        }
    }

    public Object find(String nome) {
        try {
            _entityManager.getTransaction().begin();
            Query query = _entityManager.createQuery("SELECT a FROM Atleta AS a " +
                    "WHERE a.nome = '" + nome + "'");
            return query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Sem Atleta");
            return null;
        }
    }
}
