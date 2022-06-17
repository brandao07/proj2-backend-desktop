package pt.ipvc.backend.data.db.repository;

import pt.ipvc.backend.data.db.entity.Premio;

public class PremioRepository extends Repository {
    @Override
    public Object find(Long id) {
        return _entityManager.find(Premio.class, id);
    }

    @Override
    public void update(Object object) {
        Premio objectToUpdate = (Premio) find(((Premio) object).getId());
        _entityManager.getTransaction().begin();
        objectToUpdate.setLugar(((Premio) object).getLugar());
        objectToUpdate.setValor(((Premio) object).getValor());
        objectToUpdate.setCompeticao(((Premio) object).getCompeticao());
        objectToUpdate.setTipoPremio(((Premio) object).getTipoPremio());
        _entityManager.getTransaction().commit();
    }
}
