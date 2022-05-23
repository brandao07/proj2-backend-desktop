package pt.ipvc.backend;

import pt.ipvc.backend.bll.AdministradorBLL;
import pt.ipvc.backend.entity.AdministradoresEntity;
import pt.ipvc.backend.entity.RecintosEntity;
import pt.ipvc.backend.entity.UtilizadoresEntity;

public class Main {
    public static void main(String[] args) {
        UtilizadoresEntity.returnDataCriacaoUser();
    }
}