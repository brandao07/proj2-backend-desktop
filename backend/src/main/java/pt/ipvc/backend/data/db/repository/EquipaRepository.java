package pt.ipvc.backend.data.db.repository;

import pt.ipvc.backend.data.db.entity.Equipa;
import pt.ipvc.backend.data.db.entity.Prova;

import javax.persistence.Query;
import java.util.List;

public class EquipaRepository extends Repository {
    @Override
    public Object find(Long id) {
        return _entityManager.find(Equipa.class, id);
    }

    @Override
    public void update(Object object) {
        start();
        _entityManager.getTransaction().begin();
        Equipa objectToUpdate = (Equipa) find(((Equipa) object).getId());
        objectToUpdate.setNome(((Equipa) object).getNome());
        objectToUpdate.setModalidade(((Equipa) object).getModalidade());
        objectToUpdate.setClube(objectToUpdate.getClube());
        _entityManager.getTransaction().commit();
    }

    public List findAll() {
        try {
            Query query = _entityManager.createQuery("SELECT e FROM Equipa AS e");
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Sem equipas");
            return null;
        }
    }

    public Object find(String nome) {
        try {
            Query query = _entityManager.createQuery("SELECT e FROM Equipa AS e " +
                    "WHERE e.nome = '" + nome + "'");
            return query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Sem Equipa");
            return null;
        }
    }

//    public List findEquipa(String pesquisa) {
//        try {
//            Query query = _entityManager.createQuery("SELECT NEW pt.ipvc.backend.models.(a.nome, a.genero, a.dataNascimento, a.peso, a.altura, a.nacionalidade, a.posicao, e.nome, m.nome) FROM Atleta AS a INNER JOIN Equipa as e ON e.id = a.equipa.id INNER JOIN Modalidade  as m ON m.id = a.modalidade.id WHERE a.nome LIKE CONCAT('%',?1,'%') ", AtletaNomeEquipa_Modalidade.class);
//            query.setParameter(1, pesquisa);
//            return query.getResultList();
//        } catch (Exception e) {
//            System.out.println("Sem Atletas");
//            return null;
//        }
//    }

    public List findProvasCompeticao(Long idCompeticao, Long idEquipa) {
        try {
            Query query = _entityManager.createQuery("SELECT p from Prova p " +
                    "WHERE p.competicao.id = :idC " +
                    "AND (p.equipaCasa.id = :idE OR p.equipaFora.id = :idE)");
            query.setParameter("idE", idEquipa);
            query.setParameter("idC", idCompeticao);
            List<Prova> provas = query.getResultList();
            return provas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List findProvasCompeticaoNOT(Long idCompeticao, Long idEquipa) {
        try {
            Query query = _entityManager.createQuery("SELECT p from Prova p " +
                    "WHERE (p.equipaCasa.id = :idE OR p.equipaFora.id = :idE) " +
                    "AND (p.competicao.id < :idC OR p.competicao.id > :idC)");
            query.setParameter("idE", idEquipa);
            query.setParameter("idC", idCompeticao);
            List<Prova> provas = query.getResultList();

            return provas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
