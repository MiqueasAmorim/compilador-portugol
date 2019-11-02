/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisador_sintatico.handlers;

import java.util.ArrayList;
import model.ErrorModel;
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
//        if (nextToken()) {
            if (new VariavelHandler(tokens).handle()) {
//                if (nextToken()) {
                    if (new ConjuntoIdsHandler(tokens).handle()) {
                    //new ConjuntoIdsHandler(tokens).handle();
                        if (nextToken()) {
                            if (currentToken == Token.DOIS_PONTOS) {
                                removeToken();
                                if (new TipoHandler(tokens).handle()) {
                                    if (nextToken()) {
                                        if (currentToken == Token.PONTO_VIRGULA) {
                                            removeToken();
                                            return true;
                                        } else {
                                            setCodError(10); // Esperado ";", mas outra coisa encontrada.
                                            return false;
                                        }
                                    } else {
                                        setCodError(9);
                                        // Esperado ";", mas encontrou "fim de arquivo"
                                        return false;
                                    }
                                } else {
                                    return false;
                                }
                            } else {
                                setCodError(linha); // Esperado ":", mas encontrado outra coisa
                                return false;
                            }
                        } else {
                            setCodError(7);  // Esperado dois pontos, mas encontrou nenhum token
                            return false;
                        }
                    } else {
                        return false;
                    }
//                } else {
//                    return false;
//                }
            } else {
                return false;
            }
//        } else {   
//            return false;
//        }

    }

}
