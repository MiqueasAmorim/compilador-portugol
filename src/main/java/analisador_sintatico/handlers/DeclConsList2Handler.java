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
public class DeclConsList2Handler extends AbstractHandler {

    public DeclConsList2Handler(ArrayList<TokenModel> tokens) {
        super(tokens);
    }

    @Override
    public boolean handle() {
        if (nextToken()) {
            if (currentToken == Token.DOIS_PONTOS) {
                removeToken();
                if (nextToken()) {
                    if (new TipoHandler(tokens).handle()) {
                        if (nextToken()) {
                            if (currentToken == Token.OP_IGUAL) {
                                removeToken();
                                if (new ValorHandler(tokens).handle()) {
                                    if (nextToken()) {
                                        if (currentToken == Token.PONTO_VIRGULA) {
                                            removeToken();
                                            if (new DeclConsList1Handler(tokens).handle()) {
                                                return true;
                                            } else {
                                                return false;
                                            }
                                        } else {
                                            return false;
                                        }
                                    } else {
                                        return false;
                                    }
                                } else {
                                    return false;
                                }
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }

            if (currentToken == Token.OP_IGUAL) {
                removeToken();
                if (new ValorHandler(tokens).handle()) {
                    if (nextToken()) {
                        if (currentToken == Token.PONTO_VIRGULA) {
                            removeToken();
                            if (new DeclConsList1Handler(tokens).handle()) {
                                return true;
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
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
