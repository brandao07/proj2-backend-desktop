package pt.ipvc.backend.services;

import pt.ipvc.backend.data.db.repository.EquipaRepository;

import java.util.List;

public class EquipasBLL {

    private final static EquipaRepository equipasRepository = new EquipaRepository();

    public static List getEquipas() {
        return equipasRepository.findAll();
    }
}
