package Business;

import Persistance.*;
import Presentation.PresentationController;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;

import static Business.Dau.*;

/**
 * Classe on farem totes les funcionalitats per tal de passar-li al controller i pugui mostrar lo convinent
 */
public class BusinessManager {

    /**
     * connexio amb el controller
     */
    private static PresentationController presentationController = new PresentationController();

    /**
     * Calcula el numero de punts dels personatges i els estableix
     * @param personatge personatge al qui calcular-li i afegir-li els punts
     */
    static void calculVida(Personatge personatge) {
        int hitPoints = 0;
        if (personatge.getTipus().equals("Campió")) {
            hitPoints = ((10 + personatge.getBody()) * (personatge.getXp() / 100 + 1)) + ((personatge.getBody()) * (personatge.getXp() / 100 + 1));
        } else {
            hitPoints = ((10 + personatge.getBody()) * (personatge.getXp() / 100 + 1));
        }
        personatge.setHitPoints(hitPoints);
        personatge.setMaxPoints(hitPoints);
    }

    /**
     * Calcul de la iniciativa de el personatge
     * @param personatge personatge a qui calcular-li la iniciativa
     * @return
     */
    static int calculIniciatica(Personatge personatge) {
        int num = daus12cares();
        int num2 = dau20cares();
        int num3 = daus10cares();
        int iniciativa = 0;

        if (personatge.getTipus().equals("Mag")) {
            iniciativa = num2 + personatge.getMind();
        } else if (personatge.getTipus().equals("Clergue") || personatge.getTipus().equals("Paladi")) {
            iniciativa = num3 + personatge.getSpirit();
        } else {
            iniciativa = num + personatge.getSpirit();
        }
        return iniciativa;
    }

    /**
     * Estableix el tipus del mal depenent del tipus que es el personatge
     * @param personatge personatge a qui buscar el tipuis d'atac
     * @return tipus d'atac
     */
    static String calculMal(Personatge personatge) {
        if (personatge.getTipus().equals("Mag")) {
            return "Magic";
        } else if (personatge.getTipus().equals("Clergue") || personatge.getTipus().equals("Paladi")) {
            return "Psiquic";
        } else {
            return "Fisic";
        }
    }

    /**
     * Cura als personatges que els hi convingui depenent del tipus de personatge que cura
     * @param personatge personatge que realitza la cura
     * @param ordre llistat de gent que participa al combat per ordre
     * @param nousCharacters tots els personatges que participen al combat
     * @return cantitat curada
     */
    static int curacioC(Personatge personatge, ArrayList<Combat> ordre, ArrayList<Personatge> nousCharacters) {
        int j = 0;
        boolean entrat = false;
        int dau10 = daus10cares();
        int curacio = 0;
        if (personatge.getTipus().equals("Clergue")) {
            while (j < ordre.size() && !entrat) {
                Combat combat = ordre.get(j);
                if (combat.getTipus().equals("Persona") && !combat.getNom().equals(personatge.getName())) {
                    if (combat.getHitPoints() < (personatge.getHitPoints() / 2)) {
                        curacio = dau10 + personatge.getMind();
                        combat.setHitPoints(combat.getHitPoints() + curacio);
                        for (int i = 0; i < nousCharacters.size(); i++) {
                            if (nousCharacters.get(i).getName().equals(combat.getNom())) {
                                nousCharacters.get(i).setHitPoints(nousCharacters.get(i).getHitPoints() + curacio);
                            }
                        }
                        entrat = true;
                    }
                }
                j++;
            }
        } else if (personatge.getTipus().equals("Paladí")) {
            j = 0;
            entrat = false;
            while (j < ordre.size() && !entrat) {
                Combat combat = ordre.get(j);
                if (combat.getTipus().equals("Persona") && !combat.getNom().equals(personatge.getName())) {
                    if (combat.getHitPoints() < (personatge.getHitPoints() / 2)) {
                        curacio = dau10 + personatge.getMind();
                        for (int i = 0; i < ordre.size(); i++) {
                            Combat aux = ordre.get(i);
                            aux.setHitPoints(aux.getHitPoints() + curacio);
                            for (int z = 0; z < nousCharacters.size(); z++) {
                                if (nousCharacters.get(z).getName().equals(aux.getNom())) {
                                    nousCharacters.get(z).setHitPoints(nousCharacters.get(z).getHitPoints() + curacio);
                                }
                            }
                        }
                        entrat = true;
                    }
                }
                j++;
            }
        }
        return curacio;
    }

