/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisador_sintatico.handlers;

import model.TokenModel;
import model.Token;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import model.ErrorModel;

/**
 *
 * @author Miqueas
 */
public abstract class AbstractHandler implements IHandler{
    
    protected static ArrayList<TokenModel> tokens;
    protected ArrayList<Token> terminais;
    protected static Token currentToken;
    protected DefaultMutableTreeNode noPai;
    //protected static int errorCode = 0;
    protected static int linha = 1;

    public AbstractHandler(ArrayList<TokenModel> tokens, DefaultMutableTreeNode noPai) {
        this.terminais = new ArrayList();
        this.tokens = tokens;
        this.noPai = noPai;
    }
    
    public boolean nextToken(){
        if(tokens.size() > 0){
            this.currentToken = tokens.get(0).getNome();
            return true;
        }else{
            currentToken = null;
            return false;
        }
    }
    
    public String getCurrentLexema(){
        String lexema = "";
        if(tokens.size() > 0){
            lexema = tokens.get(0).getLexema();
        }
        return lexema;
    }
    
    public int getCurrentLine(){
        if(tokens.size() > 0){
            return tokens.get(0).getLinha();
        }else{
            return linha;
        }
    }
    
    public boolean removeToken(){
        if(tokens.size() > 0 ){
            linha = tokens.get(0).getLinha();
            tokens.remove(0);
            return true;
        }else{
            return false;
        }      
    }
    
    public int getNumTokens(){
        return tokens.size();
    }
    
    public void setCodError(int cod) {
        ErrorModel.getInstance().setCodigo(cod);
        ErrorModel.getInstance().setLexema(getCurrentLexema());
        ErrorModel.getInstance().setLinha(getCurrentLine());
    }

//    public int getErrorCode() {
//        return errorCode;
//    }
   
    
}
