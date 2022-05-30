package pt.ipvc.backend;

import pt.ipvc.backend.bll.PasswordGenerator;

public class Main {
    public static void main(String[] args) {
        System.out.println(PasswordGenerator.generatePassword(8));
    }
}