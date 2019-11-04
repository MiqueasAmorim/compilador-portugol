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
public class ContSeHandler extends AbstractHandler {

    public ContSeHandler(ArrayList<TokenModel> tokens) {
        super(tokens);
    }

    @Override
    public boolean handle() {
        nextToken();
        if (currentToken == Token.PC_SENAO) {
            removeToken();
            if (new InstHandler(tokens).handle()) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }
    
}
