import java.util.ArrayList;
import java.util.List;

public class Structure {

    public static List<Node> getNextStates(Node node, int number_shells_up) {
        List<Move> moves = getPossibleMoves(node.currentPlayer, node.board,number_shells_up);
        List<Node> newNodes = new ArrayList<>();
        for (Move move : moves) {
            Node newNode = applyMove(node, move);
            newNodes.add(newNode);
        }
        return newNodes;
    }

    public static Node applyMove(Node node, Move move) {
        // Board newBoard = new Board(node.board);
        Board newBoard = node.deepCopy();
        int[] player;
        int nextPlayer;
        if (move.player == 1) {
            player = newBoard.fp;
            nextPlayer = 0;

        } else {
            player = newBoard.sp;
            nextPlayer = 1;
        }

        player[move.pieceIndex] = move.to;
        if(move.player == 1) {
            for (int i = 0; i < newBoard.sp.length; i++) {
                if (newBoard.sp[i] == -1) continue;
                if (newBoard.path1[player[move.pieceIndex]].equals( newBoard.path2[newBoard.sp[i]] )) {
                    newBoard.sp[i] = -1;
                }
            }
        }
        else if(move.player == 0) {
            for (int i = 0; i < newBoard.fp.length; i++) {
                if (newBoard.fp[i] == -1) continue;
                if(newBoard.path2[player[move.pieceIndex]].equals( newBoard.path1[newBoard.fp[i]])  ){
                    newBoard.fp[i] = -1;

                }
            }
        }
        return new Node(newBoard, node, nextPlayer);
    }

    public static List<Move> getPossibleMoves(int currentPlayer, Board board, int number_shells_up) {

        int[] player;
        if (currentPlayer == 1) player = board.fp;
        else player = board.sp;
        List<Move> moves = new ArrayList<>();

        switch (number_shells_up) {
            case 0:
                //bara
                for (int i = 0; i < player.length; i++) {
                    if (player[i] == -1) continue;
                    if (canMove(board, currentPlayer, i, 12)) {
                        moves.add(new Move(player[i], player[i] + 12, currentPlayer, i, 12));
                    }
                }

                break;
            case 1:
                //bng
                for (int i = 0; i < player.length; i++) {
                    if (player[i] == -1) {
                        moves.add(new Move(-1, 0, currentPlayer, i, 1));
                    } else if (canMove(board, currentPlayer, i, 1)) {
                        moves.add(new Move(player[i], player[i] + 1, currentPlayer, i, 1));
                    }


                    if (canMove(board, currentPlayer, i, 25) && player[i]!=-1) {
                        moves.add(new Move(player[i], player[i] + 25, currentPlayer, i, 25));
                    }
                }

                break;
            case 2:
                //four
                for (int i = 0; i < player.length; i++) {
                    if (player[i] == -1) continue;
                    if (canMove(board, currentPlayer, i, 4)) {
                        moves.add(new Move(player[i], player[i] + 4, currentPlayer, i, 4));
                    }
                }
                break;
            case 3:
                //three
                for (int i = 0; i < player.length; i++) {
                    if (player[i] == -1) continue;
                    if (canMove(board, currentPlayer, i, 3)) {
                        moves.add(new Move(player[i], player[i] + 3, currentPlayer, i, 3));
                    }
                }
                break;
            case 4:
                //two
                for (int i = 0; i < player.length; i++) {
                    if (player[i] == -1) continue;
                    if (canMove(board, currentPlayer, i, 2)) {
                        moves.add(new Move(player[i], player[i] + 2, currentPlayer, i, 2));
                    }
                }
                break;
            case 5:
                //dst
                for (int i = 0; i < player.length; i++) {
                    if (player[i] == -1) {
                        moves.add(new Move(-1, 0, currentPlayer, i, 1));
                    } else if (canMove(board, currentPlayer, i, 1)) {
                        moves.add(new Move(player[i], player[i] + 1, currentPlayer, i, 1));
                    }
                    if (canMove(board, currentPlayer, i, 10) && player[i]!=-1) {
                        moves.add(new Move(player[i], player[i] + 10, currentPlayer, i, 10));
                    }
                }

                break;
            case 6:
                //shkeh
                for (int i = 0; i < player.length; i++) {
                    if (player[i] == -1) continue;
                    if (canMove(board, currentPlayer, i, 6)) {
                        moves.add(new Move(player[i], player[i] + 6, currentPlayer, i, 6));
                    }
                }

                break;
        }

        return moves;
    }

