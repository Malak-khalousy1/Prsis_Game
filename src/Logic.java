import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Logic {
    static char human = '1';
    static char computer = '0';
    static Board board = new Board();
    static Node root = new Node(board, null, 1);


    public static void play_User_VS_User() {

        System.out.println(root.board);
        Node node = root;
        while (true) {

            node = human2Play(node);
            System.out.println(node.board);
            if (Structure.isWin(node, computer)) {
                System.out.println("I win :D I'm way smarter than you");
                break;
            }

            node = human1Play(node);
            System.out.println(node.board);
            System.out.println(Arrays.toString(node.board.sp));
            System.out.println(Arrays.toString(node.board.fp));
            if (Structure.isWin(node, human)) {
                System.out.println("You Win I");
                break;
            }

        }
    }

    public static void User_VS_computer_minimax() {

        System.out.println(root.board);
        Node node = root;
        while (true) {

            node = computer_minimax(node);
            System.out.println(node.board);
            if (Structure.isWin(node, computer)) {
                System.out.println("I win :D I'm way smarter than you");
                break;
            }

            node = human1Play(node);
            System.out.println(node.board);
            System.out.println(Arrays.toString(node.board.sp));
            System.out.println(Arrays.toString(node.board.fp));
            if (Structure.isWin(node, human)) {
                System.out.println("You Win I");
                break;
            }

        }
    }

    public static void User_VS_computer_alpha_beta() {

        System.out.println(root.board);
        Node node = root;
        while (true) {

            node = computer_alpha_beta(node);
            System.out.println(node.board);
            if (Structure.isWin(node, computer)) {
                System.out.println("I win :D I'm way smarter than you");
                break;
            }

            node = human1Play(node);
            System.out.println(node.board);
            System.out.println(Arrays.toString(node.board.sp));
            System.out.println(Arrays.toString(node.board.fp));
            if (Structure.isWin(node, human)) {
                System.out.println("You Win I");
                break;
            }

        }
    }

    public static void User_VS_computer_Expectiminimax() {

        System.out.println(root.board);
        Node node = root;
        while (true) {

            node = computer_expectimax(node);
            System.out.println(node.board);
            if (Structure.isWin(node, computer)) {
                System.out.println("I win :D I'm way smarter than you");
                break;
            }

            node = human1Play(node);
            System.out.println(node.board);
            System.out.println(Arrays.toString(node.board.sp));
            System.out.println(Arrays.toString(node.board.fp));
            if (Structure.isWin(node, human)) {
                System.out.println("You Win I");
                break;
            }

        }
    }


    static Node human1Play(Node node) {
        System.out.println("human1 turn :");
        Scanner scanner = new Scanner(System.in);
        Throw th = Structure.getThrow();
        int number_shells_up = 0;
        for (int i = 0; i < th.shell.length; i++) {
            if (th.shell[i]) number_shells_up++;
        }
        List<Move> moves = Structure.getPossibleMoves(1, node.board,number_shells_up);
        if (moves.isEmpty()) {
            return new Node(node.board, node, 0);
        }
        for (int i = 0; i < moves.size(); i++) {
            System.out.println("press " + i + " to move piece: " + moves.get(i).pieceIndex + "  from  " + moves.get(i).from + "  to  " + moves.get(i).to);
        }
        int moveIndex = scanner.nextInt();
        if (moves.get(moveIndex).numOfPoints == 10
                ||moves.get(moveIndex).numOfPoints == 6
                ||moves.get(moveIndex).numOfPoints == 25
                ||moves.get(moveIndex).numOfPoints == 12){

            return human1Play(Structure.applyMove(node, moves.get(moveIndex)));
        }
        return Structure.applyMove(node, moves.get(moveIndex));

    }

    static Node human2Play(Node node) {
        System.out.println("human2 turn :");
        Scanner scanner = new Scanner(System.in);
       Throw th = Structure.getThrow();
       int number_shells_up = 0;
        for (int i = 0; i < th.shell.length; i++) {
        if (th.shell[i]) number_shells_up++;
       }
        List<Move> moves = Structure.getPossibleMoves(0, node.board, number_shells_up);
        if (moves.isEmpty()) {
            return new Node(node.board, node, 1);
        }
        for (int i = 0; i < moves.size(); i++) {
            System.out.println("press " + i + " to move piece: " + moves.get(i).pieceIndex + "  from  " + moves.get(i).from + "  to  " + moves.get(i).to);
        }
        int moveIndex = scanner.nextInt();
        if (moves.get(moveIndex).numOfPoints == 10
                ||moves.get(moveIndex).numOfPoints == 6
                ||moves.get(moveIndex).numOfPoints == 25
                ||moves.get(moveIndex).numOfPoints == 12){

            return human2Play(Structure.applyMove(node, moves.get(moveIndex)));
        }
        return Structure.applyMove(node, moves.get(moveIndex));

    }

    static Node computer_alpha_beta(Node node) {
        System.out.println("computer turn :");
        Throw th = Structure.getThrow();
        int number_shells_up = 0;
        for (int i = 0; i < th.shell.length; i++) {
            if (th.shell[i]) number_shells_up++;
        }
        List<Move> moves = Structure.getPossibleMoves(0, node.board, number_shells_up);
        if (moves.isEmpty()) {
            return new Node(node.board, node, 1);
        }

        double maxValue = Double.NEGATIVE_INFINITY;
        Move bestMove = null;
        for (Move move : moves) {
            Node newNode = Structure.applyMove(node, move);
            double value = alpha_beta(newNode, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
            if (value > maxValue) {
                maxValue = value;
                bestMove = move;
            }
        }

        return Structure.applyMove(node, bestMove);
    }

    static double alpha_beta(Node node, int depth, int alpha, int beta, int player) {
        if (depth == 0 || Structure.isWin(node, player)) {
            return Structure.evaluate(node);
        }

        if (player == 0) {
            double value = Double.POSITIVE_INFINITY;
            Throw th = Structure.getThrow();
            int number_shells_up = 0;
            for (int i = 0; i < th.shell.length; i++) {
                if (th.shell[i]) number_shells_up++;
            }
            List<Move> moves = Structure.getPossibleMoves(0, node.board, number_shells_up);
            for (Move move : moves) {
                Node newNode = Structure.applyMove(node, move);
                value = Math.min(value, alpha_beta(newNode, depth - 1, alpha, beta, 1));
                alpha = (int) Math.max(alpha, value);
                if (beta <= alpha) {
                    break;
                }
            }
            return value;
        } else {
            double value = Double.NEGATIVE_INFINITY;
            Throw th = Structure.getThrow();
            int number_shells_up = 0;
            for (int i = 0; i < th.shell.length; i++) {
                if (th.shell[i]) number_shells_up++;
            }
            List<Move> moves = Structure.getPossibleMoves(1, node.board, number_shells_up);
            for (Move move : moves) {
                Node newNode = Structure.applyMove(node, move);
                value = Math.max(value, alpha_beta(newNode, depth - 1, alpha, beta, 0));
                beta = (int) Math.min(beta, value);
                if (beta <= alpha) {
                    break;
                }
            }
            return value;
        }
    }


    static Node computer_minimax(Node node) {
        System.out.println("computer turn :");
        Throw th = Structure.getThrow();
        int number_shells_up = 0;
        for (int i = 0; i < th.shell.length; i++) {
            if (th.shell[i]) number_shells_up++;
        }
        List<Move> moves = Structure.getPossibleMoves(0, node.board, number_shells_up);
        if (moves.isEmpty()) {
            return new Node(node.board, node, 1);
        }

        double maxValue = Double.NEGATIVE_INFINITY;
        Move bestMove = null;
        for (Move move : moves) {
            Node newNode = Structure.applyMove(node, move);
            double value = minimax(newNode, 0, 0);
            if (value > maxValue) {
                maxValue = value;
                bestMove = move;
            }
        }

        return Structure.applyMove(node, bestMove);
    }


    static double minimax(Node node, int depth, int player) {
        if (depth == 0 || Structure.isWin(node, player)) {
            return Structure.evaluate(node);
        }

        if (player == 0) {
            double value = Double.NEGATIVE_INFINITY;
            Throw th = Structure.getThrow();
            int number_shells_up = 0;
            for (int i = 0; i < th.shell.length; i++) {
                if (th.shell[i]) number_shells_up++;
            }
            List<Move> moves = Structure.getPossibleMoves(0, node.board, number_shells_up);
            for (Move move : moves) {
                Node newNode = Structure.applyMove(node, move);
                value = Math.max(value, minimax(newNode, depth - 1, 1));
            }
            return value;
        } else {
            double value = Double.POSITIVE_INFINITY;
            Throw th = Structure.getThrow();
            int number_shells_up = 0;
            for (int i = 0; i < th.shell.length; i++) {
                if (th.shell[i]) number_shells_up++;
            }
            List<Move> moves = Structure.getPossibleMoves(1, node.board, number_shells_up);
            for (Move move : moves) {
                Node newNode = Structure.applyMove(node, move);
                value = Math.min(value, minimax(newNode, depth - 1, 0));
            }
            return value;
        }
    }


//    static Node computer_expectimax(Node node) {
//        System.out.println("computer turn :");
//        Throw th = Structure.getThrow();
//        int number_shells_up = 0;
//        for (int i = 0; i < th.shell.length; i++) {
//            if (th.shell[i]) number_shells_up++;
//        }
//        List<Move> moves = Structure.getPossibleMoves(0, node.board, number_shells_up);
//        if (moves.isEmpty()) {
//            return new Node(node.board, node, 1);
//        }
//
//        double maxValue = Double.NEGATIVE_INFINITY;
//        Move bestMove = null;
//        for (Move move : moves) {
//            Node newNode = Structure.applyMove(node, move);
//            double value = expectimax(newNode, 0, 0);
//            if (value > maxValue) {
//                maxValue = value;
//                bestMove = move;
//            }
//        }
//
//        return Structure.applyMove(node, bestMove);
//    }



//    static double expectimax(Node node, int depth, int player) {
//        if (depth == 0 || Structure.isWin(node, player)) {
//            return Structure.evaluate(node);
//        }
//
//        if (player == 0) {
//            double value = Double.NEGATIVE_INFINITY;
//            Throw th = Structure.getThrow();
//            int number_shells_up = 0;
//            for (int i = 0; i < th.shell.length; i++) {
//                if (th.shell[i]) number_shells_up++;
//            }
//            List<Move> moves = Structure.getPossibleMoves(0, node.board, number_shells_up);
//            for (Move move : moves) {
//                Node newNode = Structure.applyMove(node, move);
//                value = Math.max(value, expectimax(newNode, depth - 1, 1));
//            }
//            return value;
//        } else {
//            double value = Double.POSITIVE_INFINITY;
//            Throw th = Structure.getThrow();
//            int number_shells_up = 0;
//            for (int i = 0; i < th.shell.length; i++) {
//                if (th.shell[i]) number_shells_up++;
//            }
//            List<Move> moves = Structure.getPossibleMoves(1, node.board, number_shells_up);
//            if (!moves.isEmpty()) {
//                for (Move move : moves) {
//                    Node newNode = Structure.applyMove(node, move);
//                    value = Math.min(value, expectimax(newNode, depth - 1, 0));
//                }
//                // Calculate the average value of all possible moves
//                value /= moves.size();
//            }
//            return value;
//        }
//    }


    static Node computer_expectimax(Node node) {
        System.out.println("computer turn :");
        Throw th = Structure.getThrow();
        int number_shells_up = 0;
        for (int i = 0; i < th.shell.length; i++) {
            if (th.shell[i]) number_shells_up++;
        }
        List<Move> moves = Structure.getPossibleMoves(0, node.board, number_shells_up);
        if (moves.isEmpty()) {
            return new Node(node.board, node, 1);
        }

        Move bestMove = null;
        int bestScore = Integer.MIN_VALUE;
        for (Move move : moves) {
            int score = chance(node, move, 3, Integer.MIN_VALUE, Integer.MAX_VALUE);
            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }

        return Structure.applyMove(node, bestMove);
    }

    static int chance(Node node, Move move, int depth, int alpha, int beta) {
        Node newNode = Structure.applyMove(node, move);
        if (Structure.isWin(newNode, computer)) {
            return 100; // Score for winning
        } else if (Structure.isWin(newNode, human)) {
            return -100; // Score for losing
        } else if (depth == 0) {
            return Structure.evaluate(newNode); // Evaluation function for non-terminal nodes
        }

        Throw th = Structure.getThrow();
        int number_shells_up = 0;
        for (int i = 0; i < th.shell.length; i++) {
            if (th.shell[i]) number_shells_up++;
        }

        List<Move> moves = Structure.getPossibleMoves(0, newNode.board, number_shells_up);
        int sum = 0;
        for (Move chanceMove : moves) {
            sum += expect(newNode, chanceMove, depth - 1, alpha, beta);
            alpha = Math.max(alpha, sum);

            if (beta <= alpha) {
                break; // Beta cutoff
            }
            int x= moves.size();
            if(x!=0){
                return sum/x;

            }
        }
        return sum /7;
    }

    static int expect(Node node, Move move, int depth, int alpha, int beta) {
        Node newNode = Structure.applyMove(node, move);
        if (Structure.isWin(newNode, computer)) {
            return 100; // Score for winning
        } else if (Structure.isWin(newNode, human)) {
            return -100; // Score for losing
        } else if (depth == 0) {
            return Structure.evaluate(newNode); // Evaluation function for non-terminal nodes
        }
        Throw th = Structure.getThrow();
        int number_shells_up = 0;
        for (int i = 0; i < th.shell.length; i++) {
            if (th.shell[i]) number_shells_up++;
        }
        List<Move> moves = Structure.getPossibleMoves(1, newNode.board, number_shells_up);
        for (Move opponentMove : moves) {
            int score = chance(newNode, opponentMove, depth - 1, alpha, beta);
            System.out.println(score+ " score ");
            beta = Math.min(beta, score);
            if (beta <= alpha) {
                break; // Alpha cutoff
            }
        }
        return beta;
    }


//    static Node computer_alpha_beta(Node node) {
//
//        Throw th = Structure.getThrow();
//        int number_shells_up = 0;
//        for (int i = 0; i < th.shell.length; i++) {
//            if (th.shell[i]) number_shells_up++;
//        }
//        Pair<Node,Integer> maxMove =maxMove(node, Integer.MIN_VALUE, Integer.MAX_VALUE,number_shells_up);
//        return maxMove.getFirst();
//    }
//    private static Pair<Node, Integer> maxMove(Node node, int alpha, int beta, int number_shells_up) {
//        if (Structure.isFinished(node)) {
//            return new Pair<>(node,Structure.evaluate(node));
//        }
//        int maxScore = Integer.MIN_VALUE;
//        Node maxBoard = null;
//        for (Node nextBoard : Structure.getNextStates(node,number_shells_up)) {
//            Pair<Node, Integer> result = minMove(nextBoard, alpha, beta,number_shells_up);
//            int score = result.getSecond();
//            if (score > maxScore) {
//                maxScore = score;
//                maxBoard = nextBoard;
//            }
//            alpha = Math.max(alpha, score);
//            if (beta <= alpha) {
//                break;
//            }
//        }
//        return new Pair<>(maxBoard, maxScore);
//    }
//
//    private static Pair<Node, Integer> minMove(Node b, int alpha, int beta, int number_shells_up) {
//        if (Structure.isFinished(b)) {
//            return new Pair<>(b, Structure.evaluate(b));
//        }
//        int minScore = Integer.MAX_VALUE;
//        Node minBoard = null;
//        for (Node nextBoard : Structure.getNextStates(b, number_shells_up)) {
//            Pair<Node, Integer> result = maxMove(nextBoard, alpha, beta, number_shells_up);
//            int score = result.getSecond();
//            if (score < minScore) {
//                minScore = score;
//                minBoard = nextBoard;
//            }
//            beta = Math.min(beta, score);
//            if (beta <= alpha) {
//                break;
//            }
//        }
//        return new Pair<>(minBoard, minScore);
//
//
//    }
//*********************************************************************
//    static Node computerPlay(Node node) {
//        System.out.println("computer turn :");
//        Throw th = Structure.getThrow();
//        int number_shells_up = 0;
//        for (int i = 0; i < th.shell.length; i++) {
//            if (th.shell[i]) number_shells_up++;
//        }
//        List<Move> moves = Structure.getPossibleMoves(0, node.board,number_shells_up);
//        if (moves.isEmpty()) {
//            return new Node(node.board, node, 1);
//        }
//
//        double maxValue = Double.NEGATIVE_INFINITY;
//        Move bestMove = null;
//        for (Move move : moves) {
//            Node newNode = Structure.applyMove(node, move);
//            double value = expectiminimax(newNode, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
//            if (value > maxValue) {
//                maxValue = value;
//                bestMove = move;
//            }
//        }
//
//        return Structure.applyMove(node, bestMove);
//    }
//
//
//    static double expectiminimax(Node node, int depth, int alpha, int beta, int player) {
//        System.out.println("Processing node at depth: " + depth);
//        System.out.println("Current node player: " + (player == 0 ? "AI" : "Opponent"));
//
//        if (depth == 0 || Structure.isWin(node, player)) {
//            double value = Structure.evaluate(node, player);
//            System.out.println("Evaluation function value: " + value);
//            return value;
//        }
//
//        double value;
//        if (player == 0) {
//            value = Double.POSITIVE_INFINITY;
//            List<Node> nextStates = Structure.getNextStates(node,);
//            System.out.println("Number of next states: " + nextStates.size());
//            for (Node nextState : nextStates) {
//                value = Math.min(value, expectiminimax(nextState, depth - 1, alpha, beta, 1));
//                alpha = (int) Math.max(alpha, value);
//                if (beta <= alpha) {
//                    break;
//                }
//            }
//        } else {
//            value = Double.NEGATIVE_INFINITY;
//            List<Node> nextStates = Structure.getNextStates(node);
//            System.out.println("Number of next states: " + nextStates.size());
//            for (Node nextState : nextStates) {
//                value = Math.max(value, expectiminimax(nextState, depth - 1, alpha, beta, 0));
//                beta = (int) Math.min(beta, value);
//                if (beta <= alpha) {
//                    break;
//                }
//            }
//        }
//        System.out.println("Returning value: " + value);
//        return value;
//    }

}
