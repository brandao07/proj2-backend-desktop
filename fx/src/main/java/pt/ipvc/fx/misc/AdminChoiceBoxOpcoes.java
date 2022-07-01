package pt.ipvc.fx.misc;

import javafx.event.ActionEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class AdminChoiceBoxOpcoes {
    public static ArrayList<String> opcoesAdmin(){
        ArrayList<String> opcoesAdmin = new ArrayList<>();
        opcoesAdmin.add("Árbitros");
        opcoesAdmin.add("Atletas");
        opcoesAdmin.add("Equipas");
        opcoesAdmin.add("Clubes");
        opcoesAdmin.add("Tipos de Prémio");
        opcoesAdmin.add("Tipos de Recinto");
        opcoesAdmin.add("Recintos");
         Collections.sort(opcoesAdmin);
        return opcoesAdmin;
    }

    public static void mudarSceneAdmin(String scene, ActionEvent event) {
        ValidarInput.opcoesMenuAdicionarAdmin(scene, event);
    }
}

