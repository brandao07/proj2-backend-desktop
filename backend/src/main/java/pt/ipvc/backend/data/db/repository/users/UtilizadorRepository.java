package pt.ipvc.backend.data.db.repository.users;

import pt.ipvc.backend.data.db.repository.Repository;
import pt.ipvc.backend.models.AtletaNomeEquipa_Modalidade;
import pt.ipvc.backend.models.CompeticaoNomeModalidade;
import pt.ipvc.backend.models.UtilizadorTipoUtilizador;
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

    public List findAmins() {
        try {
            Query query = _entityManager.createQuery("SELECT a FROM Administrador as a INNER JOIN Utilizador AS u ON u.id = a.id");
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Sem admins");
            return null;
        }
    }

    public List findGestores() {
        try {
            Query query = _entityManager.createQuery("SELECT g FROM Gestor as g INNER JOIN Utilizador AS u ON u.id = g.id");
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Sem gestores");
            return null;
        }
    }
    public List findClientes() {
        try {
            Query query = _entityManager.createQuery("SELECT c FROM Cliente as c INNER JOIN Utilizador AS u ON u.id = c.id");
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Sem clientes");
            return null;
        }
    }


    public List juntarUsers() {
        try {
            //Query query = _entityManager.createQuery("SELECT NEW pt.ipvc.backend.models.UtilizadorTipoUtilizador(u.id, ) FROM Utilizador AS u INNER JOIN Administrador as a ON a.id = u.id INNER JOIN Gestor as g ON g.id = u.id INNER JOIN Cliente as c ON a.id = c.id", UtilizadorTipoUtilizador.class);
            //return query.getResultList();
        } catch (Exception e) {
            System.out.println("Sem clientes");
            return null;
        }
        return null;
    }

    public Object numeroUtilizadores(){
        try {
            Query query = _entityManager.createQuery("SELECT count(u) FROM Utilizador as u");
            return  query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Sem clientes");
            return null;

        }
    }

    public Object numeroAdmins(){
        try {
            Query query = _entityManager.createQuery("SELECT count(a) FROM Administrador as a");
            return  query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Sem administradores");
            return null;

        }
    }

    public Object numeroGestores(){
        try {
            Query query = _entityManager.createQuery("SELECT count(g) FROM Gestor as g");
            return  query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Sem gestores");
            return null;

        }
    }








}
