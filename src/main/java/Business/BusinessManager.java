package Business;

import Persistance.MonstreJson;
import Persistance.PersonatgeJson;
import Presentation.PresentationController;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

import static Business.Dau.*;
import static Persistance.AventuraJson.escriureAventura;

public class BusinessManager {

    PresentationController presentationController = new PresentationController();

    Scanner scanner = new Scanner(System.in);

    public BusinessManager() {
    }

    static void calculVida(Personatge personatge) {
        int hitPoints = 0;
        if (personatge.getTipus().equals("Campio")) {
            hitPoints = ((10 + personatge.getBody()) * (personatge.getXp() / 100 + 1)) + ((personatge.getBody()) * (personatge.getXp() / 100 + 1));
        } else {
            hitPoints = ((10 + personatge.getBody()) * (personatge.getXp() / 100 + 1));
        }
        personatge.setHitPoints(hitPoints);
        personatge.setMaxPoints(hitPoints);
    }

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

    static String calculMal(Personatge personatge) {
        if (personatge.getTipus().equals("Mag")) {
            return "Magic";
        } else if (personatge.getTipus().equals("Clergue") || personatge.getTipus().equals("Paladi")) {
            return "Psiquic";
        } else {
            return "Fisic";
        }
    }

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
        } else if (personatge.getTipus().equals("Paladi")) {
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
        } else if (personatge.getTipus().equals("Guerrer") || personatge.getTipus().equals("Campio")) {
            dmg = dau10 + personatge.getBody();
        } else if (personatge.getTipus().equals("Clergue")) {
            dmg = dau4 + personatge.getSpirit();
        } else if (personatge.getTipus().equals("Paladi")) {
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

    public void run () throws IOException, NoSuchAlgorithmException {
        boolean correcte = true;
        Scanner scanner = new Scanner(System.in);
        int opcio = 0;
        int y = 0;
        ArrayList<Monstre> monstres = null;
        ArrayList<Personatge> personatges = null;
        ArrayList<Aventura> aventures = new ArrayList<>();
        ArrayList<Integer> nums = null;
        ArrayList<ArrayList<Monstre>> arrayDeArray = new ArrayList<>();
        JSONArray arrayEnfrentaments = new JSONArray();
        int enfrentaments = 0;


        presentationController.getUiController().pantallaInicial();
        int data = 0;
        boolean ok = true;
        try {
            data = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            presentationController.getUiController().invalidData();
            ok = false;
        }
        while (data > 2 || data < 1 || !ok) {
            presentationController.getUiController().invalidData2();
            try {
                data = Integer.parseInt(scanner.nextLine());
                ok = true;
            } catch (Exception e) {
                presentationController.getUiController().invalidData();
                ok = false;
            }
        }
        if (data == 2) {
            presentationController.getUiController().load();
            try {
                URL url = new URL("https://balandrau.salle.url.edu/dpoo/shared/monsters");
                //URL url = new URL("https://monet.cat/posts/fromfeed2");

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setConnectTimeout(10000);
                connection.setReadTimeout(10000);

                //connection.setRequestProperty("api_key", "YOUR_API_KEY");
                connection.connect();

                InputStream response = connection.getInputStream();
                String resposta = new BufferedReader(new InputStreamReader(response)).lines().collect(Collectors.joining());
                // Procesar la respuesta aquí
                System.out.println(resposta);
            } catch (Exception e) {
                e.printStackTrace();
                presentationController.getUiController().errorData();
                correcte = false;
            }
        } else if (data == 1 || !correcte) {
            correcte = true;
            presentationController.getUiController().load();

            try {
                monstres = MonstreJson.llegirMonstres();
                presentationController.getUiController().okData();
            } catch (IOException e) {
                presentationController.getUiController().noMonsters();
                correcte = false;
            }
            personatges = PersonatgeJson.llegirPersonatges();
        }
        if (correcte) {
            do {
                presentationController.getUiController().menu();
                opcio = Integer.parseInt(scanner.nextLine());
                switch (opcio) {
                    case 1:
                        presentationController.getUiController().enterName();
                        String nom = scanner.nextLine();
                        presentationController.getUiController().confirmName(nom);
                        String jugador = scanner.nextLine();
                        presentationController.getUiController().checkLvl();
                        int nivell = 0;
                        ok = true;
                        try {
                            nivell = Integer.parseInt(scanner.nextLine());
                        } catch (Exception e) {
                            presentationController.getUiController().errorLvl();
                            ok = false;
                        }
                        while (nivell > 10 || nivell < 1 || !ok) {
                            presentationController.getUiController().errorLvl2();
                            try {
                                nivell = Integer.parseInt(scanner.nextLine());
                                ok = true;
                            } catch (Exception e) {
                                presentationController.getUiController().errorLvl();
                                ok = false;
                            }
                        }
                        presentationController.getUiController().okLvl(nivell);
                        ArrayList<Integer> numeros = new ArrayList<>();
                        nums = daus6cares();
                        int dau1 = nums.get(0);
                        int dau2 = nums.get(1);
                        int sum = dau1 + dau2;
                        numeros.add(sum);

                        presentationController.getUiController().stBody(sum, dau1, dau2);
                        nums = daus6cares();
                        dau1 = nums.get(0);
                        dau2 = nums.get(1);
                        sum = dau1 + dau2;
                        numeros.add(sum);

                        presentationController.getUiController().stMind(sum, dau1, dau2);
                        nums = daus6cares();
                        dau1 = nums.get(0);
                        dau2 = nums.get(1);
                        sum = dau1 + dau2;
                        numeros.add(sum);

                        presentationController.getUiController().stSpirit(sum, dau1, dau2);

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

                        presentationController.getUiController().stats(numeros);
                        int Body = numeros.get(0);
                        int Mind = numeros.get(1);
                        int Spirit = numeros.get(2);

                        presentationController.getUiController().chClass();
                        String tipus = scanner.nextLine();

                        Personatge a = new Personatge(nom, jugador, (nivell * 99), Body, Mind, Spirit, tipus);
                        personatges.add(a);

                        presentationController.getUiController().personatgeCreat(nom);
                        break;
                    case 2:
                        presentationController.getUiController().filterName();
                        String nomJugador = scanner.nextLine();
                        if (nomJugador.isEmpty()) {
                            presentationController.getUiController().tots();
                        } else {
                            presentationController.getUiController().alguns();
                        }
                        int x = 1;
                        Personatge personatgeAux = null;
                        for (int i = 0; i < personatges.size(); i++) {
                            personatgeAux = personatges.get(i);
                            if (personatgeAux.getPlayer().contains(nomJugador)) {
                                presentationController.getUiController().llistarPerson(x, personatgeAux);
                                x++;
                            }
                        }
                        presentationController.getUiController().back();
                        x--;
                        presentationController.getUiController().triaPerson(x);
                        int numPersonatge = 0;
                        ok = true;
                        try {
                            numPersonatge = Integer.parseInt(scanner.nextLine());
                        } catch (Exception e) {
                            presentationController.getUiController().errorTriar();
                            ok = false;
                        }
                        while (numPersonatge > x || numPersonatge < 0 || !ok) {
                            presentationController.getUiController().errorTriar2();
                            try {
                                numPersonatge = Integer.parseInt(scanner.nextLine());
                                ok = true;
                            } catch (Exception e) {
                                presentationController.getUiController().errorTriar();
                                ok = false;
                            }
                        }

                        //CAS 0
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
                            presentationController.getUiController().eliminarPerson(personatgeAux);
                            String nomEliminar = scanner.nextLine();
                            if (nomEliminar.isEmpty()) {

                            } else if (nomEliminar.equals(personatgeAux.getName())) {
                                presentationController.getUiController().personEliminat(personatgeAux);
                                personatges.remove(j);
                            }
                        }
                        break;
                    case 3:
                        boolean existeix;
                        String nomAventura;
                        existeix = false;
                        presentationController.getUiController().createAventura();

                        nomAventura = scanner.nextLine();
                        Aventura c;
                        if (!aventures.isEmpty()) {
                            for (int k = 0; k < aventures.size(); k++) {
                                c = aventures.get(k);
                                if (c.getNom().equals(nomAventura)) {
                                    existeix = true;
                                }
                            }
                        }
                        if (existeix) {
                            presentationController.getUiController().existAdventure();
                        } else {
                            JSONObject aventura = new JSONObject();

                            aventura.put("Nom", nomAventura);

                            presentationController.getUiController().encounters(nomAventura);
                            int sumErrors = 0;
                            ok = true;
                            try {
                                enfrentaments = Integer.parseInt(scanner.nextLine());
                            } catch (Exception e) {
                                presentationController.getUiController().errorEncounters();
                                ok = false;
                            }
                            sumErrors++;
                            while ((enfrentaments > 4 || enfrentaments < 1 || !ok) && sumErrors < 3) {
                                presentationController.getUiController().errorEncounters2();
                                try {
                                    enfrentaments = Integer.parseInt(scanner.nextLine());
                                    ok = true;
                                    sumErrors++;
                                } catch (Exception e) {
                                    presentationController.getUiController().errorEncounters();
                                    ok = false;
                                    sumErrors++;
                                }
                            }
                            Aventura z = new Aventura(nomAventura, enfrentaments);
                            aventures.add(z);

                            for (int k = 0; k < enfrentaments; k++) {
                                ArrayList<Monstre> monstres1 = new ArrayList<>();
                                arrayDeArray.add(k, monstres1);
                            }

                            if (sumErrors < 3) {

                                aventura.put("NumEnfrentaments", enfrentaments);

                                presentationController.getUiController().sumEncounters(enfrentaments);
                                int i = 0;
                                int j = 1;
                                int opcio2 = 0;
                                do {
                                    JSONArray monstresJSON = new JSONArray();
                                    do {
                                        ArrayList<Monstre> monstres1;
                                        presentationController.getUiController().encounter(j, enfrentaments);
                                        monstres1 = arrayDeArray.get(i);
                                        if (monstres1.isEmpty()) {
                                            presentationController.getUiController().empty();
                                        } else {
                                            for (int k = 0; k < monstres1.size(); k++) {
                                                Monstre monstresAux = monstres1.get(k);
                                                presentationController.getUiController().showMonsters(k, monstresAux);
                                            }
                                        }

                                        presentationController.getUiController().menuEncounter();
                                        ok = true;
                                        try {
                                            opcio2 = Integer.parseInt(scanner.nextLine());
                                        } catch (Exception e) {
                                            presentationController.getUiController().errormenu();
                                            ok = false;
                                        }
                                        while (opcio2 > 3 || opcio2 < 1 || !ok) {
                                            presentationController.getUiController().errormenu2();
                                            try {
                                                opcio2 = Integer.parseInt(scanner.nextLine());
                                                ok = true;
                                            } catch (Exception e) {
                                                presentationController.getUiController().errormenu();
                                                ok = false;
                                            }
                                        }
                                        switch (opcio2) {
                                            case 1:
                                                for (int k = 0; k < monstres.size(); k++) {
                                                    Monstre monstresAux = monstres.get(k);
                                                    presentationController.getUiController().showAllMonsters(k, monstresAux);
                                                }
                                                presentationController.getUiController().chooseMonster(monstres);
                                                int numMonstre = 0;
                                                ok = true;
                                                try {
                                                    numMonstre = Integer.parseInt(scanner.nextLine());
                                                } catch (Exception e) {
                                                    System.out.println("\tInvalid format for monster.");
                                                    ok = false;
                                                }
                                                while (numMonstre > monstres.size() || numMonstre < 1 || !ok) {
                                                    System.out.println("\tPlease enter a valid number: ");
                                                    try {
                                                        numMonstre = Integer.parseInt(scanner.nextLine());
                                                        ok = true;
                                                    } catch (Exception e) {
                                                        System.out.println("\tInvalid format for monster.");
                                                        ok = false;
                                                    }
                                                }
                                                numMonstre--;
                                                Monstre monstresAux = monstres.get(numMonstre);
                                                System.out.println("-> How much " + monstresAux.getName() + "(s) do you want to add: ");

                                                int quantitat = 0;
                                                ok = true;
                                                try {
                                                    quantitat = Integer.parseInt(scanner.nextLine());
                                                } catch (Exception e) {
                                                    System.out.println("\tInvalid format for monster.");
                                                    ok = false;
                                                }
                                                while (quantitat > monstres.size() || quantitat < 1 || !ok) {
                                                    System.out.println("\tPlease enter a valid number: ");
                                                    try {
                                                        quantitat = Integer.parseInt(scanner.nextLine());
                                                        ok = true;
                                                    } catch (Exception e) {
                                                        System.out.println("\tInvalid format for monster.");
                                                        ok = false;
                                                    }
                                                }
                                                Monstre b = new Monstre(monstresAux.getName(), quantitat);
                                                JSONObject monstresAv = new JSONObject();
                                                monstresAv.put("Nom", monstresAux.getName());
                                                monstresAv.put("Quantitat", quantitat);
                                                monstresJSON.put(y, monstresAv);
                                                y++;
                                                monstres1.add(b);
                                                break;
                                            case 2:
                                                System.out.println("-> Wich monster do you want to delate: ");
                                                int num_Monstre = 0;
                                                ok = true;
                                                try {
                                                    num_Monstre = Integer.parseInt(scanner.nextLine());
                                                } catch (Exception e) {
                                                    System.out.println("\tInvalid format for monster.");
                                                    ok = false;
                                                }
                                                while (num_Monstre > monstres.size() || num_Monstre < 1 || !ok) {
                                                    System.out.println("\tPlease enter a valid number: ");
                                                    try {
                                                        num_Monstre = Integer.parseInt(scanner.nextLine());
                                                        ok = true;
                                                    } catch (Exception e) {
                                                        System.out.println("\tInvalid format for monster.");
                                                        ok = false;
                                                    }
                                                }
                                                num_Monstre--;
                                                Monstre monstreAux = monstres1.get(num_Monstre);
                                                monstres1.remove(num_Monstre);
                                                System.out.println(monstreAux.getQuantitat() + " " + monstreAux.getName() + "were removed from the encounter.");
                                                break;
                                            case 3:
                                                JSONObject enfrentament = new JSONObject();
                                                enfrentament.put("Monstres", monstresJSON);
                                                arrayEnfrentaments.put(i, enfrentament);
                                                y = 0;
                                                i++;
                                                j++;
                                                break;
                                        }
                                    } while (opcio2 != 3);
                                } while (i < enfrentaments);
                                System.out.println("Tavern keeper: “Great plan lad! I hope you won’t die!“");
                                System.out.println("The new adventure " + nomAventura + " has been created.\n");
                                aventura.put("Enfrentaments", arrayEnfrentaments);
                                escriureAventura(aventura);
                            } else {
                                System.out.println("Too much tries");
                            }
                        }
                        break;
                    case 4:
                        System.out.println("Tavern keeper: “So, you are looking to go on an adventure?“\n" +
                                "“Where do you fancy going?“");
                        System.out.println("Available adventures:");
                        if (personatges.size() < 3) {
                            System.out.println("Falten personatges, minim 3.");
                        } else {
                            for (int k = 0; k < aventures.size(); k++) {
                                Aventura z = aventures.get(k);
                                System.out.println((k + 1) + ". " + z.getNom());
                            }
                        }
                        System.out.println("-> Choose an adventure: ");
                        int numAventura = 0;
                        ok = true;
                        try {
                            numAventura = Integer.parseInt(scanner.nextLine());
                        } catch (Exception e) {
                            System.out.println("\tInvalid format for adventure.");
                            ok = false;
                        }
                        while (numAventura > aventures.size() || numAventura < 1 || !ok) {
                            System.out.println("\tPlease enter a valid number: ");
                            try {
                                numAventura = Integer.parseInt(scanner.nextLine());
                                ok = true;
                            } catch (Exception e) {
                                System.out.println("\tInvalid format for adventure.");
                                ok = false;
                            }
                        }
                        numAventura--;
                        Aventura nomAcenturaAux = aventures.get(numAventura);
                        System.out.println("Tavern keeper: “" + nomAcenturaAux.getNom() + " it is!“\n" +
                                "“And how many people shall join you?“");
                        System.out.println("-> Choose a number of characters [3..5]: ");
                        int numCharacters = 0;
                        ok = true;
                        try {
                            numCharacters = Integer.parseInt(scanner.nextLine());
                        } catch (Exception e) {
                            System.out.println("\tInvalid format for adventure.");
                            ok = false;
                        }
                        while (numCharacters > 5 || numCharacters < 3 || !ok) {
                            System.out.println("\tPlease enter a valid number: ");
                            try {
                                numCharacters = Integer.parseInt(scanner.nextLine());
                                ok = true;
                            } catch (Exception e) {
                                System.out.println("\tInvalid format for adventure.");
                                ok = false;
                            }
                        }
                        System.out.println("Tavern keeper: “Great," + numCharacters + " it is.“\n" +
                                "“Who among these lads shall join you?“");
                        ArrayList<Personatge> nousCharacters = new ArrayList<>();

                        //Inicialitzar tot a " " el array nousCharacters
                        /*for (int i = 0; i < numCharacters; i++) {
                            nousCharacters.add(" ");
                        }*/

                        for (int i = 0; i < numCharacters; i++) {
                            System.out.println("------------------------------");
                            System.out.println("Your party (" + i + "/ " + numCharacters + "):");
                            for (int j = 0; j < numCharacters; j++) {
                                if (nousCharacters.size() < (j + 1)) {
                                    System.out.println((j + 1) + ". Empty");
                                } else {
                                    Personatge charactersAux = nousCharacters.get(j);
                                    System.out.println((j + 1) + ". " + charactersAux.getName());
                                }
                            }
                            System.out.println("------------------------------");
                            System.out.println("Available characters:");
                            for (int j = 0; j < personatges.size(); j++) {
                                Personatge pAux = personatges.get(j);
                                System.out.println((j + 1) + ". " + pAux.getName());
                            }
                            System.out.println("-> Choose character " + (i + 1) + " in your party:");
                            int triaCharacter = 0;
                            ok = true;
                            try {
                                triaCharacter = Integer.parseInt(scanner.nextLine());
                            } catch (Exception e) {
                                System.out.println("\tInvalid format for adventure.");
                                ok = false;
                            }
                            while (triaCharacter > personatges.size() || triaCharacter < 1 || !ok) {
                                System.out.println("\tPlease enter a valid number: ");
                                try {
                                    triaCharacter = Integer.parseInt(scanner.nextLine());
                                    ok = true;
                                } catch (Exception e) {
                                    System.out.println("\tInvalid format for adventure.");
                                    ok = false;
                                }
                            }
                            triaCharacter--;
                            Personatge pAux = personatges.get(triaCharacter);
                            nousCharacters.add(pAux);
                        }
                        System.out.println("------------------------------");
                        System.out.println("Your party (" + numCharacters + "/ " + numCharacters + "):");
                        for (int j = 0; j < numCharacters; j++) {
                            if (nousCharacters.size() < (j + 1)) {
                                System.out.println((j + 1) + ". Empty");
                            } else {
                                Personatge charactersAux = nousCharacters.get(j);
                                System.out.println((j + 1) + ". " + charactersAux.getName());
                            }
                        }
                        System.out.println("------------------------------\n");
                        System.out.println("Tavern keeper: “Great, good luck on your adventure lads!“");
                        System.out.println("The “" + nomAcenturaAux.getNom() + "“ will start soon...\n");

                        for (int i = 0; i < enfrentaments; i++) {
                            System.out.println("---------------------");
                            System.out.println("Starting Encounter " + (i + 1) + ":");
                            ArrayList<Monstre> monstres1;
                            monstres1 = arrayDeArray.get(i);
                            for (int j = 0; j < monstres1.size(); j++) {
                                Monstre monstreAux = monstres1.get(j);
                                System.out.println("- " + monstreAux.getQuantitat() + "x " + monstreAux.getName());
                            }
                            System.out.println("---------------------\n\n");
                            System.out.println("-------------------------\n" +
                                    "*** Preparation stage ***\n" +
                                    "-------------------------");
                            for (int j = 0; j < nousCharacters.size(); j++) {
                                Personatge personatge = nousCharacters.get(j);
                                if (personatge.getTipus().equals("Adventurer") || personatge.getTipus().equals("Guerrer")) {
                                    System.out.println(personatge.getName() + " uses Self-Motivated. Their Spirit increases in +1.\n");
                                    personatge.setSpirit(personatge.getSpirit() + 1);
                                } else if (personatge.getTipus().equals("Campio")) {
                                    System.out.println(personatge.getName() + "uses Motivational speech. Everyone’s Spirit increases in +1.\n");
                                    for (int k = 0; k < nousCharacters.size(); k++) {
                                        Personatge aux = nousCharacters.get(k);
                                        aux.setSpirit(aux.getSpirit() + 1);
                                    }
                                } else if (personatge.getTipus().equals("Clergue")) {
                                    System.out.println(personatge.getName() + " uses Prayer of good luck. Everyone’s Mind increases in +1.\n");
                                    for (int k = 0; k < nousCharacters.size(); k++) {
                                        Personatge aux = nousCharacters.get(k);
                                        aux.setMind(aux.getMind() + 1);
                                    }
                                } else if (personatge.getTipus().equals("Paladi")) {
                                    int num = dau3cares();
                                    System.out.println(personatge.getName() + " uses Blessing of good luck. Everyone’s Mind increases in " + num + ".\n");
                                    for (int k = 0; k < nousCharacters.size(); k++) {
                                        Personatge aux = nousCharacters.get(k);
                                        aux.setMind(aux.getMind() + num);
                                    }
                                } else if (personatge.getTipus().equals("Mag")) {

                                }
                            }
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
                            System.out.println("Rolling initiative...");
                            Collections.sort(ordre, Comparator.comparingInt(Combat::getIniciativa));
                            Collections.reverse(ordre);

                            for (int j = 0; j < ordre.size(); j++) {
                                Combat ordreAux = ordre.get(j);
                                System.out.println("- " + ordreAux.getIniciativa() + "\t" + ordreAux.getNom());
                            }
                            System.out.println("--------------------\n" +
                                    "*** Combat stage ***\n" +
                                    "--------------------");

                            int z = 1;
                            int nMonstres = ordre.size() - nousCharacters.size();
                            int nPlayers = nousCharacters.size();
                            int sumHitPoints = 0;
                            int xp = 0;
                            do {
                                System.out.println("Round " + z);
                                System.out.println("Party: ");

                                for (int j = 0; j < nousCharacters.size(); j++) {
                                    Personatge personatge = nousCharacters.get(j);
                                    System.out.println("- " + personatge.getName() + "\t" + personatge.getHitPoints() + " / " + personatge.getMaxPoints() + " hit points");
                                }

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
                                                    System.out.println(monstre.getName() + " attacks " + sb.toString() + ".\n"
                                                            + "Hits and deals " + dmg + " physical damage.\n");
                                                    for (int j = 0; j < ordre.size(); j++) {
                                                        Combat player = ordre.get(j);
                                                        if (player.getHitPoints() <= 0 && player.getTipus().equals("persona") && player.isAlive()) {
                                                            System.out.println(player.getNom() + " inconcient.");
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
                                                    System.out.println(monstre.getName() + " attacks " + player.getNom() + ".\n"
                                                            + "Hits and deals " + dmg + " physical damage.\n");
                                                    if (player.getHitPoints() <= 0 && player.isAlive()) {
                                                        System.out.println(player.getNom() + " inconcient.");
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
                                                    if (personatge.getTipus().equals("Guerrer") || personatge.getTipus().equals("Campio")) {
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

                                                    System.out.println(personatge.getName() + " attacks " + monstre.getNom() + " with Sword slash. \n" +
                                                            "Hits and deals " + dmg + " physical damage.\n");
                                                    if (monstre.getHitPoints() <= 0 && monstre.isAlive()) {
                                                        System.out.println(monstre.getNom() + " dies.");
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
                                System.out.println("End of round " + z);
                                z++;

                            } while (nMonstres > 0 && nPlayers > 0);
                            System.out.println("All enemies are defeated.");

                            if (sumHitPoints > 0) {
                                System.out.println("------------------------\n" +
                                        "*** Short rest stage ***\n" +
                                        "------------------------\n");
                                for (int j = 0; j < nousCharacters.size(); j++) {
                                    Personatge aux = nousCharacters.get(j);
                                    aux.setXp(aux.getXp() + xp);
                                    System.out.println(aux.getName() + " gains " + xp + " xp.");

                                }
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
                                                System.out.println(aux.getNom() + " uses Bandage time. Heals " + cura + " hit points");
                                            } else {
                                                System.out.println(aux.getNom() + " is unconscious.");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case 5:
                        System.out.println("Tavern keeper: " + "\"" + "Are you leaving already? See you soon, adventurer." + "\"");
                        break;
                    default:
                        System.out.println("Invalid option!");
                        break;
                }
            } while (opcio != 5);
        }
    }
}
