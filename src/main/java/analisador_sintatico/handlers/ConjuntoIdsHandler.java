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
public class ConjuntoIdsHandler extends AbstractHandler {

    public ConjuntoIdsHandler(ArrayList<TokenModel> tokens) {
        super(tokens);
    }

    @Override
    public boolean handle() {
        if (nextToken()) {
            if (currentToken == Token.VIRGULA) {
                removeToken();
                return (new VariavelHandler(tokens).handle() && new ConjuntoIdsHandler(tokens).handle());
            }
        } else {
            return false;
        }
        return true;
    }
    
}