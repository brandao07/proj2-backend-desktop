package pt.ipvc.backend.data.db.repository;

import org.jetbrains.annotations.NotNull;
import pt.ipvc.backend.data.db.entity.Competicao;
import pt.ipvc.backend.data.db.entity.Equipa;
import pt.ipvc.backend.models.CompeticaoNomeModalidade;

import javax.persistence.Query;
import java.util.List;
import java.util.Objects;

public class CompeticaoRepository extends Repository {
    @Override
    public Object find(Long id) {
        return _entityManager.find(Competicao.class, id);
    }

    @Override
    public void update(Object object) {
        start();
        _entityManager.getTransaction().begin();
        Competicao objectToUpdate = (Competicao) find(((Competicao) object).getId());
        objectToUpdate.setNome(((Competicao) object).getNome());
        objectToUpdate.setGestor(((Competicao) object).getGestor());
        objectToUpdate.setGenero(((Competicao) object).getGenero());
        objectToUpdate.setDataInicio(((Competicao) object).getDataInicio());
        objectToUpdate.setDataFim(((Competicao) object).getDataFim());
        objectToUpdate.setModalidade(((Competicao) object).getModalidade());
        _entityManager.getTransaction().commit();
    }

    public void addEquipa(@NotNull Competicao competicao, Equipa equipa) {
        try {
            start();
            _entityManager.getTransaction().begin();
            Competicao c = (Competicao) find(competicao.getId());
            c.getEquipas().add(equipa);
            _entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Equipa ja na competicao!");
        }
    }

    public void removeEquipa(@NotNull Competicao competicao, Equipa equipa) {
        try {
            start();
            _entityManager.getTransaction().begin();
            Competicao c = (Competicao) find(competicao.getId());
            c.getEquipas().removeIf(e -> Objects.equals(e.getId(), equipa.getId()));
            _entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Equipa nao esta na competicao!");
        }
    }

    public List findAll() {
        try {
            Query query = _entityManager.createQuery("SELECT a FROM Competicao AS a");
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Sem competições");
            return null;
        }
    }

    public List findCompeticoesModalidade(String modalidade) {
        try {
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
            Query query = _entityManager.createQuery("SELECT c FROM Competicao AS c " +
                    "WHERE c.dataFim > current_date");
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Sem competicoes");
            return null;
        }
    }

    public List findAllCompeticoesModalidadeNome() {
        try {
            Query query = _entityManager.createQuery("SELECT NEW pt.ipvc.backend.models.CompeticaoNomeModalidade(c.nome, c.dataInicio, c.dataFim, c.genero, m.nome, c.id) FROM Competicao AS c INNER JOIN Modalidade as m ON m.id = c.modalidade.id", CompeticaoNomeModalidade.class);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Sem competicoes");
            return null;
        }
    }

    public List findAllCompeticoesModalidadeNomePesquisa(String pesquisa) {
        try {
            Query query = _entityManager.createQuery("SELECT NEW pt.ipvc.backend.models.CompeticaoNomeModalidade(c.nome, c.dataInicio, c.dataFim, c.genero, m.nome, c.id) FROM Competicao AS c INNER JOIN Modalidade as m ON m.id = c.modalidade.id WHERE c.nome LIKE CONCAT('%',?1,'%') ", CompeticaoNomeModalidade.class);
            query.setParameter(1, pesquisa);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Sem competicoes");
            return null;
        }
    }

    public List findAllCompeticoesModalidadeNomeCurrentDate() {
        try {
            Query query = _entityManager.createQuery("SELECT NEW pt.ipvc.backend.models.CompeticaoNomeModalidade(c.nome, c.dataInicio, c.dataFim, c.genero, m.nome, c.id) FROM Competicao AS c INNER JOIN Modalidade as m ON m.id = c.modalidade.id WHERE c.dataFim > current_date", CompeticaoNomeModalidade.class);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Sem competicoes");
            return null;
        }
    }

    public List findAllCompeticoesGestor(Long id) {
        try {
            Query query = _entityManager.createQuery("SELECT NEW pt.ipvc.backend.models.CompeticaoNomeModalidade(c.nome, c.dataInicio, c.dataFim, c.genero, m.nome, c.id) FROM Competicao AS c INNER JOIN Modalidade as m ON m.id = c.modalidade.id WHERE c.gestor.id = '" + id + "'", CompeticaoNomeModalidade.class);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Sem competicoes");
            return null;
        }
    }

    public List findCompeticoesModalidadeActive(String modalidade) {
        try {
            Query query = _entityManager.createQuery("SELECT c FROM Competicao AS c " +
                    "INNER JOIN Modalidade as m " +
                    "ON m.id = c.modalidade.id " +
                    "WHERE m.nome ='" + modalidade + "' " +
                    "AND c.dataFim > current_date");
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Sem competicoes");
            return null;
        }
    }
}
