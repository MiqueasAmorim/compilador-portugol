/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisador_sintatico.handlers;

import static analisador_sintatico.handlers.AbstractHandler.tokens;
import java.util.ArrayList;
import model.Token;
import model.TokenModel;

/**
 *
 * @author Miqueas
 */
public class DeclaracoesHandler extends AbstractHandler {

    public DeclaracoesHandler(ArrayList<TokenModel> tokens) {
        super(tokens);
    }

    @Override
    public boolean handle() {
        nextToken();
        if (currentToken == Token.PC_CONSTANTE) {
            if (new DeclConstanteHandler(tokens).handle()) {
                nextToken();
                if (currentToken == Token.PC_VARIAVEL) {
                    return new DeclaracaoVariavelHandler(tokens).handle();
                }
                return true;
            } else {
                return false;
            }
        }
        if (currentToken == Token.PC_VARIAVEL) {
            return new DeclaracaoVariavelHandler(tokens).handle();
        }
        return true;
    }
    
}
