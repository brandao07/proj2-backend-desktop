package pt.ipvc.backend.services;

import pt.ipvc.backend.data.db.repository.EquipaRepository;
import pt.ipvc.backend.data.db.repository.InfoBaseDeDadosRepository;

public class InfoBaseDeDadosBLL {

    private final static InfoBaseDeDadosRepository baseRepository = new InfoBaseDeDadosRepository();

    public static String getTamanho(){
        return baseRepository.tamanhoBD();
    }

}
