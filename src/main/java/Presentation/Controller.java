package Presentation;

import Business.Monstre;
import Business.Personatge;
import Persistance.MonstreJson;
import Persistance.PersonatgeJson;
import Business.Aventura;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static Business.Dau.daus6cares;


public class Controller {
    public static void main(String[] args) {
        boolean correcte = true;
        Scanner scanner = new Scanner(System.in);
        int opcio = 0;
        ArrayList<Monstre> monstres = null;
        ArrayList<Personatge> personatges;
        ArrayList<Aventura> aventures = new ArrayList<>();
        ArrayList<Integer> nums = null;
        System.out.println("Welcome to simple LSRPG.\n");
        System.out.println("Loading data...");

        try {
            monstres = MonstreJson.llegirMonstres();
            System.out.println("Data was successfully loaded.");
        } catch (IOException e) {
            System.out.print("Error: The monsters.json file can't be accessed.");
            correcte = false;
        }
        personatges = PersonatgeJson.llegirPersonatges();
        if(correcte){
            do {
                System.out.println("The tavern keeper looks at you and says:");
                System.out.println("\"" + "Welcome adventurer! How can i help you?" + "\"\n");
                System.out.println("\t1) Character creation\n" +
                        "\t2) List characters\n" +
                        "\t3) Create an adventure\n" +
                        "\t4) Start an adventure\n" +
                        "\t5) Exit\n");
                System.out.println("Your answer: ");
                opcio = Integer.parseInt(scanner.nextLine());
                switch (opcio) {
                    case 1:
                        System.out.println("Tavern keeper: " + "\"" + "Oh, so you are new to this land." + "\"\n\"" +
                                "What’s your name?" + "\"\n");
                        System.out.println("-> Enter your name: ");
                        String nom = scanner.nextLine();
                        System.out.println("Tavern keeper: " + "\"" + "Hello, " + nom + ", be welcome." + "\"\n" +
                                "\"" + "And now, if I may break the fourth wall, who is your Player?" + "\"");
                        System.out.println("-> Enter the player's name: ");
                        String jugador = scanner.nextLine();
                        System.out.println("Tavern keeper: " + "\"" + "I see, I see..." + "\"\n\"" +
                                "Now, are you an experienced adventurer?" + "\"\n");
                        System.out.println("-> Enter character's level [1..10]: ");
                        int nivell = 0;
                        boolean ok = true;
                        try{
                            nivell = Integer.parseInt(scanner.nextLine());
                        }catch (Exception e){
                            System.out.println("\tInvalid format for level.");
                            ok = false;
                        }
                        while(nivell > 10 || nivell < 1 || !ok){
                            System.out.println("\tPlease enter a valid level: ");
                            try{
                                nivell = Integer.parseInt(scanner.nextLine());
                                ok = true;
                            }catch (Exception e){
                                System.out.println("\tInvalid format for level.\n");
                                ok = false;
                            }
                        }
                        System.out.println("Taverner keeper: " + "\"" + "Oh, so you are level" + nivell + "!\"\n\"" +
                                "Great, let me get a closer look at you..." + "\"\n");
                        System.out.println("Generating your stats...\n");
                        ArrayList<Integer> numeros = new ArrayList<>();
                        nums = daus6cares();
                        int dau1 = nums.get(0);
                        int dau2 = nums.get(1);
                        int sum = dau1 + dau2;
                        numeros.add(sum);

                        System.out.println("Body:\tYou rolled " + sum + " (" + dau1 + " and " + dau2 + ").");
                        nums = daus6cares();
                        dau1 = nums.get(0);
                        dau2 = nums.get(1);
                        sum = dau1 + dau2;
                        numeros.add(sum);

                        System.out.println("Mind:\tYou rolled " + sum + " (" + dau1 + " and " + dau2 + ").");
                        nums = daus6cares();
                        dau1 = nums.get(0);
                        dau2 = nums.get(1);
                        sum = dau1 + dau2;
                        numeros.add(sum);

                        System.out.println("Spirit: You rolled " + sum + " (" + dau1 + " and " + dau2 + ").\n");

                        for (int i = 0; i < 3; i++) {
                            if(numeros.get(i) == 2){
                                numeros.set(i, -1);
                            }else if (numeros.get(i) >= 3 && numeros.get(i) <= 5){
                                numeros.set(i, 0);
                            }else if (numeros.get(i) >= 6 && numeros.get(i) <= 9){
                                numeros.set(i, 1);
                            }else if (numeros.get(i) >= 10 && numeros.get(i) <= 11) {
                                numeros.set(i, 2);
                            }else if (numeros.get(i) == 12){
                                numeros.set(i, 3);
                            }
                        }

                        System.out.println("Your states are:");
                        System.out.println("\t- Body: " + numeros.get(0));
                        System.out.println("\t- Mind: " + numeros.get(1));
                        System.out.println("\t- Spirit: " + numeros.get(2));
                        int Body = numeros.get(0);
                        int Mind = numeros.get(1);
                        int Spirit = numeros.get(2);

                        Personatge a = new Personatge(nom, jugador, nivell, Body, Mind, Spirit, "");
                        personatges.add(a);

                        System.out.println("The new character" + nom + "has been created.\n ");
                        break;
                    case 2:
                        System.out.println("Tavern keeper: " + "\"" + "Lads! they want to see you!\"\n\"" +
                                "Who piques your interest?\"\n");
                        System.out.println("-> Enter the name of the Player to filter: ");
                        String nomJugador = scanner.nextLine();
                        if(nomJugador.isEmpty()){
                            System.out.println("You watch all adventurers get up from their chairs and approach you.");
                        }else{
                            System.out.println("You watch as some adventurers get up from their chairs and approach you.");
                        }
                        int x = 1;
                        Personatge personatgeAux = null;
                        for (int i = 0; i < personatges.size(); i++) {
                            personatgeAux = personatges.get(i);
                            if(personatgeAux.getPlayer().contains(nomJugador)){
                                System.out.println(x + ". " + personatgeAux.getName());
                                x++;
                            }
                        }
                        System.out.println("\n0. Back\n");
                        x--;
                        System.out.println("Who would you like to meet? [0.." + x + "]");
                        int numPersonatge = 0;
                        ok = true;
                        try{
                            numPersonatge = Integer.parseInt(scanner.nextLine());
                        }catch (Exception e){
                            System.out.println("\tInvalid format for meet player.");
                            ok = false;
                        }
                        while(numPersonatge > x || numPersonatge < 0 || !ok){
                            System.out.println("\tPlease enter a valid number: ");
                            try{
                                numPersonatge = Integer.parseInt(scanner.nextLine());
                                ok = true;
                            }catch (Exception e){
                                System.out.println("\tInvalid format for meet player.");
                                ok = false;
                            }
                        }

                        //CAS 0


                        int i = 0;
                        int j = 0;
                        while(i != numPersonatge){
                            personatgeAux = personatges.get(j);
                            if(personatgeAux.getPlayer().contains(nomJugador)){
                                i++;
                                j++;
                            }else{
                                j++;
                            }
                        }
                        j--;
                        personatgeAux = personatges.get(j);
                        System.out.println("Tavern keeper: \"Hey " + personatgeAux.getName() + " get here; the boss wants to see you!\"\n");
                        System.out.println(personatgeAux.toString());
                        System.out.println("[Enter name to delete, or press enter to cancel]");
                        System.out.println("Do you want to delete " + personatgeAux.getName()+ "?");
                        String nomEliminar = scanner.nextLine();
                        if(nomEliminar.isEmpty()){

                        }else if(nomEliminar.equals(personatgeAux.getName())){
                            System.out.println("\nTavern keeper: \"I'm sorry kiddo, but you have to leave.\"\n");
                            System.out.println("Character " + personatgeAux.getName() + " left the Guild");
                            personatges.remove(j);
                        }
                        break;
                    case 3:
                        boolean existeix;
                        String nomAventura;
                        existeix = false;
                        System.out.println("Tavern keeper: “Planning an adventure? Good luck with that!“");
                        System.out.println("-> Name your adventure: ");

                        nomAventura = scanner.nextLine();
                        Aventura c;
                        if(!aventures.isEmpty()) {
                            for (int k = 0; k < aventures.size(); k++) {
                                c = aventures.get(k);
                                if (c.getNom().equals(nomAventura)) {
                                    existeix = true;
                                }
                            }
                        }
                        if(existeix) {
                            System.out.println("This adventure already exists\n");
                        }else {

                            System.out.println("Tavern keeper: “You plan to undertake " + nomAventura + ", really?“\n" +
                                    "“How long will that take?“");
                            System.out.println("-> How many encounters do you want [1..4]: ");
                            int enfrentaments = 0;
                            int sumErrors = 0;
                            ok = true;
                            try {
                                enfrentaments = Integer.parseInt(scanner.nextLine());
                            } catch (Exception e) {
                                System.out.println("\tInvalid format of encounters");
                                ok = false;
                            }
                            sumErrors++;
                            while ((enfrentaments > 4 || enfrentaments < 1 || !ok) && sumErrors < 3) {
                                System.out.println("\tPlease enter a valid number: ");
                                try {
                                    enfrentaments = Integer.parseInt(scanner.nextLine());
                                    ok = true;
                                    sumErrors++;
                                } catch (Exception e) {
                                    System.out.println("\tInvalid format of encounters");
                                    ok = false;
                                    sumErrors++;
                                }
                            }
                            ArrayList<ArrayList<Monstre>> arrayDeArray = new ArrayList<>();
                            for (int k = 0; k < enfrentaments; k++) {
                                ArrayList<Monstre> monstres1 = new ArrayList<>();
                                arrayDeArray.add(k, monstres1);
                            }

                            if (sumErrors < 3){
                                System.out.println("Tavern keeper: “" + enfrentaments + " encounters? That is too much for me...“");
                                i = 0;
                                j = 1;
                                int opcio2 = 0;
                                do {
                                    do {
                                        ArrayList<Monstre> monstres1;
                                        System.out.println("* Encounter " + j + " / " + enfrentaments);
                                        System.out.println("* Monsters in encounter");
                                        monstres1 = arrayDeArray.get(i);
                                        if (monstres1.isEmpty()) {
                                            System.out.println("\t#Empty");
                                        } else {
                                            for (int k = 0; k < monstres1.size(); k++) {
                                                Monstre monstresAux = monstres1.get(k);
                                                System.out.println("\t" + (k + 1) + ". " + monstresAux.getName() + " (X" + monstresAux.getQuantitat() + ")");
                                            }
                                        }

                                        System.out.println("\n1. Add monster\n2. Remove monster\n3. Continue\n");
                                        System.out.println("-> Enter an option [1..3]");
                                        ok = true;
                                        try {
                                            opcio2 = Integer.parseInt(scanner.nextLine());
                                        } catch (Exception e) {
                                            System.out.println("\tInvalid format of option");
                                            ok = false;
                                        }
                                        while (opcio2 > 3 || opcio2 < 1 || !ok) {
                                            System.out.println("\tPlease enter a valid number: ");
                                            try {
                                                opcio2 = Integer.parseInt(scanner.nextLine());
                                                ok = true;
                                            } catch (Exception e) {
                                                System.out.println("\tInvalid format of option");
                                                ok = false;
                                            }
                                        }
                                        switch (opcio2) {
                                            case 1:
                                                for (int k = 0; k < monstres.size(); k++) {
                                                    Monstre monstresAux = monstres.get(k);
                                                    System.out.println((k + 1) + ". " + monstresAux.getName() + " (" + monstresAux.getChallenge() + ")");
                                                }
                                                System.out.println("-> Choose a monster to add [1.." + monstres.size() + "]:");
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
                                                i++;
                                                j++;
                                                break;
                                        }
                                    } while (opcio2 != 3);
                                }while (i < enfrentaments);
                            }else {
                                System.out.println("Too much tries");
                            }
                        }

                        break;
                    case 4:

                        break;
                    case 5:
                        System.out.println("Tavern keeper: " + "\"" + "Are you leaving already? See you soon, adventurer." + "\"");
                        break;
                    default:
                        System.out.println("Invalid option!");
                        break;
                }
            }while (opcio != 5);
        }
    }
}
