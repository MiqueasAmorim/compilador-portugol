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
public class Termo2Handler extends AbstractHandler {

    public Termo2Handler(ArrayList<TokenModel> tokens) {
        super(tokens);
    }

    @Override
    public boolean handle() {
        nextToken();
        if (currentToken == Token.OP_MULTIPLICACAO || currentToken == Token.OP_DIVISAO || currentToken == Token.PC_RESTO || currentToken == Token.PC_QUOCIENTE) {
            removeToken();
            return (new UnarioHandler(tokens).handle() && new Termo2Handler(tokens).handle());
        }
        return true;
    }
    
}
