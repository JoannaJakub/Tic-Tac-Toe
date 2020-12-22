import java.lang.reflect.Array;
import java.util.*;


public class Tic {
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();


    /**
     * Creating board.
     * Asking user to enter number.
     * Checking if position is occupied by player.
     * Generate random number.
     * Checking if position is occupied by CPU.
     */
    public static void main(String[] args) {

        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};


        while (true) {
            /**
             * Asking user to enter number.
             */

            Scanner scan = new Scanner(System.in);
            System.out.println("Enter your placement (1-9): ");
            int playerPos = scan.nextInt();

            /**
             * Checking if position is occupied by player.
             */
            while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPositions)) {
                System.out.println("Position taken. Enter a correct position");
                playerPos = scan.nextInt();
            }

            placePiece(gameBoard, playerPos, "player");

            /**
             * Generate random number.
             */
            String result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }

            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            /**
             * Checking if position is occupied by CPU.
             */
            while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
                cpuPos = rand.nextInt(9) + 1;
            }
            placePiece(gameBoard, cpuPos, "cpu");

            printGameBoard(gameBoard);

            result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }
        }
    }

    /**
     * Printing board.
     */
    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    /**
     * Placing number chose by the user
     */

    public static void placePiece(char[][] gameBoard, int position, String user) {
        char symbol = ' ';

        if (user.equals("player")) {
            symbol = 'X';
            playerPositions.add(position);
        } else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPositions.add(position);
        }

        switch (position) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    /**
     * Checking if someone win.
     */
    public static String checkWinner() {
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 9);
        List rightCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(7, 5, 3);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        for (List l : winning) {
            if (playerPositions.containsAll(l)) {
                return "Congratulation you won!";
            } else if (cpuPositions.containsAll(l)) {
                return "Cpu won. Try again!";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                return "CAT!";
            }
        }
        return "";
    }
}