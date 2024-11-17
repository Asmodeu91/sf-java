package org.example;

public class Pawn extends ChessPiece {

    public Pawn (String color) {
        super(color);
        this.color = color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (toLine < 0 || toLine > 7 || toColumn < 0 || toColumn > 7) {
            System.out.println("1");
            return false;
        }
        if (toLine == line && toColumn == column) {
            System.out.println("2");
            return false;
        }
        int direction = color.equals("White") ? 1 : -1;
        System.out.println(line + " " + toLine + " ");
        if (column == toColumn && toLine == line + direction && chessBoard.board[toLine][toColumn] == null) {
            return true;
        }
        if (color.equals("White") && line == 1 && toLine == 3 && column == toColumn && chessBoard.board[2][toColumn] == null && chessBoard.board[toLine][toColumn] == null) {
            return true;
        } else if (color.equals("Black") && line == 6 && toLine == 4 && column == toColumn && chessBoard.board[5][toColumn] == null && chessBoard.board[toLine][toColumn] == null) {
            return true;
        }

        if (Math.abs(toColumn - column) == 1 && toLine == line + direction) {
            ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
            if (targetPiece != null && !targetPiece.getColor().equals(this.getColor())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}
