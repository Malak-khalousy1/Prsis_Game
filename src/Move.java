public class Move {
    int from;
    int to;
    int player;
    int pieceIndex;
    int numOfPoints = 0 ;

    public Move(int from, int to, int player, int pieceIndex) {
        this.from = from;
        this.to = to;
        this.player = player;
        this.pieceIndex = pieceIndex;

    }
    public Move(int from, int to, int player, int pieceIndex, int numOfPoints) {
        this.from = from;
        this.to = to;
        this.player = player;
        this.pieceIndex = pieceIndex;
        this.numOfPoints = numOfPoints ;

    }

}
