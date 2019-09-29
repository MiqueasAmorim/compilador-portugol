/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


/**
 *
 * @author Miqueas
 */
public class TokenModel {

    private final int ID;
    private final Token nome;
    private final String lexema;
    private final int linha;
    private final int coluna;

    public TokenModel(int ID, Token nome, String lexema, int linha, int coluna) {
        this.ID = ID;
        this.nome = nome;
        this.lexema = lexema;
        this.linha = linha;
        this.coluna = coluna;
    }

    public int getID() {
        return ID;
    }

    public Token getNome() {
        return nome;
    }

    public String getLexema() {
        return lexema;
    } 

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }
    
}
