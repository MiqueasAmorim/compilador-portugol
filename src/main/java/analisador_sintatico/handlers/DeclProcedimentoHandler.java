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
 * @author Jefferson
 */
public class DeclProcedimentoHandler extends AbstractHandler {

    public DeclProcedimentoHandler(ArrayList<TokenModel> tokens) {
        super(tokens);
    }

    @Override
    public boolean handle() {
        nextToken();

        if (currentToken == Token.PC_INICIO) {
            return true;
        }

        if (new DeclProcHandler(tokens).handle()) {
            return (new DeclProcedimentoHandler(tokens).handle());
        } else {
            return false;
        }
    }

}
