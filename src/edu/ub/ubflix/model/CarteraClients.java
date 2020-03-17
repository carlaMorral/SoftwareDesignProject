package edu.ub.ubflix.model;

import java.util.*;
import java.util.regex.Pattern;

public class CarteraClients {

    private static final Pattern passwordPattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$");
    private static final Pattern dniPattern = Pattern.compile("\\d{8}[A-Z]");
    private Map<String, Client> clients;

    public CarteraClients() {
        clients = new HashMap<>();
    }

    public void init(List<Client> allClients, List<Usuari> allUsuaris, List<Valoracio> allValoracions, List<Visualitzacio> allVisualitzacions) {

        for (Client c : allClients) {
            for(Usuari u : allUsuaris){

                if(u.getIdClient().equals(c.getIdClient()))
                    c.addUser(u);

                List<Valoracio> belongingVal = new ArrayList<>();
                List<Visualitzacio> belongingVis = new ArrayList<>();

                for(Valoracio v: allValoracions){
                    if(v.getIdClient().equals(u.getIdClient()) && v.getIdUsuari()==u.getIdUsuari()){
                        belongingVal.add(v);
                    }
                }

                for(Visualitzacio v: allVisualitzacions){
                    if(v.getIdUsuari()==u.getIdUsuari()){
                        belongingVis.add(v);
                    }
                }

                u.setValoracions(belongingVal);
                u.setVisualitzacions(belongingVis);
            }
            clients.put(c.getIdClient(), c);
        }
    }

    public Client getClient(String idClient) {
        return Objects.requireNonNull(clients.get(idClient), "Client not found");
    }

    private boolean isValidClientName(String clientName) {
        return !clients.containsKey(clientName);
    }

    private boolean isValidPassword(String password) {
        return passwordPattern.matcher(password).matches();
    }

    private boolean isValidDni(String dni) {
        char[] dniLetters = "TRWAGMYFPDXBNJZSQVHLCKE".toCharArray();
        return dniPattern.matcher(dni).matches() &&
                dniLetters[Integer.parseInt(dni.substring(0, 8)) % 23] == dni.charAt(8);
    }

    public int addClient(String idClient, String pwd, String name, String dni, String address, boolean vip) {
        if (!isValidClientName(idClient)) return -1;
        if (!isValidPassword(pwd)) return -2;
        if (!isValidDni(dni)) return -3;
        for (Client c : clients.values()) {
            if (c.getDni().equals(dni)) return -4;
        }
        Client c = new Client(idClient, pwd, name, dni, address, vip);
        clients.put(idClient, c);
        return 0;
    }

    public int addUser(String idClient, String userName, int idUsuari) {
        return getClient(idClient).addUser(userName, idUsuari);
    }

    public List<String> listUsuaris(String idClient) {
        return getClient(idClient).listUsuaris();
    }

    public String veurePerfil(String idClient, int idUsuari) {
        return getClient(idClient).veurePerfil(idUsuari);
    }

    public List<String> listMyList(String idClient, String nomUsuari) {
        return getClient(idClient).listMyList(nomUsuari);
    }

    public List<String> listWatchedList(String idClient, String nomUsuari) {
        return getClient(idClient).listWatchedList(nomUsuari);
    }

    public List<String> listContinueWatching(String idClient, String nomUsuari) {
        return getClient(idClient).listContinueWatching(nomUsuari);
    }

    public boolean marcarSerieMyList(String idClient, String nomUsuari, Serie s) {
        return getClient(idClient).marcarSerieMyList(nomUsuari, s);
    }

    public void valorarEpisodi(int id, String idClient, String nomUsuari, String idSerie, int idEpisodi, int estrelles, String thumb, String data) {
        getClient(idClient).valorarEpisodi(id, nomUsuari, idSerie, idEpisodi, estrelles, thumb, data);
    }

    public void visualitzarEpisodi(int id, String idClient, String nomUsuari, Serie serie, int idEpisodi, String estat,
                                   String data, int minutsRestants, int numTemp) {
        getClient(idClient).visualitzarEpisodi(id, nomUsuari, serie, idEpisodi, estat, data, minutsRestants, numTemp);
    }

    public boolean isEpisodiValorat(String idSerie, int numTemporada, int idEpisodi, String idClient, String idUsuari) {
        return getClient(idClient).isEpisodiValorat(idSerie,idEpisodi,idUsuari);
    }

    public List<String> getTopTenVal(String loggedInClient, String loggedInUser) {
        return getClient(loggedInClient).getTopTenVal(loggedInUser);
    }

    public List<String> getTopTenVis(String loggedInClient, String loggedInUser) {
        return getClient(loggedInClient).getTopTenVis(loggedInUser);
    }

    public boolean validateClient(String username, String password) {
        return this.clients.containsKey(username) && this.clients.get(username).getPwd().equals(password);
    }

    public int getDuracioVisualitzada(String idClient, String idUsuari, Episodi episodi) {
        return getClient(idClient).getDuracioVisualitzada(idUsuari, episodi);
    }
}
