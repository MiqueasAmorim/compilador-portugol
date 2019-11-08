/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisador_sintatico.handlers;

import java.util.ArrayList;
import model.Token;
import model.TokenModel;

/**
 *
 * @author Jefferson
 */
public class DeclaracaoConstanteHandler extends AbstractHandler{

    public DeclaracaoConstanteHandler(ArrayList<TokenModel> tokens) {
        super(tokens);
    }

    @Override
    public boolean handle() {
        if(nextToken()){
            if(currentToken == Token.PC_CONSTANTE){
                removeToken();
                return new DeclConsListHandler(tokens).handle();
            }else{
                System.out.println("Esperando token CONSTANTE!");
                return false;
            }
        }else{
            System.out.println("Esperando token CONSTANTE MAIS FDA ENCONTRADO!");
            return false;
        }
    }
}
