package pt.ipvc.backend;


import pt.ipvc.backend.data.db.entity.Modalidade;
import pt.ipvc.backend.services.ModalidadeBLL;

public class Main {
    public static void main(String[] args) {
        Modalidade modalidade = ModalidadeBLL.getModalidade("Futebol");
        Modalidade modalidade1 = ModalidadeBLL.getModalidade("Andebol");

        System.out.println(modalidade.getNome());
        System.out.println(modalidade1.getNome());
    }
}