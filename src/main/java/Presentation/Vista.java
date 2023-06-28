package Presentation;

import Business.*;
import java.util.*;


public class Vista {

    public void pantallaInicial(){
        System.out.println("\n" +
                "   _____ _                 __        __   _____ ____  ______\n" +
                "  / ___/(_)___ ___  ____  / /__     / /  / ___// __ \\/ ____/\n" +
                "  \\__ \\/ / __ `__ \\/ __ \\/ / _ \\   / /   \\__ \\/ /_/ / / __  \n" +
                " ___/ / / / / / / / /_/ / /  __/  / /______/ / _, _/ /_/ /  \n" +
                "/____/_/_/ /_/ /_/ .___/_/\\___/  /_____/____/_/ |_|\\____/   \n" +
                "                /_/                                         \n");
        System.out.println("Welcome to simple LSRPG.\n");
        System.out.println("Do you want to use your local or cloud data?");
        System.out.println("\t1) Local data\n\t2) Cloud data");
        System.out.println("-> Answer: ");
    }

    public void invalidData(){
        System.out.println("\tInvalid format for Data.");
    }

    public void invalidData2(){
        System.out.println("\tPlease enter a valid Data: ");
    }

    public void load(){
        System.out.println("Loading data...");
    }

    public void errorData(){
        System.out.println("Couldn’t connect to the remote server.\nReverting to local data.\n");
    }

    public void okData(){
        System.out.println("Data was successfully loaded.");
    }

    public void noMonsters(){
        System.out.print("Error: The monsters.json file can't be accessed.");
    }

    public void menu(){
        System.out.println("The tavern keeper looks at you and says:");
        System.out.println("\"" + "Welcome adventurer! How can i help you?" + "\"\n");
        System.out.println("\t1) Character creation\n" +
                "\t2) List characters\n" +
                "\t3) Create an adventure\n" +
                "\t4) Start an adventure\n" +
                "\t5) Exit\n");
        System.out.println("Your answer: ");
    }


//------------------------------------------------------------ Cas 1 -----------------------------------------------------------------------

    public void enterName(){
        System.out.println("Tavern keeper: " + "\"" + "Oh, so you are new to this land." + "\"\n\"" +
                "What’s your name?" + "\"\n");
        System.out.println("-> Enter your name: ");
    }

    public void confirmName(String nom){
        System.out.println("Tavern keeper: " + "\"" + "Hello, " + nom + ", be welcome." + "\"\n" +
                "\"" + "And now, if I may break the fourth wall, who is your Player?" + "\"");
        System.out.println("-> Enter the player's name: ");
    }

    public void checkLvl(){
        System.out.println("Tavern keeper: " + "\"" + "I see, I see..." + "\"\n\"" +
                "Now, are you an experienced explorer?" + "\"\n");
        System.out.println("-> Enter character's level [1..10]: ");
    }

    public void errorLvl(){
        System.out.println("\tInvalid format for level.");
    }

    public void errorLvl2(){
        System.out.println("\tPlease enter a valid level: ");
    }

    public void okLvl(int nivell){
        System.out.println("Taverner keeper: " + "\"" + "Oh, so you are level " + nivell + "!\"\n\"" +
                "Great, let me get a closer look at you..." + "\"\n");
        System.out.println("Generating your stats...\n");
    }

    public void stBody(int sum, int dau1, int dau2){
        System.out.println("Body:\tYou rolled " + sum + " (" + dau1 + " and " + dau2 + ").");
    }

    public void stMind(int sum, int dau1, int dau2){
        System.out.println("Mind:\tYou rolled " + sum + " (" + dau1 + " and " + dau2 + ").");
    }


    public void stSpirit(int sum, int dau1, int dau2){
        System.out.println("Spirit:\tYou rolled " + sum + " (" + dau1 + " and " + dau2 + ").");
    }

