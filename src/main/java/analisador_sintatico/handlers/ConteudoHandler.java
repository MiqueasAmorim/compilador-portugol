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
public class ConteudoHandler extends AbstractHandler {

    public ConteudoHandler(ArrayList<TokenModel> tokens) {
        super(tokens);
    }

    @Override
    public boolean handle() {
        nextToken();
        if (new VariavelHandler(tokens).handle()) {
            return true;
        }
        if (currentToken == Token.STRING || currentToken == Token.CARACTERE) {
            removeToken();
            return true;
        }
        return false;
    }
    
}
