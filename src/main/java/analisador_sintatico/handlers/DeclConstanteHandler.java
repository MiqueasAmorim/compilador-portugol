/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisador_sintatico.handlers;

import analisador_sintatico.handlers.AbstractHandler;
import static analisador_sintatico.handlers.AbstractHandler.currentToken;
import java.util.ArrayList;
import model.Token;
import model.TokenModel;

/**
 *
 * @author andrei
 */
public class DeclConstanteHandler extends AbstractHandler {

    public DeclConstanteHandler(ArrayList<TokenModel> tokens) {
        super(tokens);
    }

    @Override
    public boolean handle() {
     if (nextToken()) {
            if (currentToken == Token.PC_CONSTANTE) {
                removeToken();
                return new DeclConstListHandler(tokens).handle();
            } else {
                setCodError(201);
                return false;
            }
        } else {
            setCodError(202);
            return false;
        }
    }
    
}
