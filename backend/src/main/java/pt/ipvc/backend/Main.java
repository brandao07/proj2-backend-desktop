package pt.ipvc.backend;


import pt.ipvc.backend.data.db.entity.*;
import pt.ipvc.backend.data.db.entity.users.Utilizador;
import pt.ipvc.backend.data.db.repository.EquipaRepository;
import pt.ipvc.backend.data.db.repository.ModalidadeRepository;
import pt.ipvc.backend.data.db.repository.TipoRecintoRepository;
import pt.ipvc.backend.data.misc.LocalRepository;
import pt.ipvc.backend.models.ArbitroNomeModalidade;
import pt.ipvc.backend.models.AtletaNomeEquipa_Modalidade;
import pt.ipvc.backend.services.*;
import pt.ipvc.backend.services.users.AdministradorBLL;
import pt.ipvc.backend.services.users.GestorBLL;
import pt.ipvc.backend.services.users.UtilizadorBLL;
import pt.ipvc.backend.services.util.Encrypt;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(Encrypt.decrypt(":v5_~]<t"));

    }
}