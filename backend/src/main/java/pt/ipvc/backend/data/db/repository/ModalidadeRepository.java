package pt.ipvc.backend.data.db.repository;

import org.jetbrains.annotations.NotNull;
import pt.ipvc.backend.data.db.entity.Modalidade;
import pt.ipvc.backend.data.db.entity.Posicao;
import pt.ipvc.backend.data.db.entity.Prova;

import javax.persistence.Query;
import java.util.List;
import java.util.Objects;

public class ModalidadeRepository extends Repository {

    @Override
    public Object find(Long id) {
        return _entityManager.find(Modalidade.class, id);
    }

    @Override
    public void update(Object object) {
        start();
        _entityManager.getTransaction().begin();
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

    public void addPosicao(@NotNull Modalidade modalidade, Posicao posicao) {
        try {
            start();
            _entityManager.getTransaction().begin();
            Modalidade m = (Modalidade) find(modalidade.getId());
            m.getPosicoes().removeIf(tp -> Objects.equals(tp.getId(), posicao.getId()));
            m.getPosicoes().add(posicao);
            _entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Posicao ja na modalidade!");
        }
    }

    public void removePosicao(@NotNull Modalidade modalidade, Posicao posicao) {
        try {
            start();
            _entityManager.getTransaction().begin();
            Modalidade m = (Modalidade) find(modalidade.getId());
            m.getPosicoes().removeIf(p -> Objects.equals(p.getId(), posicao.getId()));
            _entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Posicao nao esta na modalidade!");
        }
    }

    public List getPosicoes(String modalidade) {
        try {
            Query query = _entityManager.createNativeQuery("SELECT p FROM posicao as p " +
                    "INNER JOIN modalidade_posicao mp on p.id = mp.posicoes_id " +
                    "INNER JOIN modalidade m on mp.modalidades_id = m.id " +
                    "WHERE m.nome like :nome");
            query.setParameter("nome", modalidade);
            List<Prova> provas = query.getResultList();
            return provas;
        } catch (Exception e) {
            System.out.println("Sem posicoes");
            return null;
        }
    }
}
