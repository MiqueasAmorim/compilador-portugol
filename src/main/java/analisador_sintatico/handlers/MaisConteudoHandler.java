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
public class MaisConteudoHandler extends AbstractHandler {

    public MaisConteudoHandler(ArrayList<TokenModel> tokens) {
        super(tokens);
    }

    @Override
    public boolean handle() {
        nextToken();
        if (currentToken == Token.VIRGULA) {
            removeToken();
            if (new ConteudoHandler(tokens).handle() && new MaisConteudoHandler(tokens).handle()) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }
    
}
