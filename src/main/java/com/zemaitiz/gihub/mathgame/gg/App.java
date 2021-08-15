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
        while (!isUserNameValid(userName)) {
            userName = SCANNER.nextLine();
        }
        User user = userService.createUser(userName);
        System.out.println("Sveiki, " + user.getName() + "!");
    }

    private boolean isUserNameValid(String userName) {
        try {
            if (userName.isEmpty()) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Nieko neįvedėte!");
            return false;
        }
        return true;
    }


    //todo kiekvienam skirtingam modui tureti atskira metoda - arba tureti viena metoda su parametrais

    //todo sugalvot veikiancia validacija, while true loopai pvz.


    private static void enterMainMenu() {
        System.out.println("Pasirinkite žaidimo tipą:");
        System.out.println("I - infinite " + "|" + "T - time " + "|" + "P - perfect");
        String mode = SCANNER.nextLine();
        while (!isGameModeValid(mode)) {
            mode = SCANNER.nextLine();
        }
        switch (mode) {
            case "i" -> startInifinityGame();
            case "t" -> startTimeGame();
            case "p" -> startPerfectGame();
        }
    }

    private static boolean isGameModeValid(String mode) {
        try {
            if (!mode.equalsIgnoreCase("i") && !mode.equalsIgnoreCase("t") && !mode.equalsIgnoreCase("p")) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Įveskite i, t arba pt!");
            return false;
        }
        return true;
    }


    public static void startInifinityGame() {
        String difficultyAnswer = getDifficultyAnswer();
        System.out.println("Įrašykite, kiek klausimų norite atsakyti. Jei norite begalybės klausimų, įveskite b:");
        String answer = SCANNER.nextLine();
        while (!isInputValid(answer)) {
            answer = SCANNER.nextLine();
        }


        if (answer.equalsIgnoreCase("b")) {
            System.out.println("Norėdami baigti žaidimą ir grįžti į pagrindinį meniu, įveskite end");
            while (true) {
                rootGame(difficultyAnswer);
            }

        } else {
            for (int i = 0; i < Integer.parseInt(answer); i++) {
                rootGame(difficultyAnswer);
            }
        }
        System.out.println("Jūsų surinkti taškai: " + score);
    }

    private static String getDifficultyAnswer() {
        System.out.println("Pasirinkite sunkumo lygį.");
        System.out.println("E - easy |" + "M-medium |" + "H- hard");
        String difficultyAnswer = SCANNER.nextLine();
        while (!isDifficultyAnswerValid(difficultyAnswer)) {
            difficultyAnswer = SCANNER.nextLine();
        }
        return difficultyAnswer;
    }

    private static boolean isDifficultyAnswerValid(String difficultyAnswer) {
        try {
            if (!difficultyAnswer.equalsIgnoreCase("e") && !difficultyAnswer.equalsIgnoreCase("m") && !difficultyAnswer.equalsIgnoreCase("h")) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Įveskite e, m arba h!");
            return false;
        }
        return true;
    }

    private static boolean isInputValid(String answer) {
        try {
            if (!answer.equalsIgnoreCase("b") && Integer.parseInt(answer, 29) <= 0 ) {
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

    private static void rootGame(String difficulty) {
        Random random = new Random();
        int maxNumber = switch (difficulty) {
            case "e" -> 11;
            case "m" -> 101;
            case "h" -> 1001;
            default -> 0;
        };

        int a = random.nextInt(maxNumber);
        int b = random.nextInt(maxNumber);


        System.out.println(a + " + " + b + " = ?");
        String userAnswer = SCANNER.nextLine();
        try {
            if (userAnswer.equalsIgnoreCase("end")) {
                System.out.println("Surinkti taškai: " + score);
                System.out.println("------------------------------------------------");
                enterMainMenu();
            }
            if (userAnswer.isEmpty()) {
                throw new NumberFormatException();
            }
            if (Integer.parseInt(userAnswer) == a + b) {
                System.out.println("Teisingai!");
                score += 10;
            } else {
                System.out.println("Neteisingai... :(");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ar tikrai įvedėte atsakymą?");
        }
    }
}


//todo saugoti i db, koks useris i koki klausima atsakinejo (per kiek laiko atsake ir t.t.), koki mode zaide (Game panaudojimas)
