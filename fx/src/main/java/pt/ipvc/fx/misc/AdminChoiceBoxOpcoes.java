package pt.ipvc.fx.misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class AdminChoiceBoxOpcoes {
    public static ArrayList<String> opcoesAdmin(){
        ArrayList<String> opcoesAdmin = new ArrayList<>();
        opcoesAdmin.add("Árbitro");
        opcoesAdmin.add("Atleta");
        opcoesAdmin.add("Equipa");
        opcoesAdmin.add("Prêmio");
        opcoesAdmin.add("Recintos");
         Collections.sort(opcoesAdmin);
        return opcoesAdmin;
    }
}

