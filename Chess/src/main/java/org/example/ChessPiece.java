package org.example;

public abstract class ChessPiece {

    String color;
    public int x, y;
    public boolean check = true;

    public ChessPiece(String color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String getColor() {
        return color;
    }



    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    public abstract String getSymbol();



}
