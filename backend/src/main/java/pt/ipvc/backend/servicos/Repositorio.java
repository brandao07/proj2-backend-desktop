package pt.ipvc.backend.servicos;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Repositorio {
    private static Map<String, ArrayList<String>> mapCidadesPais = new HashMap<>();
    private static ArrayList<String> equipasNBA = new ArrayList<>();
    private static Map<String, String> mapAssosiacoesPortuguesas = new HashMap<>();

    public static void paises_e_cidades() throws IOException {
        String sample = ",";
        String mystring;
        try
        {
            BufferedReader brdrd = new BufferedReader(new FileReader("./states.csv"));
            while ((mystring = brdrd.readLine()) != null)  //Reads a line of text
            {
                String[] info = mystring.split(sample);//utilized to split the string

                if (!mapCidadesPais.containsKey(info[4])){
                    ArrayList<String> cidade = new ArrayList<>();
                    cidade.add(info[1]);
                    mapCidadesPais.put(info[4], cidade);
                }if (!info[4].equals("Country name EN")){
                    mapCidadesPais.get(info[4]).add(info[1]);
                }

            }
        }
        catch (IOException e)//catches exception in the try block
        {
            e.printStackTrace();//Prints this throwable and its backtrace
        }
    }
    public static void associacoes_portuguesas() throws IOException {
        String sample = ",";
        String mystring;
        try
        {
            BufferedReader brdrd = new BufferedReader(new FileReader("./associacoes_portugal.csv"));
            while ((mystring = brdrd.readLine()) != null)  //Reads a line of text
            {
                String[] info = mystring.split(sample);//utilized to split the string
                mapAssosiacoesPortuguesas.put(info[0], info[1]);
            }
        }
        catch (IOException e)//catches exception in the try block
        {
            e.printStackTrace();//Prints this throwable and its backtrace
        }
    }
    public static void equipasNBA() throws IOException {
        String sample = ",";
        String mystring;
        try
        {
            BufferedReader brdrd = new BufferedReader(new FileReader("./nba_teams.csv"));
            while ((mystring = brdrd.readLine()) != null)  //Reads a line of text
            {
                String[] info = mystring.split(sample);//utilized to split the string
                equipasNBA.add(info[0]);
            }
        }
        catch (IOException e)//catches exception in the try block
        {
            e.printStackTrace();//Prints this throwable and its backtrace
        }
    }


    public static Map<String, ArrayList<String>> getMapCidadesPais() {
        return mapCidadesPais;
    }

    public static void setMapCidadesPais(Map<String, ArrayList<String>> mapCidadesPais) {
        Repositorio.mapCidadesPais = mapCidadesPais;
    }

    public static ArrayList<String> getEquipasNBA() {
        return equipasNBA;
    }

    public static void setEquipasNBA(ArrayList<String> equipasNBA) {
        Repositorio.equipasNBA = equipasNBA;
    }

    public static Map<String, String> getMapAssosiacoesPortuguesas() {
        return mapAssosiacoesPortuguesas;
    }

    public static void setMapAssosiacoesPortuguesas(Map<String, String> mapAssosiacoesPortuguesas) {
        Repositorio.mapAssosiacoesPortuguesas = mapAssosiacoesPortuguesas;
    }
}
