package Presentation;

import Business.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;

import static Business.Dau.dau3cares;

public class PresentationController {

    private static Vista vista = null;

    public PresentationController() {
        this.vista = new Vista();
    }

    public static Vista getVista() {
        return vista;
    }

    public static void run() throws IOException {
        Scanner scanner = new Scanner(System.in);
        JSONArray arrayEnfrentaments = new JSONArray();
        ArrayList<ArrayList<Monstre>> arrayDeArray = null;

        getVista().pantallaInicial();
        int data = 0;
        boolean ok = true;
        try {
            data = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            getVista().invalidData();
            ok = false;
        }
        while (data > 2 || data < 1 || !ok) {
            getVista().invalidData2();
            try {
                data = Integer.parseInt(scanner.nextLine());
                ok = true;
            } catch (Exception e) {
                getVista().invalidData();
                ok = false;
            }
        }
        ok = BusinessManager.getResult(data);
        if (ok) {
            int opcio = 0;
            do {
                getVista().menu();
                opcio = Integer.parseInt(scanner.nextLine());
                switch (opcio) {
                    case 1:
                        cas1(scanner, data);
                        break;
                    case 2:
                        cas2(scanner, data);
                        break;
                    case 3:
                        boolean existeix = false;
                        String nomAventura;
                        getVista().createAventura();
                        nomAventura = scanner.nextLine();
                        existeix = BusinessManager.checkAdventure(data, nomAventura);

                        if (existeix) {
                            getVista().existAdventure();
                        } else {
                            getVista().encounters(nomAventura);

                            int sumErrors = 0;
                            int enfrentaments = 0;
                            boolean okay = true;

                            try {
                                enfrentaments = Integer.parseInt(scanner.nextLine());
                            } catch (Exception e) {
                                getVista().errorEncounters();
                                okay = false;
                            }
                            sumErrors++;
                            while ((enfrentaments > 4 || enfrentaments < 1 || !okay) && sumErrors < 3) {
                                getVista().errorEncounters2();
                                try {
                                    enfrentaments = Integer.parseInt(scanner.nextLine());
                                    okay = true;
                                    sumErrors++;
                                } catch (Exception e) {
                                    getVista().errorEncounters();
                                    okay = false;
                                    sumErrors++;
                                }
                            }
                            JSONObject aventura = new JSONObject();
                            arrayDeArray = BusinessManager.createMonstersArray(enfrentaments, aventura, nomAventura);
                            if (sumErrors < 3) {
                                getVista().sumEncounters(enfrentaments);

                                int i = 0;
                                int j = 1;
                                int y = 0;
                                int opcio2 = 0;

                                do {
                                    JSONArray monstresJSON = new JSONArray();
                                    do {
                                        ArrayList<Monstre> monstres1;
                                        getVista().encounter(j, enfrentaments);
                                        monstres1 = BusinessManager.checkMonsters(i, arrayDeArray, aventura, enfrentaments);
                                        if (monstres1.isEmpty()) {
                                            getVista().empty();
                                        } else {
                                            for (int k = 0; k < monstres1.size(); k++) {
                                                Monstre monstresAux = monstres1.get(k);
                                                getVista().showMonsters(k, monstresAux);
                                            }
                                        }
                                        getVista().menuEncounter();
                                        ok = true;
                                        try {
                                            opcio2 = Integer.parseInt(scanner.nextLine());
                                        } catch (Exception e) {
                                            getVista().errormenu();
                                            ok = false;
                                        }
                                        while (opcio2 > 3 || opcio2 < 1 || !ok) {
                                            getVista().errormenu2();
                                            try {
                                                opcio2 = Integer.parseInt(scanner.nextLine());
                                                ok = true;
                                            } catch (Exception e) {
                                                getVista().errormenu();
                                                ok = false;
                                            }
                                        }
                                        switch (opcio2) {
                                            case 1:
                                                ArrayList<Monstre> monstres;
                                                monstres = BusinessManager.getMonstres(data);
                                                for (int k = 0; k < monstres.size(); k++) {
                                                    Monstre monstresAux = monstres.get(k);
                                                    getVista().showAllMonsters(k, monstresAux);
                                                }
                                                getVista().chooseMonster(monstres);
                                                int numMonstre = 0;
                                                ok = true;
                                                try {
                                                    numMonstre = Integer.parseInt(scanner.nextLine());
                                                } catch (Exception e) {
                                                    getVista().errorMonsters();
                                                    ok = false;
                                                }
                                                while (numMonstre > monstres.size() || numMonstre < 1 || !ok) {
                                                    getVista().errorMonsters2();
                                                    try {
                                                        numMonstre = Integer.parseInt(scanner.nextLine());
                                                        ok = true;
                                                    } catch (Exception e) {
                                                        getVista().errorMonsters();
                                                        ok = false;
                                                    }
                                                }
                                                numMonstre--;
                                                Monstre monstresAux = monstres.get(numMonstre);
                                                getVista().monstersQuantity(monstresAux);
                                                int quantitat = 0;
                                                ok = true;
                                                try {
                                                    quantitat = Integer.parseInt(scanner.nextLine());
                                                } catch (Exception e) {
                                                    getVista().errorMonsters();
                                                    ok = false;
                                                }
                                                while (quantitat > monstres.size() || quantitat < 1 || !ok) {
                                                    getVista().errorMonsters2();
                                                    try {
                                                        quantitat = Integer.parseInt(scanner.nextLine());
                                                        ok = true;
                                                    } catch (Exception e) {
                                                        getVista().errorMonsters();
                                                        ok = false;
                                                    }
                                                }
                                                y = BusinessManager.addMonster(monstresAux, quantitat, monstresJSON, monstres1, y);
                                                break;
                                            case 2:
                                                ArrayList<Monstre> monstresa;
                                                monstresa = BusinessManager.getMonstres(data);
                                                getVista().deleteMonster();
                                                int num_Monstre = 0;
                                                ok = true;
                                                try {
                                                    num_Monstre = Integer.parseInt(scanner.nextLine());
                                                } catch (Exception e) {
                                                    getVista().errorMonsters();
                                                    ok = false;
                                                }
                                                while (num_Monstre > monstresa.size() || num_Monstre < 1 || !ok) {
                                                    getVista().errorMonsters2();
                                                    try {
                                                        num_Monstre = Integer.parseInt(scanner.nextLine());
                                                        ok = true;
                                                    } catch (Exception e) {
                                                        getVista().errorMonsters();
                                                        ok = false;
                                                    }
                                                }
                                                Monstre monstreAux = BusinessManager.deleteMonster(num_Monstre, monstres1, monstresJSON);
                                                getVista().monsterDeleted(monstreAux);
                                                break;
                                            case 3:

                                                BusinessManager.confirmMonsters(monstresJSON, arrayEnfrentaments, i);
                                                y = 0;
                                                i++;
                                                j++;
                                                break;
                                        }
                                    } while (opcio2 != 3);
                                } while (i < enfrentaments);
                                getVista().endCase3(nomAventura);
                                BusinessManager.safeAventure(aventura, arrayEnfrentaments, data);
                            } else {
                                getVista().muchTries();
                            }
                        }
                        break;
                    case 4:
                        ArrayList<Personatge> personatges;
                        personatges = BusinessManager.getPersonatges(data);
                        getVista().startAdventure();
                        ArrayList<Aventura> aventures;
                        aventures = BusinessManager.getAventures(data);
                        if (personatges.size() < 3) {
                            getVista().errorPersonatges();
                        } else {

                            for (int k = 0; k < aventures.size(); k++) {
                                Aventura z = aventures.get(k);
                                getVista().aventures(k, z);
                            }
                        }
                        getVista().chooseAdv();
                        int numAventura = 0;
                        ok = true;
                        try {
                            numAventura = Integer.parseInt(scanner.nextLine());
                        } catch (Exception e) {
                            getVista().errorAdv();
                            ok = false;
                        }
                        while (numAventura > aventures.size() || numAventura < 1 || !ok) {
                            getVista().errorAdv2();
                            try {
                                numAventura = Integer.parseInt(scanner.nextLine());
                                ok = true;
                            } catch (Exception e) {
                                getVista().errorAdv();
                                ok = false;
                            }
                        }
                        numAventura--;
                        Aventura nomAcenturaAux = aventures.get(numAventura);
                        getVista().confirmAdv(nomAcenturaAux);

                        int numCharacters = 0;
                        ok = true;
                        try {
                            numCharacters = Integer.parseInt(scanner.nextLine());
                        } catch (Exception e) {
                            getVista().errorAdv();
                            ok = false;
                        }
                        while (numCharacters > 5 || numCharacters < 3 || !ok) {
                            getVista().errorAdv2();
                            try {
                                numCharacters = Integer.parseInt(scanner.nextLine());
                                ok = true;
                            } catch (Exception e) {
                                getVista().errorAdv();
                                ok = false;
                            }
                        }
                        getVista().confirmChar(numCharacters);

                        ArrayList<Personatge> nousCharacters = new ArrayList<>();

                        for (int i = 0; i < numCharacters; i++) {
                            getVista().indexParty(i, numCharacters);
                            for (int j = 0; j < numCharacters; j++) {
                                if (nousCharacters.size() < (j + 1)) {
                                    getVista().emptyParty(j);
                                } else {
                                    Personatge charactersAux = nousCharacters.get(j);
                                    getVista().printchar(j, charactersAux);
                                }
                            }
                            getVista().charAvailable();
                            for (int j = 0; j < personatges.size(); j++) {
                                Personatge pAux = personatges.get(j);
                                getVista().charList(j, pAux);
                            }
                            getVista().chooseChar(i);
                            int triaCharacter = 0;
                            ok = true;
                            try {
                                triaCharacter = Integer.parseInt(scanner.nextLine());
                            } catch (Exception e) {
                                getVista().errorAdv();
                                ok = false;
                            }
                            while (triaCharacter > personatges.size() || triaCharacter < 1 || !ok) {
                                getVista().errorAdv2();
                                try {
                                    triaCharacter = Integer.parseInt(scanner.nextLine());
                                    ok = true;
                                } catch (Exception e) {
                                    getVista().errorAdv();
                                    ok = false;
                                }
                            }
                            BusinessManager.addCharacter(triaCharacter, personatges, nousCharacters);
                        }
                        getVista().indexPartyEnd(numCharacters);
                        for (int j = 0; j < numCharacters; j++) {
                            if (nousCharacters.size() < (j + 1)) {
                                getVista().emptyParty(j);
                            } else {
                                Personatge charactersAux = nousCharacters.get(j);
                                getVista().printchar(j, charactersAux);
                            }
                        }
                        ArrayList<Monstre> monstres;
                        monstres = BusinessManager.getMonstres(data);
                        getVista().startAdventure(nomAcenturaAux);
                        for (int i = 0; i < nomAcenturaAux.getEnfrentaments(); i++) {
                            getVista().startEncounter(i);
                            ArrayList<Monstre> monstres1;
                            monstres1 = arrayDeArray.get(i);
                            for (int j = 0; j < monstres1.size(); j++) {
                                Monstre monstreAux = monstres1.get(j);
                                getVista().monstresEncounter(monstreAux);
                            }
                            getVista().prepariationStage();
                            BusinessManager.preparationStage(nousCharacters);
                            ArrayList<Combat> ordre = BusinessManager.orderCombat(nousCharacters, arrayDeArray, i, monstres);

                            getVista().rollIniciative();
                            Collections.sort(ordre, Comparator.comparingInt(Combat::getIniciativa));
                            Collections.reverse(ordre);

                            for (int j = 0; j < ordre.size(); j++) {
                                Combat ordreAux = ordre.get(j);
                                getVista().printInciative(ordreAux);
                            }
                            getVista().combatStage();

                            int z = 1;
                            int nMonstres = ordre.size() - nousCharacters.size();
                            int nPlayers = nousCharacters.size();
                            int sumHitPoints = 0;
                            int xp = 0;
                            do {
                                getVista().round(z);
                                for (int j = 0; j < nousCharacters.size(); j++) {
                                    Personatge personatge = nousCharacters.get(j);
                                    getVista().personHealth(personatge);
                                }

                                Resultat resultat = BusinessManager.combatStage(ordre, monstres, nousCharacters, nPlayers, nMonstres, xp);
                                nPlayers = resultat.getnPlayers();
                                nMonstres = resultat.getnMonstres();
                                xp = resultat.getXp();
                                getVista().end(z);
                                z++;
                            } while (nMonstres > 0 && nPlayers > 0);
                            getVista().defeated();
                            if (sumHitPoints > 0) {
                                getVista().restStage();
                                for (int j = 0; j < nousCharacters.size(); j++) {
                                    Personatge aux = nousCharacters.get(j);
                                    aux.setXp(aux.getXp() + xp);
                                    getVista().gainXp(aux, xp);
                                    BusinessManager.evolve(aux);
                                }
                                BusinessManager.endBattle(ordre, nousCharacters);
                            }
                        }
                        break;

                    case 5:
                        getVista().leave();
                        break;

                    default:
                        getVista().invalid();
                        break;
                }
            } while (opcio != 5);
        }
    }

