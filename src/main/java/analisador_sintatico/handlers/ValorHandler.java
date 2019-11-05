/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisador_sintatico.handlers;

import analisador_sintatico.handlers.AbstractHandler;
import analisador_sintatico.handlers.UnarioHandler;
import java.util.ArrayList;
import model.Token;
import model.TokenModel;

/**
 *
 * @author andrei
 */
public class ValorHandler extends AbstractHandler {

    public ValorHandler(ArrayList<TokenModel> tokens) {
        super(tokens);
    }

    @Override
    public boolean handle() {
        if (new UnarioHandler(tokens).handle() || currentToken == Token.STRING) {
            return true;
        }else{
            setCodError(204);
            return false;
        }
    }

}
