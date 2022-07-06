package pt.ipvc.backend.data.db.repository.users;

import pt.ipvc.backend.data.db.entity.Arbitro;
import pt.ipvc.backend.data.db.entity.users.Utilizador;
import pt.ipvc.backend.data.db.repository.Repository;
import pt.ipvc.backend.models.CountByDate;
import pt.ipvc.backend.models.UserTypeModel;
import pt.ipvc.backend.services.util.Encrypt;

import javax.persistence.Query;
import java.util.List;

public class UtilizadorRepository extends Repository {
    @Override
    public Object find(Long id) {
        return _entityManager.find(Utilizador.class, id);

    }

    @Override
    public void update(Object object) {
        start();
        _entityManager.getTransaction().begin();
        Utilizador objectToUpdate = (Utilizador) find(((Utilizador) object).getId());
        objectToUpdate.setUsername(((Utilizador) object).getUsername());
        objectToUpdate.setPassword(((Utilizador) object).getPassword());
        objectToUpdate.setEmail(((Utilizador) object).getEmail());
        objectToUpdate.setDataCriacao(((Utilizador) object).getDataCriacao());
        _entityManager.getTransaction().commit();
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





    public List findUserTypeModel(String username) {
        try{
            Query queryAdmin = _entityManager.createQuery("SELECT NEW pt.ipvc.backend.models.UserTypeModel(u.id, u.username, u.email, 'Administrador') FROM Utilizador u INNER JOIN Administrador a ON u.id = a.id WHERE u.username LIKE CONCAT('%',?1,'%') ");
            Query queryGestor = _entityManager.createQuery("SELECT NEW pt.ipvc.backend.models.UserTypeModel(u.id, u.username, u.email, 'Gestor') FROM Utilizador u INNER JOIN Gestor g ON u.id = g.id WHERE u.username LIKE CONCAT('%',?1,'%') ");
            Query queryCliente = _entityManager.createQuery("SELECT NEW pt.ipvc.backend.models.UserTypeModel(u.id, u.username, u.email, 'Cliente') FROM Utilizador u INNER JOIN Cliente c ON u.id = c.id WHERE u.username LIKE CONCAT('%',?1,'%') ");
            queryAdmin.setParameter(1, username);
            queryGestor.setParameter(1, username);
            queryCliente.setParameter(1, username);


            List<UserTypeModel> users = queryAdmin.getResultList();
            users.addAll(queryCliente.getResultList());
            users.addAll(queryGestor.getResultList());
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List findUserTypes(){
        try{
            Query queryAdmin = _entityManager.createQuery("SELECT NEW pt.ipvc.backend.models.UserTypeModel(u.id, u.username, u.email, 'Administrador') FROM Utilizador u INNER JOIN Administrador a ON u.id = a.id");
            Query queryGestor = _entityManager.createQuery("SELECT NEW pt.ipvc.backend.models.UserTypeModel(u.id, u.username, u.email, 'Gestor') FROM Utilizador u INNER JOIN Gestor g ON u.id = g.id");
            Query queryCliente = _entityManager.createQuery("SELECT NEW pt.ipvc.backend.models.UserTypeModel(u.id, u.username, u.email, 'Cliente') FROM Utilizador u INNER JOIN Cliente c ON u.id = c.id");
            List<UserTypeModel> users = queryAdmin.getResultList();
            users.addAll(queryCliente.getResultList());
            users.addAll(queryGestor.getResultList());
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object findUserById(String username) {
        try {
            Query query = _entityManager.createQuery("SELECT u from Utilizador as u " +
                    "WHERE u.username = '" + username + "' ");
            return query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Utilizador nao encontrado!");
            return null;
        }
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

    public List findDataCount() {
        try {
            Query query = _entityManager.createQuery("SELECT NEW pt.ipvc.backend.models.CountByDate(u.dataCriacao, count(u.dataCriacao)) FROM Utilizador AS u GROUP BY u.dataCriacao", CountByDate.class);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Utilizador nao encontrado!");
            return null;
        }
    }

    public List findAllAtletasNomePesquisa(String pesquisa) {
        try {
            Query query = _entityManager.createQuery("SELECT u.email, u.username FROM Utilizador as u WHERE u.username LIKE CONCAT('%',?1,'%') ");
            query.setParameter(1, pesquisa);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Sem utilizadores");
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
