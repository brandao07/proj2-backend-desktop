package pt.ipvc.backend.data.db.repository;

import pt.ipvc.backend.data.db.entity.Prova;

public class ProvaRepository extends Repository {
    @Override
    public Object find(Long id) {
        return _entityManager.find(Prova.class, id);
    }

    @Override
    public void update(Object object) {
        start();
        Prova objectToUpdate = (Prova) find(((Prova) object).getId());
        _entityManager.getTransaction().begin();
        objectToUpdate.setArbitro(((Prova) object).getArbitro());
        objectToUpdate.setRecinto(((Prova) object).getRecinto());
        objectToUpdate.setEquipaFora(((Prova) object).getEquipaFora());
        objectToUpdate.setEquipaCasa(((Prova) object).getEquipaCasa());
        objectToUpdate.setCompeticao(((Prova) object).getCompeticao());
        objectToUpdate.setDataInicio(((Prova) object).getDataInicio());
        objectToUpdate.setResultadoEquipaCasa(((Prova) object).getResultadoEquipaCasa());
        objectToUpdate.setResultadoEquipaFora(((Prova) object).getResultadoEquipaFora());
        _entityManager.getTransaction().commit();
    }
}
