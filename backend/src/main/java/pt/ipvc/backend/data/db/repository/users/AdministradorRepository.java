package pt.ipvc.backend.data.db.repository.users;

import pt.ipvc.backend.data.db.entity.users.Administrador;
import pt.ipvc.backend.data.db.repository.Repository;
import pt.ipvc.backend.services.util.Encrypt;

import javax.persistence.Query;

public class AdministradorRepository extends Repository {
    @Override
    public Object find(Long id) {
        return _entityManager.find(Administrador.class, id);
    }

    @Override
    public void update(Object object) {
        Administrador objectToUpdate = (Administrador) find(((Administrador) object).getId());
        String password = Encrypt.encrypt(((Administrador) object).getPassword());
        _entityManager.getTransaction().begin();
        objectToUpdate.setUsername(((Administrador) object).getUsername());
        objectToUpdate.setDataCriacao(((Administrador) object).getDataCriacao());
        objectToUpdate.setEmail(((Administrador) object).getEmail());
        objectToUpdate.setPassword(password);
        _entityManager.getTransaction().commit();
    }

    public Object find(String username) {
        try {
            _entityManager.getTransaction().begin();
            Query query = _entityManager.createQuery(
                    "SELECT u from Utilizador as u " +
                            "INNER JOIN Administrador as a " +
                            "ON u.id = a.id " +
                            "WHERE u.username = '" + username + "'");
            return query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Utilizador nao encontrado!");
            return null;
        }
    }
}
