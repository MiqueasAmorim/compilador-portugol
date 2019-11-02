/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisador_sintatico;

import analisador_sintatico.handlers.DeclaracaoVariavelHandler;
import model.ErrorModel;
import model.TokenModel;
import java.util.ArrayList;

/**
 *
 * @author Miqueas
 */
public class AnalisadorSintatico{
    
    private ArrayList<TokenModel> tokens;
    private DeclaracaoVariavelHandler handler;
    private ArrayList<ErrorModel> errorList;
    
    public AnalisadorSintatico(ArrayList<TokenModel> tokens) {
        this.tokens = tokens;
        this.handler = new DeclaracaoVariavelHandler(tokens);
        this.errorList = new ArrayList();      
    }
    
    public boolean run(){
        ErrorModel.getInstance().limpar();
        return handler.handle();
        //Iterator<TokenModel> it = tokens.iterator();
        //while(it.hasNext()){
            //if(!handler.handle()){
                
                //int errorCode = handler.getErrorCode();
                //String lexema = handler.getCurrentLexema();
                //int linha = handler.getCurrentLine();
                //ErrorModel error = new ErrorModel(errorCode, lexema, linha);
                //errorList.add(error);
                //break;
                /*
                recuperaErro();
                this.handler = new Single_input(tokens);
                */
            //}
        //}
        
        //return errorList;
    }
    
}
