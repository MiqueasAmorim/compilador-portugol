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
 * @author Jefferson
 */
public class DeclVar2Handler extends AbstractHandler {

    public DeclVar2Handler(ArrayList<TokenModel> tokens) {
        super(tokens);
    }

    @Override
    public boolean handle() {
        if (new VariavelHandler(tokens).handle()) {
            if (new ConjuntoIdsHandler(tokens).handle()) {
                if (nextToken()) {
                    if (currentToken == Token.DOIS_PONTOS) {
                        removeToken();
                        if (new TipoHandler(tokens).handle()) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        setCodError(1); // Esperado ":", mas encontrado outra coisa
                        return false;
                    }
                } else {
                    setCodError(7);  // Esperado dois pontos, mas encontrou nenhum token
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
