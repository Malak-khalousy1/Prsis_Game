import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) {
        while (true) {
            System.out.println("Choose Game Type:");
            System.out.println("q: Quit");
            System.out.println("1- User VS User ");
            System.out.println("2- User VS computer (Algorithm minimax)");
            System.out.println("3- User VS computer (Algorithm alpha_beta)");
            System.out.println("4- User VS computer (Algorithm Expectiminimax)");

            char option = new Scanner(System.in).next().charAt(0);

            if (option == 'q') {
                return;
            }
            //Logic.play();
            switch (option) {
                case '1':
                    Logic.play_User_VS_User();
                    break;
                case '2':
                    Logic.User_VS_computer_minimax();
                    break;
                case '3':
                    Logic.User_VS_computer_alpha_beta();
                    break;
                case '4':
                    Logic.User_VS_computer_Expectiminimax();
                    break;

            }



         }

    }
}