    public static Throw getThrow() {
        return new Throw();
    }

    public static boolean canMove(Board board, int currentPlayer, int pieceIndex, int steps) {
        int[] cplayer, splayer;
        Position[] path, spath;
        if (currentPlayer == 1) {
            cplayer = board.fp;
            path = board.path1;
            splayer = board.sp;
            spath = board.path2;
        } else {
            cplayer = board.sp;
            path = board.path2;
            splayer = board.fp;
            spath = board.path1;
        }
        if (cplayer[pieceIndex] + steps >= path.length) {
            return false;
        }
        Position pos1 = path[cplayer[pieceIndex] + steps];
        for (int i = 0; i < board.X.length; i++) {
            if (pos1.equals(board.X[i])) {
                for (int pos : splayer) {
                    if (pos == -1) continue;
                   // Position pos2 = spath[splayer[pos]];
                    Position pos2 = spath[pos];
                    if (pos1.equals(pos2)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean isWin(Node node, int currentPlayer) {
        int[] player;
        if (currentPlayer == 1) player = node.board.fp;
        else player = node.board.sp;
        for (int i = 0; i < player.length; i++) {
            if (player[i] < 83) {
                return false;
            }
        }
        return true;
    }


    public static boolean isFinished(Node node) {
        return (isWin( node,1) || isWin(node,0));
    }



//    public static int evaluate(Node node) {
//        if (isWin(node,'1')) {
//            return Integer.MAX_VALUE;
//        } else if (isWin(node,'0')) {
//            return Integer.MIN_VALUE;
//        }else
//            return 0;
//    }

    static int evaluate(Node node) {

        int cosp=0;
        // if fp+25 == sp ممكن بقتلني فا مو منيح
        for(int i=0;i<node.board.sp.length;i++) {
            if (node.board.sp[i] == node.board.fp[i]+25
            ||node.board.sp[i] == node.board.fp[i]+10
                    ||node.board.sp[i] == node.board.fp[i]+12
                    || node.board.sp[i] == node.board.fp[i]+2
                    || node.board.sp[i] == node.board.fp[i]+6
                    ||node.board.sp[i] == node.board.fp[i]+3
                    ||node.board.sp[i] == node.board.fp[i]+4
                    || node.board.sp[i] == node.board.fp[i]+1
            ) {
                cosp++;
            }
            cosp--;
        }
        // status distance 1 and rest   nested for
        for(int i=0; i<node.board.sp.length; i++) {
            if (node.board.sp[0] > node.board.fp[i]) {
                cosp--;
            }
            else {
                cosp++;
            }

            if (node.board.sp[1] > node.board.fp[i]) {
                cosp--;
            }
            else {
                cosp++;
            }

            if (node.board.sp[2] > node.board.fp[i]) {
                cosp--;
            }
            else {
                cosp++;
            }

            if (node.board.sp[3] > node.board.fp[i]) {
                cosp--;
            }
            else {
                cosp++;
            }

        }

        //هون انا ممكن اني اتحرك عدد خطوات معينة عحسب الرمية اللي عم تطلعلي فاذا تحققى  احد الشروط ممتاز
        for(int i=0; i<node.board.sp.length; i++) {
            if(node.board.sp[i]+1==node.board.fp[i]){
                cosp--;
            }
            else {
                cosp++;
            }
            if(node.board.sp[i]+10==node.board.fp[i]){
                cosp--;
            }
            else {
                cosp++;
            }
            if(node.board.sp[i]+25==node.board.fp[i]){
                cosp--;
            }
            else {
                cosp++;
            }
            if(node.board.sp[i]+12==node.board.fp[i]){
                cosp--;
            }
            else {
                cosp++;
            }
            if(node.board.sp[i]+6==node.board.fp[i]){
                cosp--;
            }
            else {
                cosp++;
            }
            if(node.board.sp[i]+3==node.board.fp[i]){
                cosp--;
            }
            else {
                cosp++;
            }
            if(node.board.sp[i]+4==node.board.fp[i]){
                cosp--;
            }
            else {
                cosp++;
            }
        }


        return cosp;
    }

}
