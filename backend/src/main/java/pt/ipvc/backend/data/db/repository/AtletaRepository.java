package pt.ipvc.backend.data.db.repository;

import pt.ipvc.backend.data.db.entity.Atleta;
import pt.ipvc.backend.models.ArbitroNomeModalidade;
import pt.ipvc.backend.models.AtletaNomeEquipa_Modalidade;

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
        objectToUpdate.setModalidade(((Atleta) object).getModalidade());
        objectToUpdate.setGenero(((Atleta) object).getGenero());
        objectToUpdate.setAltura(((Atleta) object).getAltura());
        objectToUpdate.setDataNascimento(((Atleta) object).getDataNascimento());
        objectToUpdate.setPeso(((Atleta) object).getPeso());
        objectToUpdate.setPosicao(((Atleta) object).getPosicao());
        objectToUpdate.setNacionalidade(((Atleta) object).getNacionalidade());
        objectToUpdate.setNaturalidade(((Atleta) object).getNaturalidade());
        objectToUpdate.setImage(((Atleta) object).getImage());
        _entityManager.getTransaction().commit();
    }

    public List findAll() {
        try {
            Query query = _entityManager.createQuery("SELECT a FROM Atleta AS a");
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Sem atletas");
            return null;
        }
    }

    public Object find(String nome) {
        try {
            Query query = _entityManager.createQuery("SELECT a FROM Atleta AS a " +
                    "WHERE a.nome = '" + nome + "'");
            return query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Sem Atleta");
            return null;
        }
    }

    public Object findById(long id) {
        try {
            Query query = _entityManager.createQuery("SELECT a FROM Atleta AS a " +
                    "WHERE a.id = '" + id + "'");
            return query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Sem Atleta");
            return null;
        }
    }

    public List findAllAtletaNomeEquipaModalidade() {
        try {
            Query query = _entityManager.createQuery("SELECT NEW pt.ipvc.backend.models.AtletaNomeEquipa_Modalidade(a.id, a.nome, a.genero, a.dataNascimento, a.peso, a.altura, a.nacionalidade, a.naturalidade, a.posicao, e.nome, m.nome) FROM Atleta AS a INNER JOIN Equipa as e ON e.id = a.equipa.id INNER JOIN Modalidade as m ON m.id = a.modalidade.id", AtletaNomeEquipa_Modalidade.class);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Sem Atletas!!!!!");
            return null;
        }
    }

    public List findAllAtletasNomePesquisa(String pesquisa) {
        try {
            Query query = _entityManager.createQuery("SELECT NEW pt.ipvc.backend.models.AtletaNomeEquipa_Modalidade(a.id, a.nome, a.genero, a.dataNascimento, a.peso, a.altura, a.nacionalidade, a.naturalidade, a.posicao, e.nome, m.nome) FROM Atleta AS a INNER JOIN Equipa as e ON e.id = a.equipa.id INNER JOIN Modalidade  as m ON m.id = a.modalidade.id WHERE a.nome LIKE CONCAT('%',?1,'%') ", AtletaNomeEquipa_Modalidade.class);
            query.setParameter(1, pesquisa);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Sem Atletas");
            return null;
        }
    }

    public List findAllAtletasSemEquipa(String modalidade) {
        try {
            Query query = _entityManager.createQuery("SELECT a FROM Atleta AS a WHERE a.modalidade.nome = '" + modalidade + "' and a.equipa.id is null");
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Sem Atletas");
            return null;
        }
    }



}