    /**
     * Calcula el mal que fa el personatge depenent de quin tipus de rol te
     * @param personatge personatge a qui calcular el mal
     * @param ordre llista dels que participen a la batalla
     * @return el mal que ha fet
     */
    static int dmgClasse(Personatge personatge, ArrayList<Combat> ordre) {
        int dau6 = dau6cares();
        int dau10 = daus10cares();
        int dau8 = dau8cares();
        int dau4 = dau4cares();
        int dmg = 0;
        int monstres = 0, player = 0;

        for (int i = 0; i < ordre.size(); i++) {
            Combat combat = ordre.get(i);
            if (combat.getTipus().equals("Monster")) {
                monstres++;
            } else {
                player++;
            }
        }
        if (personatge.getTipus().equals("Adventurer")) {
            dmg = dau6 + personatge.getBody();
        } else if (personatge.getTipus().equals("Guerrer") || personatge.getTipus().equals("Campió")) {
            dmg = dau10 + personatge.getBody();
        } else if (personatge.getTipus().equals("Clergue")) {
            dmg = dau4 + personatge.getSpirit();
        } else if (personatge.getTipus().equals("Paladí")) {
            dmg = dau8 + personatge.getSpirit();
        } else if (personatge.getTipus().equals("Mag")) {
            if (monstres >= 3) {
                dmg = dau4 + personatge.getMind();
            } else {
                dmg = dau6 + personatge.getMind();
            }
        }


        return dmg;
    }

    /**
     * Busca els monstres de l'aventura que participen en un determinat enfrentament
     * @param i numero del enfrentament on ens trobem
     * @param arrayDeArray llista de llistes de monstres dividit per enfrentament
     * @param aventura aventura la cual estem creant
     * @param enfrentaments numero d'enfrentaments
     * @return la llista dels monstres d'aquest enfrentament
     */
    public static ArrayList<Monstre> checkMonsters(int i, ArrayList<ArrayList<Monstre>> arrayDeArray, JSONObject aventura, int enfrentaments){
        aventura.put("NumEnfrentaments", enfrentaments);
        ArrayList<Monstre> monstres1;
        monstres1 = arrayDeArray.get(i);
        return monstres1;
    }

    /**
     * Agafa els monstres de la API o el JSON i els guarda a una llista
     * @param data depenent si s'ha d'agafar de la API o del JSON
     * @return la llista de monstres
     * @throws IOException
     */
    public static ArrayList<Monstre>  getMonstres(int data) throws IOException {
        ArrayList<Monstre> monstres;
        monstres = MonstreDAO.llegirMonstres(data);
        return monstres;
    }

    /**
     * Agafa un llistat de personatges de la API o del JSON
     * @param data depenent si s'ha d'agafar de la API o del JSON
     * @return el llistat de personatges
     * @throws IOException
     */
    public static ArrayList<Personatge>  getPersonatges(int data) throws IOException {
        ArrayList<Personatge> personatges;
        personatges = PersnatgeDAO.llegirPersonatges(data);
        return personatges;
    }

    /**
     * Agafa un llistat de les aventures de la API o del JSON
     * @param data depenent si s'ha d'agafar de la API o del JSON
     * @return el llistat de aventures
     * @throws IOException
     */
    public static ArrayList<Aventura>  getAventures(int data) throws IOException {
        ArrayList<Aventura> aventures;
        aventures = AventuresDAO.llegirAventures(data);
        return aventures;
    }

    /**
     * Crea un array amb el tamany dels enfrentaments i dins de cada un hi fica un array de monstres buit.
     * @param enfrentaments nombre d'enfrentaments
     * @param aventura aventura que estem creant
     * @param nomAventura nom de l'aventura
     * @return la llista que hem creat
     */
    public static ArrayList<ArrayList<Monstre>> createMonstersArray(int enfrentaments, JSONObject aventura, String nomAventura){
        aventura.put("Nom", nomAventura);
        ArrayList<ArrayList<Monstre>> array = new ArrayList<>();
        for (int k = 0; k < enfrentaments; k++) {
            ArrayList<Monstre> monstres1 = new ArrayList<>();
            array.add(k, monstres1);
        }
        return array;
    }

