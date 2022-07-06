package pt.ipvc.backend.services.users;

import pt.ipvc.backend.data.db.entity.Arbitro;
import pt.ipvc.backend.data.db.entity.users.Utilizador;
import pt.ipvc.backend.data.db.repository.users.UtilizadorRepository;
import pt.ipvc.backend.models.UserTypeModel;

import java.util.List;

public class UtilizadorBLL {

    private static final UtilizadorRepository utilizadorRepository = new UtilizadorRepository();
    private static Utilizador userSession;

    public static Utilizador getUserSession() {
        return userSession;
    }

    public static void setUserSession(Utilizador userSession) {
        UtilizadorBLL.userSession = userSession;
    }

    public static Object validarLogin(String username, String password) {
        return userSession = (Utilizador) utilizadorRepository.find(username, password);
    }

    public static Utilizador find(Long id) {
        return (Utilizador) utilizadorRepository.find(id);
    }
    public static Utilizador getUtilizador(String username) {
        return (Utilizador) utilizadorRepository.findUser(username);
    }

    public static List findUserTypeModel(String username) {
        return  utilizadorRepository.findUserTypeModel(username);
    }

    public static void updateUtilizador(Utilizador user) {
        utilizadorRepository.update(user);
    }

    public static Utilizador getUtilizadoByIdr(String username) {
        return (Utilizador) utilizadorRepository.findUserById(username);
    }

    public static List getUtilizadores() {
        return utilizadorRepository.findAll();
    }

    public static void removerUtilizador(String username) {
        utilizadorRepository.delete(UtilizadorBLL.getUtilizador(username));
    }

    public static void removerUtilizadorById(String username) {
        utilizadorRepository.delete(UtilizadorBLL.getUtilizador(username));
    }

    public static List findUserTypes() {
        return utilizadorRepository.findUserTypes();
    }


    public static long numeroUtilizadores(){
        return (long) utilizadorRepository.numeroUtilizadores();
    }

    public static long numeroAdmins(){
        return (long) utilizadorRepository.numeroAdmins();
    }

    public static long numeroGestores(){
        return (long) utilizadorRepository.numeroGestores();
    }

    public static List getDataCount() {
        return utilizadorRepository.findDataCount();
    }

    public static List getUtilizadoresNomePesquisa(String pesquisa) {
        return  utilizadorRepository.findAllAtletasNomePesquisa(pesquisa);
    }
}