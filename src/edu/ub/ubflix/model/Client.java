package edu.ub.ubflix.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Client {
    private String idClient;
    private String pwd;
    private String name;
    private String dni;
    private String address;
    private boolean vip;
    private List<Usuari> usuaris;

    public Client(String idClient, String pwd, String name, String dni, String address, boolean vip) {
        this.idClient = idClient;
        this.pwd = pwd;
        this.name = name;
        this.dni = dni;
        this.address = address;
        this.vip = vip;
        this.usuaris = new ArrayList<>();
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public List<Usuari> getUsuaris() {
        return usuaris;
    }

    public void setUsuaris(List<Usuari> usuaris) {
        this.usuaris = usuaris;
    }

    private Usuari getUsuari(int idUsuari) {
        for (Usuari u : usuaris) {
            if (u.getIdUsuari()==idUsuari) return u;
        }
        return null;
    }

    private Usuari getUserByName(String nomUsuari) {
        for (Usuari u : usuaris) {
            if (u.getName().equals(nomUsuari)) return u;
        }
        return null;
    }

    public Usuari getUsuari(String nomUsuari) {
        return Objects.requireNonNull(getUserByName(nomUsuari), "User not found");
    }

    private boolean isAllowedNewUser() {
        return usuaris.size() != 5;
    }

    private boolean isValidUserName(String userName) {
        return getUserByName(userName) == null;
    }

    public int addUser(String userName, int idUsuari) {
        if (!isAllowedNewUser()) return -1;
        if (!isValidUserName(userName)) return -2;
        usuaris.add(new Usuari(idClient, idUsuari, userName));
        return 0;
    }

    public void addUser(Usuari u){
        usuaris.add(u);
    }

    public List<String> listUsuaris() {
        List<String> userNames = new ArrayList<>();
        for (Usuari u : usuaris) {
            userNames.add(u.getName());
        }
        return userNames;
    }

    public String veurePerfil(int idUsuari) {
        return Objects.requireNonNull(getUsuari(idUsuari)).toString();
    }

    public List<String> listMyList(String nomUsuari) {
        return Objects.requireNonNull(getUsuari(nomUsuari)).listMyList();
    }

    public List<String> listWatchedList(String nomUsuari) {
        return Objects.requireNonNull(getUsuari(nomUsuari)).listWatchedList();
    }

    public List<String> listContinueWatching(String nomUsuari) {
        return Objects.requireNonNull(getUsuari(nomUsuari)).listContinueWatching();
    }

    public boolean marcarSerieMyList(String nomUsuari, Serie s) {
        return Objects.requireNonNull(getUsuari(nomUsuari)).marcarSerieMyList(s);
    }

    public boolean valorarEpisodi(int id, String nomUsuari, String idSerie, int idEpisodi, int estrelles, String thumb, String data) {
        return Objects.requireNonNull(getUsuari(nomUsuari)).valorarEpisodi(id, idSerie, idEpisodi, estrelles, thumb, data);
    }

    public String visualitzarEpisodi(int id, String nomUsuari, Serie serie, int idEpisodi, String estat, String data, int minutsRestants, int numTemp) {
        return Objects.requireNonNull(getUsuari(nomUsuari)).visualitzarEpisodi(id, serie, idEpisodi, estat, data, minutsRestants, numTemp);
    }

    public boolean isEpisodiValorat(String idSerie, int idEpisodi, String idUsuari) {
        return getUsuari(idUsuari).isEpisodiValorat(idSerie,idEpisodi);
    }


    public List<String> getTopTenVal(String loggedInUser) {
        return getUsuari(loggedInUser).getTopTenVal();
    }

    public List<String> getTopTenVis(String loggedInUser) {
        return getUsuari(loggedInUser).getTopTenVis();
    }

    public int getDuracioVisualitzada(String idUsuari, Episodi episodi) {
        return getUsuari(idUsuari).getDuracioVisualitzada(episodi);
    }
}
