package pt.ipvc.backend.data.db.repository.users;

import pt.ipvc.backend.data.db.repository.Repository;
import pt.ipvc.backend.models.AtletaNomeEquipa_Modalidade;
import pt.ipvc.backend.models.CompeticaoNomeModalidade;
import pt.ipvc.backend.models.CountByDate;
import pt.ipvc.backend.services.util.Encrypt;

import javax.persistence.Query;
import java.util.List;

public class UtilizadorRepository extends Repository {
    @Override
    public Object find(Long id) {
        return null;
    }

    @Override
    public void update(Object object) {
    }

    public List findAll() {
        try {
            Query query = _entityManager.createQuery("SELECT u FROM Utilizador as u ");
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Sem utilizadores");
            return null;
        }
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

    public Object findUser(String username) {
        try {
            Query query = _entityManager.createQuery("SELECT u from Utilizador as u " +
                    "WHERE u.username = '" + username + "' ");
            return query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Utilizador nao encontrado!");
            return null;
        }
    }

    public List findDataCount() {
        try {
            Query query = _entityManager.createQuery("SELECT NEW pt.ipvc.backend.models.CountByDate(u.dataCriacao, count(u.dataCriacao)) FROM Utilizador AS u GROUP BY u.dataCriacao", CountByDate.class);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Utilizador nao encontrado!");
            return null;
        }
    }

}
