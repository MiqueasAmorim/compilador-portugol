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
public class FatorHandler extends AbstractHandler {

    public FatorHandler(ArrayList<TokenModel> tokens) {
        super(tokens);
    }

    @Override
    public boolean handle() {
        nextToken();
        if (currentToken == Token.ABRE_PARENTESES) {
            removeToken();
            if (new ExprHandler(tokens).handle()) {
                if (currentToken == Token.FECHA_PARENTESES) {
                    removeToken();
                    return true;
                }
            }
        }
        if (new VariavelHandler(tokens).handle()) {
            return true;
        }
        if (currentToken == null) {
            setCodError(15); // Esperado um identificador, número ou string, mas fim de arquivo encontrado
            return false;
        }
        if (currentToken == Token.INTEIRO || currentToken == Token.REAL || currentToken == Token.CARACTERE || currentToken == Token.STRING) {
            removeToken();
            return true;
        } else {
            setCodError(14); // Esperado um identificador, número ou string, mas outra coisa encontrada
            return false;
        }

    }

}
