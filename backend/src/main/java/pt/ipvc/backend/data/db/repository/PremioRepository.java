package pt.ipvc.backend.data.db.repository;

import pt.ipvc.backend.data.db.entity.Competicao;
import pt.ipvc.backend.data.db.entity.Premio;
import pt.ipvc.backend.services.CompeticaoBLL;

import javax.persistence.Query;
import java.util.List;

public class PremioRepository extends Repository {
    @Override
    public Object find(Long id) {
        return _entityManager.find(Premio.class, id);
    }

    @Override
    public void update(Object object) {
        start();
        Premio objectToUpdate = (Premio) find(((Premio) object).getId());
        _entityManager.getTransaction().begin();
        objectToUpdate.setLugar(((Premio) object).getLugar());
        objectToUpdate.setValor(((Premio) object).getValor());
        objectToUpdate.setCompeticao(((Premio) object).getCompeticao());
        objectToUpdate.setTipoPremio(((Premio) object).getTipoPremio());
        _entityManager.getTransaction().commit();
    }

    public List findCompeticao(String nomeCompeticao) {
        try {
            Query query = _entityManager.createQuery("select p from Premio As p Where p.competicao.nome = :nomeCompeticao");
            query.setParameter("nomeCompeticao", nomeCompeticao);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Sem competicao");
            return null;
        }
    }
}
