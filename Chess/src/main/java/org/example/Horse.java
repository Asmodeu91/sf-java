package org.example;

public class Horse extends ChessPiece{
    public Horse (String color)  {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (line == toLine && column == toColumn) {
            return false;
        }
        if (toLine < 0 || toLine >= 8 || toColumn < 0 || toColumn >= 8) {
            return false;
        }
        if ((Math.abs(toColumn - column) == 2 && Math.abs(toLine - line) == 1) ||
                (Math.abs(toColumn - column) == 1 && Math.abs(toLine - line) == 2)) {
            ChessPiece targetPiece = ChessBoard.getPieceAt(toColumn, toLine);
            if (targetPiece == null || !targetPiece.getColor().equals(this.color)) {
                return true;
            }
        }
        return false;
   }

   public String getSymbol(){
        return "H";
   }

}