    /**
     * Comprova que existeixi el fitxer de monstres i que no estigui buit
     * @param data depen si es API o JSON
     * @return si hi ha el arxiu o no (boolea)
     */
    public static Boolean getResult(int data){

        boolean correcte = false;
        if (data == 2) {
            presentationController.getVista().load();
            try {
                MonstreApi.llegirMonstresApi();
            } catch (Exception e) {
                e.printStackTrace();
                presentationController.getVista().errorData();
                correcte = false;
            }
        } else if (data == 1 || !correcte) {
            correcte = true;
            presentationController.getVista().load();
            try {
                MonstreJson.llegirMonstres();
                presentationController.getVista().okData();
            } catch (IOException e) {
                presentationController.getVista().noMonsters();
                correcte = false;
            }
        }
        return correcte;
    }

    /**
     * Comprova si existeix l'activitat que volem crear
     * @param data local o cloud
     * @param nomAventura nom de l'aventura que volem crear
     * @return si existeix el nom ho no
     * @throws IOException
     */
    public static boolean checkAdventure(int data, String nomAventura) throws IOException {
        ArrayList<Aventura> aventures;
        boolean existeix = false;
        Aventura c;
        aventures = AventuresDAO.llegirAventures(data);
        if (!aventures.isEmpty()) {
            for (int k = 0; k < aventures.size(); k++) {
                c = aventures.get(k);
                if (c.getNom().equals(nomAventura)) {
                    existeix = true;
                }
            }
        }
        return existeix;
    }

    /**
     * Afegim a una llista el monstre que s'ha guardat al enfrentament.
     * @param monstresAux monstre que volem guardar
     * @param quantitat quantitat de monstres com aquest
     * @param monstresJSON jsonArray on guardem els monstres
     * @param monstres1 llista on ens guardem els monstres
     * @param y numero de monstre dins l'enfrentament
     * @return
     */
    public static int addMonster(Monstre monstresAux, int quantitat, JSONArray monstresJSON, ArrayList<Monstre> monstres1, int y){
        Monstre b = new Monstre(monstresAux.getName(), quantitat);
        JSONObject monstresAv = new JSONObject();
        monstresAv.put("Nom", monstresAux.getName());
        monstresAv.put("Quantitat", quantitat);
        monstresJSON.put(y, monstresAv);
        y++;
        monstres1.add(b);
        return y;
    }

    /**
     * elimina un monstre en concret del enfrentament
     * @param num_Monstre monstre a eliminar
     * @param monstres1 llista on estan els monstres
     * @param monstresJSON jsonArray on guardem els monstres
     * @return monstre eliminat per mostrar-ho
     */
    public static Monstre deleteMonster(int num_Monstre, ArrayList<Monstre> monstres1, JSONArray monstresJSON){
        num_Monstre--;
        Monstre monstreAux = monstres1.get(num_Monstre);
        monstres1.remove(num_Monstre);
        monstresJSON.remove(num_Monstre);
        return monstreAux;
    }

    /**
     * fica el llistat de monstres dins de cada enfrentament
     * @param monstresJSON llistat monstres
     * @param arrayEnfrentaments array on tenim guardat la informacio dels enfrentaments
     * @param i numero d'enfrentament
     */
    public static void confirmMonsters(JSONArray monstresJSON, JSONArray arrayEnfrentaments, int i){
        JSONObject enfrentament = new JSONObject();
        enfrentament.put("Monstres", monstresJSON);
        arrayEnfrentaments.put(i, enfrentament);
    }

    /**
     *  guarda l'aventura
     * @param aventura JsonObject de l'aventura
     * @param arrayEnfrentaments enfrentaments de l'aventura amb tota la informació
     * @param data per guardar-ho a la API o al JSON
     * @throws IOException
     */
    public static void safeAventure(JSONObject aventura, JSONArray arrayEnfrentaments, int data) throws IOException {
        aventura.put("Enfrentaments", arrayEnfrentaments);
        AventuresDAO.safeAventures(data, aventura);
    }

