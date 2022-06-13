package pt.ipvc.backend.services;

import pt.ipvc.backend.data.db.repository.RecintoRepository;

import java.util.List;

public class RecintoBLL {

    private final static RecintoRepository recintoRepository = new RecintoRepository();

    public static List getRecintos() {
        return recintoRepository.findAll();
    }
}
