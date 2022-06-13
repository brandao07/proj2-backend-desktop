package pt.ipvc.backend.services;

import pt.ipvc.backend.data.db.repository.ModalidadeRepository;

import java.util.List;


public class ModalidadeBLL {

    private final static ModalidadeRepository modalidadeRepository = new ModalidadeRepository();

    /**
     * @return de todas as modalidades
     */
    public static List getModalidades() {
        return modalidadeRepository.findAll();
    }
}