    /**
     * afegir un personatge a la party
     * @param triaCharacter personatge a afegir
     * @param personatges llista de tots els personatges
     * @param nousCharacters llista dels personatges de la party
     */
    public static void addCharacter(int triaCharacter, ArrayList<Personatge> personatges, ArrayList<Personatge> nousCharacters){
        triaCharacter--;
        Personatge pAux = personatges.get(triaCharacter);
        nousCharacters.add(pAux);
    }

    /**
     * fa les accions depenen del tipus de personatge a la preparació
     * @param nousCharacters
     */
    public static void preparationStage(ArrayList<Personatge> nousCharacters){
        for (int j = 0; j < nousCharacters.size(); j++) {
            Personatge personatge = nousCharacters.get(j);
            if (personatge.getTipus().equals("Adventurer") || personatge.getTipus().equals("Guerrer")) {
                presentationController.getVista().prepaAV(personatge);
                personatge.setSpirit(personatge.getSpirit() + 1);
            } else if (personatge.getTipus().equals("Campió")) {
                presentationController.getVista().prepaChamp(personatge);
                for (int k = 0; k < nousCharacters.size(); k++) {
                    Personatge aux = nousCharacters.get(k);
                    aux.setSpirit(aux.getSpirit() + 1);
                }
            } else if (personatge.getTipus().equals("Clergue")) {
                presentationController.getVista().prepaClrg(personatge);
                for (int k = 0; k < nousCharacters.size(); k++) {
                    Personatge aux = nousCharacters.get(k);
                    aux.setMind(aux.getMind() + 1);
                }
            } else if (personatge.getTipus().equals("Paladí")) {
                int num = dau3cares();
                presentationController.getVista().prepaPldi(personatge, num);
                for (int k = 0; k < nousCharacters.size(); k++) {
                    Personatge aux = nousCharacters.get(k);
                    aux.setMind(aux.getMind() + num);
                }
            } else if (personatge.getTipus().equals("Mag")) {

            }
        }
    }

    /**
     * ordena els monstres i els personatges depenent de la iniciativa per lluitar
     * @param nousCharacters array de personatges
     * @param arrayDeArray array de monstres
     * @param i enfrentament en el que ens trobem
     * @param monstres llistat de tots els monstres
     * @return un array ordenat dels monstres i personatges
     */
    public static ArrayList<Combat> orderCombat(ArrayList<Personatge> nousCharacters, ArrayList<ArrayList<Monstre>> arrayDeArray, int i, ArrayList<Monstre> monstres){

        ArrayList<Monstre> monstresAventura = arrayDeArray.get(i);
        int iniciativa;
        for (int j = 0; j < monstresAventura.size(); j++) {
            Monstre monstre = monstresAventura.get(j);
            for (int k = 0; k < monstre.getQuantitat(); k++) {
                Monstre b = new Monstre(monstre.getName());
                monstresAventura.add(b);
            }
        }
        ArrayList<Combat> ordre = new ArrayList<>();
        for (int j = 0; j < monstresAventura.size(); j++) {
            Monstre b = monstresAventura.get(j);
            for (int k = 0; k < monstres.size(); k++) {
                Monstre general = monstres.get(k);
                if (b.getName().equals(general.getName())) {
                    b.setHitPoints(general.getHitPoints());
                    b.setExperience(general.getExperience());
                }
            }

            for (int k = 0; k < b.getQuantitat(); k++) {
                int num = daus12cares();
                iniciativa = num + b.getInitiative();
                Combat aux = new Combat(iniciativa, b.getName(), "Monster", b.getHitPoints(), b.getExperience(), true, b.getDamageType());
                ordre.add(aux);
            }
        }

        for (int j = 0; j < nousCharacters.size(); j++) {

            Personatge personatge = nousCharacters.get(j);
            calculVida(personatge);
            iniciativa = calculIniciatica(personatge);
            String mal = calculMal(personatge);

            Combat aux = new Combat(iniciativa, personatge.getName(), "persona", personatge.getHitPoints(), personatge.getXp(), true, mal);
            ordre.add(aux);
        }
        return ordre;
    }

