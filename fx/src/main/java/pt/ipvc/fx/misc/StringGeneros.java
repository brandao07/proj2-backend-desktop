package pt.ipvc.fx.misc;

import java.util.HashSet;
import java.util.Set;

public class StringGeneros {
    public static Set<String> generos(){
        Set<String> generos = new HashSet<>();
        generos.add("Masculino");
        generos.add("Feminino");
        generos.add("Outro");
        return generos;
    }
}
