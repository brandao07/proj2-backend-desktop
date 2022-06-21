package pt.ipvc.backend.data.db.repository;

import org.jetbrains.annotations.NotNull;
import pt.ipvc.backend.data.db.entity.Equipa;
import pt.ipvc.backend.data.db.entity.Modalidade;

import javax.persistence.Query;
import java.util.List;
import java.util.Objects;

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
        objectToUpdate.setCidade(((Equipa) object).getCidade());
        objectToUpdate.setPais(((Equipa) object).getPais());
        objectToUpdate.setAssociacao(((Equipa) object).getAssociacao());
        objectToUpdate.setSigla(((Equipa) object).getSigla());
        objectToUpdate.setDataFundacao(((Equipa) object).getDataFundacao());
        objectToUpdate.setContacto(((Equipa) object).getContacto());
        _entityManager.getTransaction().commit();
    }

    public void addModalidade(@NotNull Equipa equipa, Modalidade modalidade) {
        try {
            start();
            _entityManager.getTransaction().begin();
            Equipa e = (Equipa) find(equipa.getId());
            e.getModalidades().removeIf(m -> Objects.equals(m.getId(), modalidade.getId()));
            _entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Equipa ja tem modalidade!");
        }
    }

    public void removeModalidade(@NotNull Equipa equipa, Modalidade modalidade) {
        try {
            start();
            _entityManager.getTransaction().begin();
            Equipa e = (Equipa) find(equipa.getId());
            e.getModalidades().removeIf(m -> Objects.equals(m.getId(), modalidade.getId()));
            _entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Equipa nao tem modalidade!");
        }
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
}
