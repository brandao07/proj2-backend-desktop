package pt.ipvc.backend.data.db.repository;

import pt.ipvc.backend.data.db.entity.Posicao;
import pt.ipvc.backend.data.db.entity.Prova;

import javax.persistence.Query;
import java.util.List;

public class PosicaoRepository extends Repository {
    @Override
    public Object find(Long id) {
        return _entityManager.find(Posicao.class, id);
    }

    @Override
    public void update(Object object) {
        start();
        _entityManager.getTransaction().begin();
        Posicao objectToUpdate = (Posicao) find(((Posicao) object).getId());
        objectToUpdate.setNome(((Posicao) object).getNome());
        objectToUpdate.setModalidades(((Posicao) object).getModalidades());
        _entityManager.getTransaction().commit();
    }

    public Object find(String nome) {
        try {
            Query query = _entityManager.createQuery("SELECT p FROM Posicao AS p " +
                    "WHERE p.nome = '" + nome + "'");
            return query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Sem Posicao");
            return null;
        }
    }

    public List findAll() {
        try {
            Query query = _entityManager.createQuery("SELECT p FROM Posicao AS p");
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Sem posicoes");
            return null;
        }
    }

    public List getPosicoesModalidade() {
        try {
            Query query = _entityManager.createQuery("SELECT NEW pt.ipvc.backend.models.PosicaoModalidade(p.nome, m.nome) FROM Posicao as p JOIN p.modalidades m");
            List<Prova> provas = query.getResultList();
            return provas;
        } catch (Exception e) {
            System.out.println("Sem posicoes");
            return null;
        }
    }
}
