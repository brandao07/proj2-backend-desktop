package pt.ipvc.backend.data.db.repository;

import org.jetbrains.annotations.NotNull;
import pt.ipvc.backend.data.db.entity.Recinto;
import pt.ipvc.backend.data.db.entity.TipoRecinto;

import javax.persistence.Query;
import java.util.List;
import java.util.Objects;

public class RecintoRepository extends Repository {
    @Override
    public Object find(Long id) {
        return _entityManager.find(Recinto.class, id);
    }

    @Override
    public void update(Object object) {
        start();
        Recinto objectToUpdate = (Recinto) find(((Recinto) object).getId());
        _entityManager.getTransaction().begin();
        objectToUpdate.setNome(((Recinto) object).getNome());
        objectToUpdate.setCapacidade(((Recinto) object).getCapacidade());
        objectToUpdate.setPais(((Recinto) object).getPais());
        _entityManager.getTransaction().commit();
    }

    public void addTipoRecinto(@NotNull Recinto recinto, TipoRecinto tipoRecinto) {
        try {
            start();
            _entityManager.getTransaction().begin();
            Recinto r = (Recinto) find(recinto.getId());
            r.getTipos().removeIf(tp -> Objects.equals(tp.getId(), tipoRecinto.getId()));
            r.getTipos().add(tipoRecinto);
            _entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Recinto ja tem tipo!");
        }
    }

    public void removeTipoRecinto(@NotNull Recinto recinto, TipoRecinto tipoRecinto) {
        try {
            start();
            _entityManager.getTransaction().begin();
            Recinto r = (Recinto) find(recinto.getId());
            r.getTipos().removeIf(tp -> Objects.equals(tp.getId(), tipoRecinto.getId()));
            _entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Tipo nao esta no recinto!");
        }
    }

    public List findAll() {
        try {
            Query query = _entityManager.createQuery("SELECT r FROM Recinto AS r");
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Sem recintos");
            return null;
        }
    }

    public Object find(String nome) {
        try {
            Query query = _entityManager.createQuery("SELECT r FROM Recinto AS r " +
                    "WHERE r.nome = '" + nome + "'");
            return query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Sem Recinto");
            return null;
        }
    }
}
