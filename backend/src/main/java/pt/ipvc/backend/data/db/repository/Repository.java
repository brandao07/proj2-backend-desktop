package pt.ipvc.backend.data.db.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class Repository {

    protected EntityManagerFactory _emf;
    protected EntityManager _entityManager;

    public Repository() {
        _emf = Persistence.createEntityManagerFactory("default");
        _entityManager = _emf.createEntityManager();
    }

    public Object add(Object object) {
        _entityManager = _emf.createEntityManager();
        try {
            _entityManager.getTransaction().begin();
            _entityManager.persist(object);
            _entityManager.getTransaction().commit();
        } catch (Exception e) {
            return null;
        }
        return object;
    }

    public void delete(Object object) {
        _entityManager.getTransaction().begin();
        _entityManager.remove(object);
        _entityManager.getTransaction().commit();
    }

    public void close() {
        _entityManager.close();
    }

    public void start() {
        _entityManager = _emf.createEntityManager();
    }

    public abstract Object find(Long id);

    public abstract void update(Object object);
}
