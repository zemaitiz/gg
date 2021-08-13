package com.zemaitiz.gihub.mathgame.gg;


import com.zemaitiz.gihub.mathgame.gg.model.User;
import com.zemaitiz.gihub.mathgame.gg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.Random;
import java.util.Scanner;


//todo pasidomet sintakses stiliumi
@Component
public class App {

    @Autowired
    private UserService userService;

    private static final Scanner SCANNER = new Scanner(System.in);
    private static int score;

//todo ivedant varda, tikrinti, ar toks useris jau egzistuoja
    //todo pasidaryt db, kuri neisnyktu aplikacijai issijungus - tikrinsime, ar toks useris egzistuoja
    //todo parasyti prisijungiant info papildomos (highscore etc.)

    public void initializeGame() {
        createUser();
        enterMainMenu();
    }







    private void createUser() {
        System.out.println("Įveskite savo vardą:");
        String userName = SCANNER.nextLine();
        User user = userService.createUser(userName);
        System.out.println("Sveiki, " + user.getName() + "!");
    }

    //todo kiekvienam skirtingam modui tureti atskira metoda - arba tureti viena metoda su parametrais

    //todo sugalvot veikiancia validacija, while true loopai pvz.


    private static void enterMainMenu() {
        System.out.println("Pasirinkite žaidimo tipą:");
        System.out.println("I - infinite " + "|" + "T - time " + "|" + "P - perfect");
        String mode = SCANNER.nextLine();
        switch (mode) {
            case "i":
                startInifinityGame();
                break;
            case "t":
                startTimeGame();
                break;
            case "p":
                startPerfectGame();
                break;
        }
    }
    public static void startInifinityGame() {
        System.out.println("Įrašykite, kiek klausimų norite atsakyti. Jei norite begalybės klausimų, įveskite b:");
        String answer = SCANNER.nextLine();
        while (!isInputValid(answer)) {
            System.out.println("Įrašykite, kiek klausimų norite atsakyti. Jei norite begalybės klausimų, įveskite b:");
            answer = SCANNER.nextLine();
        }


        if (answer.equalsIgnoreCase("b")) {
            while (true) {
                //todo kaip iseiti is loopo
                baseGame();
                }

        } else {
            for (int i = 0; i < Integer.parseInt(answer); i++) {
                baseGame();
            }
        }
        System.out.println("Jūsų surinkti taškai: " + score);
    }

    private static boolean isInputValid(String answer) {
        try {
            int parsedAnswer = Integer.parseInt(answer);
            if (parsedAnswer <= 0) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Įrašykite normalų skaičių arba b!");
            return false;
        }
        return true;
    }

    public static void startTimeGame() {
        int userAnswer;
        while (true) {
            Random random = new Random();
            int a = random.nextInt(11);
            int b = random.nextInt(11);

            System.out.println(a + " + " + b + " = ?");
            long start = System.nanoTime();
            userAnswer = SCANNER.nextInt();
            SCANNER.nextLine();
            long finish = System.nanoTime();
            long timeElapsed = (finish - start) / 1_000_000_000;
            System.out.println(timeElapsed);
            if (userAnswer == a + b && timeElapsed <= 10) {
                System.out.println("Teisingai!");
                score += 10;
                System.out.println("Jūsų surinkti taškai: " + score);
            } else if (userAnswer != a + b) {
                System.out.println("Neteisingai!");
                System.out.println("Jūsų surinkti taškai: " + score);
            } else if (timeElapsed > 10) {
                System.out.println("Nespėjote!");
                System.out.println("Jūsų surinkti taškai: " + score);
                score = 0;
                break;
            }
        }
    }


    public static void startPerfectGame() {
        int userAnswer;
        while (true) {
            Random random = new Random();
            int a = random.nextInt(11);
            int b = random.nextInt(11);


            System.out.println(a + " + " + b + " = ?");
            userAnswer = SCANNER.nextInt();
            SCANNER.nextLine();
            if (userAnswer == a + b) {
                System.out.println("Teisingai!");
                score += 10;
                System.out.println("Jūsų surinkti taškai: " + score);
            } else {
                System.out.println("Neteisingai... :(");
                System.out.println("Jūsų surinkti taškai: " + score);
                score = 0;
                break;
            }
        }
    }


    public static void baseGame() {
        Random random = new Random();
        int a = random.nextInt(11);
        int b = random.nextInt(11);



        System.out.println(a + " + " + b + " = ?");
        String userAnswer = SCANNER.nextLine();

        if (Integer.parseInt(userAnswer) == a + b) {
            System.out.println("Teisingai!");
            score += 10;
        }
         if(userAnswer.equalsIgnoreCase("999"))  {
            System.out.println("Surinkti taškai: " + score);
            enterMainMenu();
        }  else {
            System.out.println("Neteisingai... :(");
        }

    }
}


//todo saugoti i db, koks useris i koki klausima atsakinejo (per kiek laiko atsake ir t.t.), koki mode zaide (Game panaudojimas)