    /**
     * fa totes les accions que es fan durant la batalla
     * @param ordre llista dels participants a la lluita
     * @param monstres llista dels monstres
     * @param nousCharacters llista dels personatges
     * @param nPlayers numero de personatges
     * @param nMonstres numero de monstres
     * @param xp experiencia
     * @return xp, nPlayers i nMonstres
     */
    public static Resultat combatStage(ArrayList<Combat> ordre, ArrayList<Monstre> monstres, ArrayList<Personatge> nousCharacters, int nPlayers, int nMonstres, int xp){

        for (int k = 0; k < ordre.size(); k++) {
            int impacte = daus10cares();
            int mult = 1;
            if (impacte == 1) {
                mult = 0;
            } else if (impacte == 10) {
                mult = 2;
            }
            Combat combat = ordre.get(k);
            if (combat.getTipus().equals("Monster") && combat.isAlive()) {
                for (int l = 0; l < monstres.size(); l++) {
                    Monstre monstre = monstres.get(l);
                    if (combat.getNom().equals(monstre.getName())) {
                        int dmg = daumonstre(Integer.parseInt(monstre.getDamageDice().substring(1)));
                        int jugadorAAtacar;
                        boolean entrat = false;
                        if (monstre.getChallenge().equals("Boss")) {
                            List<String> jugadors = new ArrayList<>();
                            for (int j = 0; j < ordre.size(); j++) {
                                Combat player = ordre.get(j);
                                if (player.getTipus().equals("persona") /*&& player.getHitPoints() > 0*/) {
                                    player.setHitPoints(player.getHitPoints() - (dmg * mult));
                                    for (int m = 0; m < nousCharacters.size(); m++) {
                                        if (nousCharacters.get(m).getName().equals(player.getNom())) {
                                            nousCharacters.get(m).setHitPoints(nousCharacters.get(m).getHitPoints() - dmg);
                                            if (nousCharacters.get(m).getHitPoints() < 0) {
                                                nousCharacters.get(m).setHitPoints(0);
                                                player.setHitPoints(player.getHitPoints() - (dmg * mult));
                                            }
                                        }
                                    }
                                    jugadors.add(player.getNom());
                                }
                            }
                            StringBuilder sb = new StringBuilder();
                            for (int j = 0; j < jugadors.size(); j++) {
                                if (j == 0) {
                                    sb.append(jugadors.get(j));
                                } else if (j == jugadors.size() - 1) {
                                    sb.append(" y ").append(jugadors.get(j));
                                } else {
                                    sb.append(", ").append(jugadors.get(j));
                                }
                            }
                            presentationController.getVista().bossAttack(monstre, sb, dmg);
                            for (int j = 0; j < ordre.size(); j++) {
                                Combat player = ordre.get(j);
                                if (player.getHitPoints() <= 0 && player.getTipus().equals("persona") && player.isAlive()) {
                                    presentationController.getVista().inconcient(player);
                                    player.setHitPoints(0);
                                    player.setAlive(false);
                                    nPlayers--;
                                }
                            }
                        } else {
                            do {
                                jugadorAAtacar = daumonstre(ordre.size());
                                Combat player = ordre.get(jugadorAAtacar);
                                if (player.getTipus().equals("persona") && player.getHitPoints() > 0) {
                                    player.setHitPoints(player.getHitPoints() - (dmg * mult));
                                    for (int j = 0; j < nousCharacters.size(); j++) {
                                        if (nousCharacters.get(j).equals(player.getNom())) {
                                            nousCharacters.get(j).setHitPoints(nousCharacters.get(j).getHitPoints() - (dmg * mult));
                                            player.setHitPoints(player.getHitPoints() - (dmg * mult));
                                            if (nousCharacters.get(j).getHitPoints() < 0) {
                                                nousCharacters.get(j).setHitPoints(0);
                                            }
                                        }
                                    }
                                    entrat = true;
                                }
                            } while (!entrat);

                            Combat player = ordre.get(jugadorAAtacar);
                            presentationController.getVista().monsterAttack(monstre, player, dmg);
                            if (player.getHitPoints() <= 0 && player.isAlive()) {
                                presentationController.getVista().inconcient(player);
                                player.setHitPoints(0);
                                player.setAlive(false);
                                nPlayers--;
                            }
                        }
                    }
                }
            } else if (combat.getTipus().equals("persona") && combat.isAlive()) {
                for (int j = 0; j < nousCharacters.size(); j++) {
                    Personatge personatge = nousCharacters.get(j);
                    if (combat.getNom().equals(personatge.getName()) && combat.getHitPoints() > 0) {
                        int dmg = 0, curacio = 0;
                        boolean entrat = false;
                        int monstreAAtacar = 0;

                        curacio = curacioC(personatge, ordre, nousCharacters);

                        if (curacio != 0) {

                        } else {
                            dmg = dmgClasse(personatge, ordre) * mult;
                            if (personatge.getTipus().equals("Guerrer") || personatge.getTipus().equals("Campió")) {
                                int vidamin = 10000;
                                for (int l = 0; l < ordre.size(); l++) {
                                    Combat monstre = ordre.get(l);
                                    if (monstre.getTipus().equals("Monster")) {
                                        if (monstre.getHitPoints() < vidamin) {
                                            vidamin = monstre.getHitPoints();
                                            monstreAAtacar = l;
                                        }
                                    }
                                }
                            } else {
                                do {
                                    monstreAAtacar = daumonstre(ordre.size());
                                    Combat monstre = ordre.get(monstreAAtacar);
                                    if (monstre.getTipus().equals("Monster")) {
                                        if (monstre.getTipus().equals("Boss")) {
                                            if (monstre.getMal().equals(combat.getMal())) {
                                                dmg = dmg / 2;
                                            }
                                        }
                                        entrat = true;
                                    }
                                } while (!entrat);
                            }
                            Combat monstre = ordre.get(monstreAAtacar);
                            monstre.setHitPoints(monstre.getHitPoints() - dmg);

                            presentationController.getVista().personAttack(personatge, monstre, dmg);
                            if (monstre.getHitPoints() <= 0 && monstre.isAlive()) {
                                presentationController.getVista().dies(monstre);
                                monstre.setAlive(false);
                                nMonstres = ordre.size() - nousCharacters.size();
                                xp += monstre.getXp();
                            }
                        }
                    }
                }
            }
            //}
        }
        Resultat resultat = new Resultat(nPlayers, nMonstres, xp);
        return  resultat;
    }

