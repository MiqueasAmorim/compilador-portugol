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
public class ProgramaHandler extends AbstractHandler {

    public ProgramaHandler(ArrayList<TokenModel> tokens) {
        super(tokens);
    }

    @Override
    public boolean handle() {
        if (!nextToken()) {
            setCodError(23);
            return false;
        }
        if (!(currentToken == Token.PC_PROGRAMA)) {
            setCodError(24);
            return false;
        }
        removeToken();
        if (!(new VariavelHandler(tokens).handle())) {
            return false;
        }
        if (!nextToken()) {
            setCodError(9);
            return false;
        }
        if (!(currentToken == Token.PONTO_VIRGULA)) {
            setCodError(10);
            return false;
        }
        removeToken();
        if (!(new DeclaracoesHandler(tokens).handle())) {
            return false;
        }
        if (!nextToken()) {
            setCodError(18);
            return false;
        }
        if (!(currentToken == Token.PC_INICIO)) {
            setCodError(19);
            return false;
        }
        removeToken();
        if (!(new InstrucoesHandler(tokens).handle())) {
            return false;
        }
        if (!nextToken()) {
            setCodError(20);
            return false;
        }
        if (!(currentToken == Token.PC_FIM)) {
            setCodError(21);
            return false;
        }
        removeToken();
        if (!nextToken()) {
            setCodError(25);
            return false;
        }
        if (!(currentToken == Token.PONTO)) {
            setCodError(26);
            return false;
        }
        removeToken();
        if (nextToken()) {
            setCodError(41);
            return false;
        }
        return true;
    }
    
}
