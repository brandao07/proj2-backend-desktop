package pt.ipvc.backend.data.db.repository.users;

import org.jetbrains.annotations.NotNull;
import pt.ipvc.backend.data.db.entity.Competicao;
import pt.ipvc.backend.data.db.entity.Equipa;
import pt.ipvc.backend.data.db.entity.users.Cliente;
import pt.ipvc.backend.data.db.repository.Repository;
import pt.ipvc.backend.services.util.Encrypt;

import java.util.Objects;

public class ClienteRepository extends Repository {
    @Override
    public Object find(Long id) {
        return _entityManager.find(Cliente.class, id);
    }

    @Override
    public void update(Object object) {
        Cliente objectToUpdate = (Cliente) find(((Cliente) object).getId());
        String password = Encrypt.encrypt(((Cliente) object).getPassword());
        _entityManager.getTransaction().begin();
        objectToUpdate.setUsername(((Cliente) object).getUsername());
        objectToUpdate.setDataCriacao(((Cliente) object).getDataCriacao());
        objectToUpdate.setEmail(((Cliente) object).getEmail());
        objectToUpdate.setPassword(password);
        _entityManager.getTransaction().commit();
    }

    public void addEquipa(@NotNull Cliente cliente, Equipa equipa) {
        try {
            start();
            _entityManager.getTransaction().begin();
            Cliente c = (Cliente) find(cliente.getId());
            c.getEquipas().add(equipa);
            _entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Equipa ja nos favoritos!");
        }
    }

    public void removeEquipa(@NotNull Cliente cliente, Equipa equipa) {
        try {
            start();
            _entityManager.getTransaction().begin();
            Cliente c = (Cliente) find(cliente.getId());
            c.getEquipas().removeIf(e -> Objects.equals(e.getId(), equipa.getId()));
            _entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Equipa nao esta nos favoritos!");
        }
    }

    public void addCompeticao(@NotNull Cliente cliente, Competicao competicao) {
        try {
            start();
            _entityManager.getTransaction().begin();
            Cliente c = (Cliente) find(cliente.getId());
            c.getCompeticoes().add(competicao);
            _entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Competicao ja nos favoritos!");
        }
    }

    public void removeCompeticao(@NotNull Cliente cliente, Competicao competicao) {
        try {
            start();
            _entityManager.getTransaction().begin();
            Cliente c = (Cliente) find(cliente.getId());
            c.getCompeticoes().removeIf(e -> Objects.equals(e.getId(), competicao.getId()));
            _entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Competicao nao esta nos favoritos!");
        }
    }
}
