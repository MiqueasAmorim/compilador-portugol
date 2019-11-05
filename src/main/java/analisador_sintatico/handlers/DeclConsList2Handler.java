/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisador_sintatico.handlers;

import analisador_sintatico.handlers.AbstractHandler;
import analisador_sintatico.handlers.TipoHandler;
import analisador_sintatico.handlers.ValorHandler;
import static analisador_sintatico.handlers.AbstractHandler.currentToken;
import java.util.ArrayList;
import model.Token;
import model.TokenModel;

/**
 *
 * @author andrei
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
                    if (currentToken == Token.PC_CHAR) {
                        removeToken();
                        if (nextToken()) {
                            if (currentToken == Token.OP_IGUAL) {
                                removeToken();
                                if (nextToken()) {
                                    if (currentToken == Token.CARACTERE) {
                                        removeToken();
                                        if (nextToken()) {
                                            if (currentToken == Token.PONTO_VIRGULA) {
                                                removeToken();
                                                if (new DeclConstList1Handler(tokens).handle()) {
                                                    return true;
                                                }
                                            } else {
                                                setCodError(10);
                                                return false;
                                            }
                                        } else {
                                            setCodError(9);
                                            return false;
                                        }
                                    } else {
                                        setCodError(210);
                                        return false;
                                    }
                                }else{
                                    setCodError(209);
                                    return false;
                                }
                            }else{
                                setCodError(205);
                                return false;
                            }
                        }else{
                            setCodError(206);
                            return false;
                        }

                                } else {
                                    if (new TipoHandler(tokens).handle()) {
                                        if (nextToken()) {
                                            if (currentToken == Token.OP_IGUAL) {
                                                removeToken();
                                                if (new ValorHandler(tokens).handle()) {
                                                    if (nextToken()) {
                                                        if (currentToken == Token.PONTO_VIRGULA) {
                                                            removeToken();
                                                            if (new DeclConstList1Handler(tokens).handle()) {

                                                            }
                                                        } else {
                                                            setCodError(10);
                                                            return false;
                                                        }

                                                    } else {
                                                        setCodError(9);
                                                        return false;
                                                    }
                                                } else {
                                                    setCodError(204);
                                                    return false;

                                                }
                                            } else {
                                                setCodError(205);
                                                return false;
                                            }

                                        } else {
                                            setCodError(206);
                                            return false;
                                        }
                                    } else {
                                        setCodError(2);
                                        return false;
                                    }

                                }

                            } else {
                                setCodError(1);
                                return false;
                            }

                        } else {
                            if (currentToken == Token.OP_IGUAL) {
                                removeToken();
                                if (new ValorHandler(tokens).handle()) {
                                    if (nextToken()) {
                                        if (currentToken == Token.PONTO_VIRGULA) {
                                            removeToken();
                                            if (new DeclConstList1Handler(tokens).handle()) {
                                                return true;
                                            } else {
                                                setCodError(202);
                                                return false;
                                            }
                                        } else {
                                            setCodError(10);
                                            return false;
                                        }

                                    } else {
                                        setCodError(9);
                                        return false;
                                    }
                                } else {
                                    setCodError(204);
                                    return false;
                                }
                            } else {
                                setCodError(205);
                                return false;
                            }

                        }
                    } else {
                        setCodError(7);
                        return false;
                    }
                   return true;
                }
                // return true;
            }
