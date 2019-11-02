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
public class VariavelHandler extends AbstractHandler {

    public VariavelHandler(ArrayList<TokenModel> tokens) {
        super(tokens);
    }

    @Override
    public boolean handle() {
        if (nextToken()) {
            if (currentToken == Token.IDENTIFICADOR) {
                removeToken();
                return true;
            } else {
                setCodError(6);
                return false;
            }
        } else {
            setCodError(5);
            return false;
        }
    }
    
}
