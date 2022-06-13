package pt.ipvc.backend.data.db.repository.users;

import pt.ipvc.backend.data.db.repository.Repository;
import pt.ipvc.backend.services.util.Encrypt;

import javax.persistence.Query;
import java.util.List;

public class UtilizadorRepository extends Repository {
    @Override
    public Object find(Long id) {
        return null;
    }

    @Override
    public Object update(Object object) {
        return null;
    }

    public List findAll() {
        _entityManager.getTransaction().begin();
        Query query = _entityManager.createQuery(
                "SELECT u FROM Utilizador as u ");
        return query.getResultList();
    }

    public Object find(String username, String password) {
        try {
            String aux = Encrypt.encrypt(password);
            _entityManager.getTransaction().begin();
            Query query = _entityManager.createQuery("SELECT u from Utilizador as u " +
                    "WHERE u.username = '" + username + "' " +
                    "AND u.password = '" + aux + "'");
            return query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Dados inválidos!");
            return null;
        }
    }

    public Object find(String username) {
        try {
            _entityManager.getTransaction().begin();
            Query query = _entityManager.createQuery("SELECT u from Utilizador as u WHERE u.username '" + username + "' ");
            return query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Utilizador nao encontrado!");
            return null;
        }
    }
}