    /**
     * acabem la batalla mirant els punts de vida dels personatges
     * @param ordre llistat de els participants per ordre
     * @param nousCharacters llistat dels personatges
     */
    public static void endBattle(ArrayList<Combat> ordre, ArrayList<Personatge> nousCharacters){
        for (int j = 0; j < ordre.size(); j++) {
            Combat aux = ordre.get(j);
            for (int k = 0; k < nousCharacters.size(); k++) {
                Personatge personatge = nousCharacters.get(k);
                if (aux.getNom().equals(personatge.getName())) {
                    if (aux.getHitPoints() > 0) {
                        int dau = dau8cares();
                        int cura = dau + personatge.getMind();
                        if ((aux.getHitPoints() + cura) > personatge.getMaxPoints()) {
                            aux.setHitPoints(personatge.getMaxPoints());
                        } else {
                            aux.setHitPoints(aux.getHitPoints() + cura);
                        }
                        presentationController.getVista().heals(aux, cura);
                    } else {
                        presentationController.getVista().unconscious(aux);
                    }
                }
            }
        }
    }

    /**
     * printa els personatges que tenim
     * @param data agafar-los de la API o el JSON
     * @param nomJugador nom del jugador que volem editar
     * @return posicio del jugador dins la llista
     * @throws IOException
     */
    public static int printPersons(int data, String nomJugador) throws IOException {
        int x = 1;
        ArrayList<Personatge> personatges;

        personatges = PersnatgeDAO.llegirPersonatges(data);

        Personatge personatgeAux = null;
        for (int i = 0; i < personatges.size(); i++) {
            personatgeAux = personatges.get(i);
            if (personatgeAux.getPlayer().contains(nomJugador)) {
                presentationController.getVista().llistarPerson(x, personatgeAux);
                x++;
            }
        }
        return x;
    }

