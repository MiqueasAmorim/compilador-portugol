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
public class ParametrosHandler extends AbstractHandler {

    public ParametrosHandler(ArrayList<TokenModel> tokens) {
        super(tokens);
    }

    @Override
    public boolean handle() {
        nextToken();

        if (currentToken == Token.FECHA_PARENTESES) {
            return true;

        } else if (new DeclVar2Handler(tokens).handle()) {
            nextToken();
            if (currentToken == Token.FECHA_PARENTESES) {
                return (new ParametrosHandler(tokens).handle());
            } else if (currentToken == Token.PONTO_VIRGULA) {
                removeToken();
                nextToken();
                if (currentToken != Token.FECHA_PARENTESES) {
                    return (new ParametrosHandler(tokens).handle());
                } else {
                    return false;
                }
            } else {
                setCodError(10);
                return false;
            }

        } else {
            return false;
        }

    }
}
