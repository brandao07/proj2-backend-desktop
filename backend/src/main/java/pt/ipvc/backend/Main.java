package pt.ipvc.backend;

import pt.ipvc.backend.servicos.Repositorio;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        Repositorio.paises_e_cidades();
        Repositorio.equipasNBA();
        Repositorio.associacoes_portuguesas();

        for (String key : Repositorio.getMapCidadesPais().keySet()){
            if (key.equals("Portugal")){
                for (String string : Repositorio.getMapCidadesPais().get(key)){
                    System.out.println(string);
                }
            }
            }

    }
}