    public void stats(ArrayList<Integer> numeros){
        System.out.println("\nYour states are:");
        System.out.println("\t- Body: " + numeros.get(0));
        System.out.println("\t- Mind: " + numeros.get(1));
        System.out.println("\t- Spirit: " + numeros.get(2));
    }

    public void chClass(){
        System.out.println("Tavern keeper: \"Looking good!\"\n" +
                "\"And, lastly, ?\"\n");
        System.out.println("-> Enter the character’s initial class [Adventurer, Clergue, Mag]: ");
    }

    public void personatgeCreat(String nom){
        System.out.println("Tavern keeper: \"Any decent party needs one of those.\"\n" +
                "\"I guess that means you’re a Paladin by now, nice!\"\n");
        System.out.println("The new character " + nom + " has been created.\n ");
    }

//----------------------------------------------------------Cas 2 --------------------------------------------------------------------------------------

    public void filterName(){
        System.out.println("Tavern keeper: " + "\"" + "Lads! they want to see you!\"\n" +
                "Who piques your interest?\"\n");
        System.out.println("-> Enter the name of the Player to filter: ");
    }

    public void tots(){
        System.out.println("You watch all adventurers get up from their chairs and approach you.");
    }

    public void alguns(){
        System.out.println("You watch as some adventurers get up from their chairs and approach you.");
    }

    public void llistarPerson(int x, Personatge personatgeAux){
        System.out.println(x + ". " + personatgeAux.getName());
    }

    public void back(){
        System.out.println("\n0. Back\n");
    }

    public void triaPerson(int x){
        System.out.println("Who would you like to meet? [0.." + x + "]");
    }

    public void errorTriar(){
        System.out.println("\tInvalid format for meet player.");

    }

    public void errorTriar2(){
        System.out.println("\tPlease enter a valid number: ");
    }

    public void eliminarPerson(Personatge personatgeAux){
        System.out.println("Tavern keeper: \"Hey " + personatgeAux.getName() + " get here; the boss wants to see you!\"\n");
        System.out.println(personatgeAux.toString());
        System.out.println("[Enter name to delete, or press enter to cancel]");
        System.out.println("Do you want to delete " + personatgeAux.getName() + "?");
    }

    public void personEliminat(Personatge personatgeAux){
        System.out.println("\nTavern keeper: \"I'm sorry kiddo, but you have to leave.\"\n");
        System.out.println("Character " + personatgeAux.getName() + " left the Guild");
    }


//------------------------------------------------------------ Cas 3 -----------------------------------------------------------------


    public void createAventura(){
        System.out.println("Tavern keeper: \"Planning an adventure? Good luck with that!\"");
        System.out.println("-> Name your adventure: ");
    }

    public void existAdventure(){
        System.out.println("This adventure already exists\n");
    }

    public void encounters(String nomAventura){
        System.out.println("Tavern keeper: \"You plan to undertake " + nomAventura + ", really?\"\n" +
                "\"How long will that take?\"");
        System.out.println("-> How many encounters do you want [1..4]: ");
    }

    public void errorEncounters(){
        System.out.println("\tInvalid format of encounters");

    }

    public void errorEncounters2(){
        System.out.println("\tPlease enter a valid number: ");
    }

    public void muchTries(){
        System.out.println("Too much tries");
    }

    public void sumEncounters(int enfrentaments){
        System.out.println("Tavern keeper: \"" + enfrentaments + " encounters? That is too much for me...\"");
    }

    public void encounter(int j, int enfrentaments){
        System.out.println("* Encounter " + j + " / " + enfrentaments);
        System.out.println("* Monsters in encounter");
    }

    public void empty(){
        System.out.println("\t#Empty");
    }

    public void showMonsters(int k, Monstre monstresAux){
        System.out.println("\t" + (k + 1) + ". " + monstresAux.getName() + " (X" + monstresAux.getQuantitat() + ")");
    }

    public void menuEncounter(){
        System.out.println("\n1. Add monster\n2. Remove monster\n3. Continue\n");
        System.out.println("-> Enter an option [1..3]");
    }