    /**
     * Mostra els personatges que tenim i extreu el seleccionat
     * @param numPersonatge personatge a extreure
     * @param data API o JSON
     * @param nomJugador nom del jugador a extreure
     * @return personatge a eliminar
     * @throws IOException
     */
    public static Personatge showPerson(int numPersonatge, int data, String nomJugador) throws IOException {

        Personatge personatgeAux = null;

        ArrayList<Personatge> personatges;

        personatges = PersnatgeDAO.llegirPersonatges(data);

        if (numPersonatge == 0) {

        } else {
            int i = 0;
            int j = 0;
            while (i != numPersonatge) {
                personatgeAux = personatges.get(j);
                if (personatgeAux.getPlayer().contains(nomJugador)) {
                    i++;
                    j++;
                } else {
                    j++;
                }
            }
            j--;
            personatgeAux = personatges.get(j);
        }
        return personatgeAux;
    }

    /**
     * borra un personatge
     * @param nomEliminar nom del personatge a eliminar
     * @param personatgeAux personatges que tenim
     * @param data eliminar de la api o del JSON
     * @throws IOException
     */
    public static void deletePerson(String nomEliminar, Personatge personatgeAux, int data) throws IOException {

        if (nomEliminar.isEmpty()) {

        } else if (nomEliminar.equals(personatgeAux.getName())) {
            presentationController.getVista().personEliminat(personatgeAux);
            PersnatgeDAO.borrarPersonatge(data, nomEliminar);
        }
    }

    /**
     * Calcula tots els parametres dels personatges i els afegeix on toqui
     * @param nivell nivell del personatge
     * @param data guardar a la API o al JSON
     * @param nom nom del personatge
     * @param jugador jugador que el crea
     * @param scanner scanner per llegir el que introdueix l'usuari
     * @throws IOException
     */
    public static void cas1(int nivell, int data, String nom, String jugador, Scanner scanner) throws IOException {
        ArrayList<Integer> nums;

        ArrayList<Integer> numeros = new ArrayList<>();
        nums = daus6cares();
        int dau1 = nums.get(0);
        int dau2 = nums.get(1);
        int sum = dau1 + dau2;
        numeros.add(sum);

        presentationController.getVista().stBody(sum, dau1, dau2);
        nums = daus6cares();
        dau1 = nums.get(0);
        dau2 = nums.get(1);
        sum = dau1 + dau2;
        numeros.add(sum);

        presentationController.getVista().stMind(sum, dau1, dau2);
        nums = daus6cares();
        dau1 = nums.get(0);
        dau2 = nums.get(1);
        sum = dau1 + dau2;
        numeros.add(sum);

        presentationController.getVista().stSpirit(sum, dau1, dau2);

        for (int i = 0; i < 3; i++) {
            if (numeros.get(i) == 2) {
                numeros.set(i, -1);
            } else if (numeros.get(i) >= 3 && numeros.get(i) <= 5) {
                numeros.set(i, 0);
            } else if (numeros.get(i) >= 6 && numeros.get(i) <= 9) {
                numeros.set(i, 1);
            } else if (numeros.get(i) >= 10 && numeros.get(i) <= 11) {
                numeros.set(i, 2);
            } else if (numeros.get(i) == 12) {
                numeros.set(i, 3);
            }
        }

        presentationController.getVista().stats(numeros);
        int Body = numeros.get(0);
        int Mind = numeros.get(1);
        int Spirit = numeros.get(2);

        presentationController.getVista().chClass();
        String tipus = scanner.nextLine();

        Personatge a = new Personatge(nom, jugador, (nivell * 99), Body, Mind, Spirit, tipus);

        if(data == 1){
            PersonatgeJson.addCharacter(a);
        }else{
            PersonatgeApi.addCharacterApi(a);
        }
    }

    /**
     * Evoluciona el personatge segons el nivell
     * @param aux personatge a evolucionar
     */
    public static void evolve(Personatge aux){

        if(aux.getTipus().equals("Aventurer")){
            if((aux.getXp() / 101) == 4){
                aux.setTipus("Guerrer");
                presentationController.getVista().evolve2(aux);
            }
        }else if(aux.getTipus().equals("Guerrer")){
            if((aux.getXp() / 101) == 8) {
                aux.setTipus("Campió");
                presentationController.getVista().evolve1(aux);
            }
        }else if(aux.getTipus().equals("Clergue")){
            if((aux.getXp() / 101) == 5){
                aux.setTipus("Paladí");
                presentationController.getVista().evolve3(aux);
            }
        }
    }
}
