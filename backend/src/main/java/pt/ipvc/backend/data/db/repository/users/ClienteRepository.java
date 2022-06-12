package pt.ipvc.backend.data.db.repository.users;

import pt.ipvc.backend.data.db.entity.users.Cliente;
import pt.ipvc.backend.data.db.repository.Repository;

public class ClienteRepository extends Repository {
    @Override
    public Object find(Long id) {
        return _entityManager.find(Cliente.class, id);
    }

    @Override
    public Object update(Object object) {
        Cliente objectToUpdate = (Cliente) find(((Cliente) object).getId());
        _entityManager.getTransaction().begin();
        _entityManager.getTransaction().commit();
        return objectToUpdate;
    }
}
