/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisador_sintatico.handlers;

import java.util.ArrayList;
import model.ErrorModel;
import model.Token;
import model.TokenModel;

/**
 *
 * @author Miqueas
 */
public class DeclaracaoVariavelHandler extends AbstractHandler {

    public DeclaracaoVariavelHandler(ArrayList<TokenModel> tokens) {
        super(tokens);
    }

    @Override
    public boolean handle() {
        if (nextToken()){
            if (currentToken == Token.PC_VARIAVEL) {
                removeToken();
                return new DeclVarListHandler(tokens).handle();        
            } else {
                setCodError(4);
                return false;
            }
        } else {
            setCodError(3);
            return false;
        }
 
    }
    
}
