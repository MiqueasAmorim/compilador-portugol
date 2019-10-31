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
public class DeclVarHandler extends AbstractHandler {

    public DeclVarHandler(ArrayList<TokenModel> tokens) {
        super(tokens);
    }

    @Override
    public boolean handle() {
        if (nextToken()) {
            if (new VariavelHandler(tokens).handle() && new ConjuntoIdsHandler(tokens).handle()) {
                removeToken();
                if (nextToken()) {
                    if (currentToken == Token.DOIS_PONTOS) {
                        removeToken();
                        if (new TipoHandler(tokens).handle()) {
                            removeToken();
                            if (currentToken == Token.PONTO_VIRGULA) {
                                return true;
                            }
                        }
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }
    
}