    private static void cas2(Scanner scanner, int data) throws IOException {
        boolean ok;
        getVista().filterName();
        String nomJugador = scanner.nextLine();
        if (nomJugador.isEmpty()) {
            getVista().tots();
        } else {
            getVista().alguns();
        }
        int x = BusinessManager.printPersons(data, nomJugador);
        getVista().back();
        x--;
        getVista().triaPerson(x);
        int numPersonatge = 0;
        ok = true;
        try {
            numPersonatge = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            getVista().errorTriar();
            ok = false;
        }
        while (numPersonatge > x || numPersonatge < 0 || !ok) {
            getVista().errorTriar2();
            try {
                numPersonatge = Integer.parseInt(scanner.nextLine());
                ok = true;
            } catch (Exception e) {
                getVista().errorTriar();
                ok = false;
            }
        }

        Personatge personatge = BusinessManager.showPerson(numPersonatge, data, nomJugador);
        getVista().eliminarPerson(personatge);
        String nomEliminar = scanner.nextLine();
        BusinessManager.deletePerson(nomEliminar, personatge, data);
    }

    private static void cas1(Scanner scanner, int data) throws IOException {
        boolean ok;
        getVista().enterName();
        String nom = scanner.nextLine();
        getVista().confirmName(nom);
        String jugador = scanner.nextLine();
        getVista().checkLvl();
        int nivell = 0;
        ok = true;
        try {
            nivell = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            getVista().errorLvl();
            ok = false;
        }
        while (nivell > 10 || nivell < 1 || !ok) {
            getVista().errorLvl2();
            try {
                nivell = Integer.parseInt(scanner.nextLine());
                ok = true;
            } catch (Exception e) {
                getVista().errorLvl();
                ok = false;
            }
        }
        getVista().okLvl(nivell);
        BusinessManager.cas1(nivell, data, nom, jugador, scanner);
        getVista().personatgeCreat(nom);
    }
}
