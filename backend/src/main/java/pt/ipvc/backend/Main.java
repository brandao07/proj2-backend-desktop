package pt.ipvc.backend;

import pt.ipvc.backend.services.AdministradorBLL;

public class Main {
    public static void main(String[] args) {
        System.out.println(AdministradorBLL.getAdministrador("admin"));
    }
}