    public void errormenu(){
        System.out.println("\tInvalid format of option");
    }

    public void errormenu2(){
        System.out.println("\tPlease enter a valid number: ");
    }

    public void showAllMonsters(int k, Monstre monstresAux){
        System.out.println((k + 1) + ". " + monstresAux.getName() + " (" + monstresAux.getChallenge() + ")");
    }

    public void chooseMonster(ArrayList<Monstre> monstres){
        System.out.println("-> Choose a monster to add [1.." + monstres.size() + "]:");
    }

    public void errorMonsters(){
        System.out.println("\tInvalid format for monster.");
    }

    public void errorMonsters2(){
        System.out.println("\tPlease enter a valid number: ");
    }

    public void monstersQuantity(Monstre monstresAux){
        System.out.println("-> How much " + monstresAux.getName() + "(s) do you want to add: ");
    }

    public void deleteMonster(){
        System.out.println("-> Wich monster do you want to delate: ");
    }

    public void monsterDeleted(Monstre monstreAux){
        System.out.println(monstreAux.getQuantitat() + " " + monstreAux.getName() + "were removed from the encounter.");
    }

    public void endCase3(String nomAventura){
        System.out.println("Tavern keeper: \"Great plan lad! I hope you won’t die!\"");
        System.out.println("The new adventure " + nomAventura + " has been created.\n");
    }

//--------------------------------------------------------- Case 4------------------------------------------------------------


    public void startAdventure(){
        System.out.println("Tavern keeper: \"So, you are looking to go on an adventure?\"\n" +
                "\"Where do you fancy going?\"");
        System.out.println("Available adventures:");
    }

    public void aventures(int k, Aventura z){
        System.out.println((k + 1) + ". " + z.getNom());
    }

    public void errorPersonatges(){
        System.out.println("Falten personatges, minim 3.");
    }

    public void chooseAdv(){
        System.out.println("-> Choose an adventure: ");
    }

    public void errorAdv(){
        System.out.println("\tInvalid format for adventure.");
    }

    public void errorAdv2(){
        System.out.println("\tPlease enter a valid number: ");
    }

    public void confirmAdv(Aventura nomAcenturaAux){
        System.out.println("Tavern keeper: \"" + nomAcenturaAux.getNom() + " it is!\"\n" +
                "\"And how many people shall join you?\"");
        System.out.println("-> Choose a number of characters [3..5]: ");
    }

    public void confirmChar(int numCharacters){
        System.out.println("Tavern keeper: \"Great," + numCharacters + " it is.\"\n" +
                "\"Who among these lads shall join you?\"");
    }

    public void indexParty(int i, int numCharacters){
        System.out.println("------------------------------");
        System.out.println("Your party (" + i + "/ " + numCharacters + "):");
    }

    public void emptyParty(int j){
        System.out.println((j + 1) + ". Empty");
    }

    public void printchar(int j, Personatge charactersAux){
        System.out.println((j + 1) + ". " + charactersAux.getName());
    }

    public void charAvailable(){
        System.out.println("------------------------------");
        System.out.println("Available characters:");
    }

    public void charList(int j, Personatge pAux){
        System.out.println((j + 1) + ". " + pAux.getName());
    }

    public void chooseChar(int i){
        System.out.println("-> Choose character " + (i + 1) + " in your party:");
    }

    public void indexPartyEnd(int numCharacters){
        System.out.println("------------------------------");
        System.out.println("Your party (" + numCharacters + "/ " + numCharacters + "):");
    }

    public void startAdventure(Aventura nomAcenturaAux){
        System.out.println("------------------------------\n");
        System.out.println("Tavern keeper: \"Great, good luck on your adventure lads!\"");
        System.out.println("The \"" + nomAcenturaAux.getNom() + "\" will start soon...\n");
    }

    public void startEncounter(int i){
        System.out.println("---------------------");
        System.out.println("Starting Encounter " + (i + 1) + ":");
    }

