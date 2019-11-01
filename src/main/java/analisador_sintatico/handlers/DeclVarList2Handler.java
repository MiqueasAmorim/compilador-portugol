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
 * @author Miqueas
 */
public class DeclVarList2Handler extends AbstractHandler {

    public DeclVarList2Handler(ArrayList<TokenModel> tokens) {
        super(tokens);
    }

    @Override
    public boolean handle() {
        if (nextToken()) {
            if (currentToken == Token.PC_CONSTANTE || currentToken == Token.PC_INICIO) {
                return true;
            }
            return (new DeclVarHandler(tokens).handle() && new DeclVarList2Handler(tokens).handle());
        } else {
            return false;
        }
    }
    
}
