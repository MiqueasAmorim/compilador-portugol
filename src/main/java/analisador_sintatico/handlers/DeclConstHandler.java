/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisador_sintatico.handlers;

import analisador_sintatico.handlers.AbstractHandler;
import analisador_sintatico.handlers.ConjuntoIdsHandler;
import static analisador_sintatico.handlers.AbstractHandler.currentToken;
import java.util.ArrayList;
import model.Token;
import model.TokenModel;

/**
 *
 * @author andrei
 */
public class DeclConstHandler extends AbstractHandler {

    public DeclConstHandler(ArrayList<TokenModel> tokens) {
        super(tokens);
    }

    @Override
    public boolean handle() {
        if (nextToken()) {
            if (currentToken == Token.IDENTIFICADOR) {
                removeToken();
                return new ConjuntoIdsHandler(tokens).handle();
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
