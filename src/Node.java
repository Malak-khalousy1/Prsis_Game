public class Node {
    Board board;
    Node parent;
    int currentPlayer;

    public Node(Board board, Node parent, int currentPlayer) {
        this.board = board;
        this.parent = parent;
        this.currentPlayer = currentPlayer;
    }

    public Board deepCopy() {
        return new Board(board);
    }
}