    public void monstresEncounter(Monstre monstreAux){
        System.out.println("- " + monstreAux.getQuantitat() + "x " + monstreAux.getName());
    }

    public void prepariationStage(){
        System.out.println("---------------------\n\n");
        System.out.println("-------------------------\n" +
                "*** Preparation stage ***\n" +
                "-------------------------");
    }

    public void prepaAV(Personatge personatge){
        System.out.println(personatge.getName() + " uses Self-Motivated. Their Spirit increases in +1.\n");
    }

    public void prepaChamp(Personatge personatge){
        System.out.println(personatge.getName() + "uses Motivational speech. Everyone’s Spirit increases in +1.\n");
    }

    public void prepaClrg(Personatge personatge){
        System.out.println(personatge.getName() + " uses Prayer of good luck. Everyone’s Mind increases in +1.\n");
    }

    public void prepaPldi(Personatge personatge, int num){
        System.out.println(personatge.getName() + " uses Blessing of good luck. Everyone’s Mind increases in " + num + ".\n");
    }

    public void prepaMag(){

    }

    public void rollIniciative(){
        System.out.println("Rolling initiative...");
    }

    public void printInciative(Combat ordreAux){
        System.out.println("- " + ordreAux.getIniciativa() + "\t" + ordreAux.getNom());
    }

    public void combatStage(){
        System.out.println("--------------------\n" +
                "*** Combat stage ***\n" +
                "--------------------");
    }

    public void round(int z){
        System.out.println("Round " + z);
        System.out.println("Party: ");
    }

    public void personHealth(Personatge personatge){
        System.out.println("- " + personatge.getName() + "\t" + personatge.getHitPoints() + " / " + personatge.getMaxPoints() + " hit points");
    }

    public void bossAttack(Monstre monstre, StringBuilder sb, int dmg){
        System.out.println(monstre.getName() + " attacks " + sb.toString() + ".\n"
                + "Hits and deals " + dmg + " physical damage.\n");
    }

    public void inconcient(Combat player){
        System.out.println(player.getNom() + " inconcient.");
    }

    public void monsterAttack(Monstre monstre, Combat player, int dmg){
        System.out.println(monstre.getName() + " attacks " + player.getNom() + ".\n"
                + "Hits and deals " + dmg + " physical damage.\n");
    }

    public void personAttack(Personatge personatge, Combat monstre, int dmg){
        System.out.println(personatge.getName() + " attacks " + monstre.getNom() + " with Sword slash. \n" +
                "Hits and deals " + dmg + " physical damage.\n");
    }

    public void dies(Combat monstre){
        System.out.println(monstre.getNom() + " dies.");
    }

    public void end(int z){
        System.out.println("End of round " + z);
    }

    public void defeated(){System.out.println("All enemies are defeated.");}

    public void restStage(){
        System.out.println("------------------------\n" +
                "*** Short rest stage ***\n" +
                "------------------------\n");
    }

    public void gainXp(Personatge aux, int xp){
        System.out.println(aux.getName() + " gains " + xp + " xp.");
    }

    public void heals(Combat aux, int cura){
        System.out.println(aux.getNom() + " uses Bandage time. Heals " + cura + " hit points");
    }

    public void unconscious(Combat aux){
        System.out.println(aux.getNom() + " is unconscious.");
    }

//-------------------------------------------------- Cas 5 ---------------------------------------------------------------

    public void leave(){
        System.out.println("Tavern keeper: " + "\"" + "Are you leaving already? See you soon, adventurer." + "\"");
    }

    public void invalid(){
        System.out.println("Invalid option!");
    }

    public void evolve1(Personatge aux){
        System.out.println(aux + " evolves to Champion");
    }
    public void evolve2(Personatge aux){
        System.out.println(aux + " evolves to Warrior");
    }
    public void evolve3(Personatge aux){
        System.out.println(aux + " evolves to Paladin");
    }


}
