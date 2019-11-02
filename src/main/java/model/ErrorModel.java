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
public class ErrorModel {
    private static ErrorModel instance;
    private int codigo = 0;
    private String lexema = null;
    private int linha = 0;

    private ErrorModel() {}
    
    public static ErrorModel getInstance() {
        if (instance == null) {
            instance = new ErrorModel();
        }
        return instance;
    }
    
    public void limpar() {
        codigo = 0;
        lexema = null;
        linha = 0;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getLexema() {
        return lexema;
    }

    public int getLinha() {
        return linha;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }
    
}
