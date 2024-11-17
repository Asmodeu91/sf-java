package org.example;

public class Rook extends ChessPiece{

    public Rook (String color) {
        super(color);
    }
    public String getColor() {
        return color;
    }
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (toLine < 0 || toLine >= 8 || toColumn < 0 || toColumn >= 8) {
            return false;
        }
        if (toLine == line && toColumn == column) {
            return false;
        }
        if (toLine == line || toColumn == column) {
            if (!ChessBoard.isPathClear(chessBoard, line, column, toLine, toColumn)) {
                return false;
            }
            ChessPiece targetPiece = ChessBoard.getPieceAt(toColumn, toLine);
            if (targetPiece == null || !targetPiece.getColor().equals(this.color)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public String getSymbol() {
        return "R";
    }
}
