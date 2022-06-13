package pt.ipvc.backend.data.misc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LocalRepository {
    private static Map<String, Set<String>> mapCidadesPais = new HashMap<>();
    private static ArrayList<String> equipasNBA = new ArrayList<>();
    private static Map<String, String> mapAssosiacoesPortuguesas = new HashMap<>();

    public static void paises_e_cidades() throws IOException {
        String sample = ",";
        String mystring;
        try {
            BufferedReader brdrd = new BufferedReader(new FileReader("./backend/src/main/java/pt/ipvc/backend/data/misc/states.csv"));
            while ((mystring = brdrd.readLine()) != null)  //Reads a line of text
            {
                String[] info = mystring.split(sample);//utilized to split the string

                if (!mapCidadesPais.containsKey(info[4])) {
                    Set<String> cidade = new HashSet<>();
                    String string_cidade = info[1].replace("\"", "");
                    String string_pais = info[4].replace("\"", "");
                    cidade.add(string_cidade);
                    mapCidadesPais.put(string_pais, cidade);
                }
                else {
                    String string_cidade = info[1].replace("\"", "");
                    String string_pais = info[4].replace("\"", "");
                    mapCidadesPais.get(string_pais).add(string_cidade);
                    }

                }
        } catch (IOException e)//catches exception in the try block
        {
            e.printStackTrace();//Prints this throwable and its backtrace
        }
    }

    public static void associacoes_portuguesas() throws IOException {
        String sample = ",";
        String mystring;
        try {
            BufferedReader brdrd = new BufferedReader(new FileReader("./backend/src/main/java/pt/ipvc/backend/data/misc/associacoes_portugal.csv"));

            while ((mystring = brdrd.readLine()) != null)  //Reads a line of text
            {
                String[] info = mystring.split(sample);//utilized to split the string
                mapAssosiacoesPortuguesas.put(info[0], info[1]);
            }
        } catch (IOException e)//catches exception in the try block
        {
            e.printStackTrace();//Prints this throwable and its backtrace
        }
    }


    public static Map<String, Set<String>> getMapCidadesPais() {
        return mapCidadesPais;
    }

    public static void setMapCidadesPais(Map<String, Set<String>> mapCidadesPais) {
        LocalRepository.mapCidadesPais = mapCidadesPais;
    }

    public static ArrayList<String> getEquipasNBA() {
        return equipasNBA;
    }

    public static void setEquipasNBA(ArrayList<String> equipasNBA) {
        LocalRepository.equipasNBA = equipasNBA;
    }

    public static Map<String, String> getMapAssosiacoesPortuguesas() {
        return mapAssosiacoesPortuguesas;
    }

    public static void setMapAssosiacoesPortuguesas(Map<String, String> mapAssosiacoesPortuguesas) {
        LocalRepository.mapAssosiacoesPortuguesas = mapAssosiacoesPortuguesas;
    }
}
