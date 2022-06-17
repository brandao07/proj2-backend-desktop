package pt.ipvc.backend.data.db.repository.users;

import pt.ipvc.backend.data.db.entity.users.Gestor;
import pt.ipvc.backend.data.db.repository.Repository;
import pt.ipvc.backend.services.util.Encrypt;

import javax.persistence.Query;

public class GestorRepository extends Repository {
    @Override
    public Object find(Long id) {
        return _entityManager.find(Gestor.class, id);
    }

    @Override
    public void update(Object object) {
        Gestor objectToUpdate = (Gestor) find(((Gestor) object).getId());
        String password = Encrypt.encrypt(((Gestor) object).getPassword());
        _entityManager.getTransaction().begin();
        objectToUpdate.setUsername(((Gestor) object).getUsername());
        objectToUpdate.setDataCriacao(((Gestor) object).getDataCriacao());
        objectToUpdate.setEmail(((Gestor) object).getEmail());
        objectToUpdate.setPassword(password);
        _entityManager.getTransaction().commit();
    }

    public Object find(String username) {
        try {
            _entityManager.getTransaction().begin();
            Query query = _entityManager.createQuery(
                    "SELECT u from Utilizador as u " +
                            "INNER JOIN Gestor as g " +
                            "ON u.id = g.id " +
                            "WHERE u.username = '" + username + "'");
            return query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Utilizador nao encontrado!");
            return null;
        }
    }
}
