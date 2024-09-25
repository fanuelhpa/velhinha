package com.fandevv.velhinha.entities;

public class Player {

    private Integer id;
    private String name;
    private Piece piece;

    public Player(){
    }
    public Player(Integer id,String name){
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Piece getPiece() {
        return piece;
    }
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
