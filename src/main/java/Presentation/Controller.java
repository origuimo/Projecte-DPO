package Presentation;

import Business.Monstre;
import Business.Personatge;
import Persistance.MonstreJson;
import Persistance.PersonatgeJson;

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
        ArrayList<Personatge> personatges = null;
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
                        System.out.println("Tavern keeper: \"Hey " + personatgeAux.getName() + "get here; the boss wants to see you!\"\n");
                        System.out.println(personatgeAux.toString());
                        break;
                    case 3:

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
