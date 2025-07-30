import java.util.Arrays;

public class Board {
    Position[] path1 = new Position[84];
    Position[] path2 = new Position[84];
    Position[] X = new Position[8];
    int[] fp = new int[4];
    int[] sp = new int[4];

    public Board() {

        int[] xCol = {2, 2, 8, 8, 10, 10, 16, 16}; // the column numbers of the X positions
        int[] xRow = {8, 10, 2, 16, 2, 16, 8, 10}; // the row numbers of the X positions
        for (int i = 0; i < X.length; i++) {
            int row = xRow[i];
            int col = xCol[i];
            X[i] = new Position(row, col);
        }

        int[] p1Col = {9, 9, 9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 10, 10, 11, 12, 13, 14, 15, 16, 17, 18, 18, 18, 17, 16, 15, 14, 13, 12, 11, 10, 10, 10, 10, 10, 10, 10, 10, 9, 8, 8, 8, 8, 8, 8, 8, 8, 7, 6, 5, 4, 3, 2, 1, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9, 9

        }; // the column numbers of the path1 positions

        int[] p1Row = {11, 12, 13, 14, 15, 16, 17, 18, 18, 17, 16, 15, 14, 13, 12, 11, 10, 10, 10, 10, 10, 10, 10, 10, 9, 8, 8, 8, 8, 8, 8, 8, 8, 7, 6, 5, 4, 3, 2, 1, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 8, 8, 8, 8, 8, 8, 8, 9, 10, 10, 10, 10, 10, 10, 10, 10, 11, 12, 13, 14, 15, 16, 17, 18, 18, 17, 16, 15, 14, 13, 12, 11, 10

        }; // the row numbers of the path1 positions

        for (int i = 0; i < path1.length; i++) {
            int row = p1Row[i];
            int col = p1Col[i];
            path1[i] = new Position(row, col);
        }


        int[] p2Col = {9, 9, 9, 9, 9, 9, 9, 9, 8, 8, 8, 8, 8, 8, 8, 8, 7, 6, 5, 4, 3, 2, 1, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 8, 8, 8, 8, 8, 8, 8, 9, 10, 10, 10, 10, 10, 10, 10, 10, 11, 12, 13, 14, 15, 16, 17, 18, 18, 18, 17, 16, 15, 14, 13, 12, 11, 10, 10, 10, 10, 10, 10, 10, 10, 9, 9, 9, 9, 9, 9, 9, 9, 9


        }; // the column numbers of the path2 positions

        int[] p2Row = {7, 6, 5, 4, 3, 2, 1, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 8, 8, 8, 8, 8, 8, 8, 9, 10, 10, 10, 10, 10, 10, 10, 10, 11, 12, 13, 14, 15, 16, 17, 18, 18, 18, 17, 16, 15, 14, 13, 12, 11, 10, 10, 10, 10, 10, 10, 10, 10, 9, 8, 8, 8, 8, 8, 8, 8, 8, 7, 6, 5, 4, 3, 2, 1, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8

        }; // the row numbers of the path2 positions

        for (int i = 0; i < path2.length; i++) {
            int row = p2Row[i];
            int col = p2Col[i];
            path2[i] = new Position(row, col);
        }
//        for (int i = 0; i < fp.length; i++) {
//            fp[i] =25;
//        }

        //for (int i = 0; i < sp.length; i++) {
        //    sp[0] = 43;
        //}

        Arrays.fill(fp, -1);
        Arrays.fill(sp, -1);
//        sp[0] = 43;
//        sp[1] = 0;
//        fp[0] = 20;
//        ;
    }

    public Board(Board board) {
        for (int i = 0; i < this.path1.length; i++) {
            this.path1[i] = new Position(board.path1[i]);
        }
        for (int i = 0; i < this.path2.length; i++) {
            this.path2[i] = new Position(board.path2[i]);
        }
        for (int i = 0; i < this.X.length; i++) {
            this.X[i] = new Position(board.X[i]);
        }
        System.arraycopy(board.fp, 0, this.fp, 0, this.fp.length);
        System.arraycopy(board.sp, 0, this.sp, 0, this.sp.length);
    }

    @Override
    public String toString() {

        char[][] board = new char[19][19];
        for (int i = 0; i < 8; i++) {
            Position P = X[i];

            int col = P.getX();
            int row = P.getY();
            board[col][row] = 'X';
        }
        for (int i = 0; i < 4; i++) {
            int P = fp[i];
            if (P != -1) {
                Position P1 = path1[P];

                int col = P1.getX();
                int row = P1.getY();
                board[col][row] = '*';
            }
        }
        for (int i = 0; i < 4; i++) {
            int P = sp[i];
            if (P != -1) {
                Position P1 = path2[P];

                int col = P1.getX();
                int row = P1.getY();
                board[col][row] = '#';
            }
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 19; i++) {
            sb.append(" | ");
            for (int j = 0; j < 19; j++) {
                if (board[i][j] == 'X') {
                    sb.append("X");
                    sb.append("  |  ");
                } else if (board[i][j] == '*') {
                    sb.append("*");
                    sb.append("  |  ");
                } else if (board[i][j] == '#') {
                    sb.append("#");
                    sb.append("  |  ");
                } else {
                    sb.append(" ");
                    sb.append("  |  ");
                }
            }

            sb.append('\n');
        }
        sb.append(" ");
        for (int i = 1; i < 19; i++) {
            sb.append("------");
        }
        sb.append("\n | ");
        for (int i = 1; i <= 19; i++) {
            sb.append(i);
            if (i < 10) sb.append("  |  ");
            else sb.append(" |  ");
        }

        return sb.toString();
    }
}
/* char[][] board = new char[19][19];
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {

                //prints

            }
        }
        String s = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                s += board[i][j];
            }
            s += '\n';
        }
        return s;*/