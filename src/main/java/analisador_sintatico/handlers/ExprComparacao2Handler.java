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
public class ExprComparacao2Handler extends AbstractHandler {

    public ExprComparacao2Handler(ArrayList<TokenModel> tokens) {
        super(tokens);
    }

    @Override
    public boolean handle() {
        nextToken();
        if (currentToken == Token.OP_IGUAL || currentToken == Token.OP_DIFERENTE || currentToken == Token.OP_MENOR ||
        currentToken == Token.OP_MENOR_IGUAL || currentToken == Token.OP_MAIOR || currentToken == Token.OP_MAIOR_IGUAL) {
            removeToken();
            return (new ExprOpHandler(tokens).handle() && new ExprComparacao2Handler(tokens).handle());
        }
        return true;
    }
    
}
