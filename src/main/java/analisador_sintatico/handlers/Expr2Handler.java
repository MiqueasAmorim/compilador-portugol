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
public class Expr2Handler extends AbstractHandler {

    public Expr2Handler(ArrayList<TokenModel> tokens) {
        super(tokens);
    }

    @Override
    public boolean handle() {
        nextToken();
        if (currentToken == Token.OP_OU || currentToken == Token.OP_E) {
            removeToken();
            return (new ExprComparacaoHandler(tokens).handle() && new Expr2Handler(tokens).handle());
        }
        return true;
    }
}
