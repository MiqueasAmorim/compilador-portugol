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
public class InstHandler extends AbstractHandler {

    public InstHandler(ArrayList<TokenModel> tokens) {
        super(tokens);
    }

    @Override
    public boolean handle() {
        nextToken();
        if (currentToken == null) {
            setCodError(17);
            return false;
        }
        if (new VariavelHandler(tokens).handle()) {
            nextToken();
            if (currentToken == Token.OP_ATRIBUICAO) {
                removeToken();
                if (new ExprHandler(tokens).handle()) {
                    nextToken();
                    if (currentToken == Token.PONTO_VIRGULA) {
                        removeToken();
                        return true;
                    }
                }
            }

            if (currentToken == Token.ABRE_COLCHETES) {
                removeToken();
                if (new ExprHandler(tokens).handle()) {
                    nextToken();
                    if (currentToken == Token.FECHA_COLCHETES) {
                        removeToken();
                        nextToken();
                        if (currentToken == Token.OP_ATRIBUICAO) {
                            removeToken();
                            if (new ExprHandler(tokens).handle()) {
                                nextToken();
                                if (currentToken == Token.PONTO_VIRGULA) {
                                    removeToken();
                                    return true;
                                }
                            }
                        }
                    }
                }

            }
            if (currentToken == Token.ABRE_PARENTESES) {
                removeToken();
                if (new Parametros2Handler(tokens).handle()) {
                    nextToken();
                    if (currentToken == Token.FECHA_PARENTESES) {
                        removeToken();
                        nextToken();
                        if (currentToken == Token.PONTO_VIRGULA) {
                            removeToken();
                            return true;
                        }
                    }
                }
            }
        }

        if (currentToken == Token.PC_SE) {
            removeToken();
            if (new ExprHandler(tokens).handle()) {
                nextToken();
                if (currentToken == Token.PC_ENTAO) {
                    removeToken();
                    if (new InstHandler(tokens).handle()) {
                        if (new ContSeHandler(tokens).handle()) {
                            return true;
                        }
                    }
                }
            } 
        }
        if (currentToken == Token.PC_ENQUANTO) {
            removeToken();
            if (new ExprHandler(tokens).handle()) {
                nextToken();
                if (currentToken == Token.PC_FACA) {
                    removeToken();
                    if (new InstHandler(tokens).handle()) {
                        return true;
                    }
                }
            }
        }

        if (currentToken == Token.PC_REPITA) {
            removeToken();
            if (new InstHandler(tokens).handle()) {
                nextToken();
                if (currentToken == Token.PC_ATE) {
                    removeToken();
                    if (new ExprHandler(tokens).handle()) {
                        nextToken();
                        if (currentToken == Token.PONTO_VIRGULA) {
                            removeToken();
                            return true;
                        }
                    }
                }
            }
        }
        
        if (currentToken == Token.PC_PARA) {
            removeToken();
            if (new VariavelHandler(tokens).handle()) {
                nextToken();
                if (currentToken == Token.OP_ATRIBUICAO) {
                    removeToken();
                    nextToken();
                    if (currentToken == Token.INTEIRO || currentToken == Token.IDENTIFICADOR) {
                        removeToken();
                        nextToken();
                        if (currentToken == Token.PC_ATE) {
                            removeToken();
                            nextToken();
                            if (currentToken == Token.INTEIRO || currentToken == Token.IDENTIFICADOR) {
                                removeToken();
                                nextToken();
                                if (currentToken == Token.PC_FACA) {
                                    removeToken();
                                    return new InstHandler(tokens).handle();
                                }
                            }
                        }
                    }
                }
            }
        }

        if (currentToken == Token.PC_PARE) {
            removeToken();
            nextToken();
            if (currentToken == Token.PONTO_VIRGULA) {
                removeToken();
                return true;
            }
        }

        if (currentToken == Token.PC_CONTINUA) {
            removeToken();
            nextToken();
            if (currentToken == Token.PONTO_VIRGULA) {
                removeToken();
                return true;
            }
        }

        if (currentToken == Token.PC_LEIA) {
            removeToken();
            nextToken();
            if (currentToken == Token.ABRE_PARENTESES) {
                removeToken();
                if (new VariavelHandler(tokens).handle() && new ConjuntoIdsHandler(tokens).handle()) {
                    nextToken();
                    if (currentToken == Token.FECHA_PARENTESES) {
                        removeToken();
                        nextToken();
                        if (currentToken == Token.PONTO_VIRGULA) {
                            removeToken();
                            return true;
                        }
                    }
                }
            }
        }
        
        if (currentToken == Token.PC_ESCREVA) {
            removeToken();
            nextToken();
            if (currentToken == Token.ABRE_PARENTESES) {
                removeToken();
                if (new ConteudoHandler(tokens).handle() && new MaisConteudoHandler(tokens).handle()) {
                    nextToken();
                    if (currentToken == Token.FECHA_PARENTESES) {
                        removeToken();
                        nextToken();
                        if (currentToken == Token.PONTO_VIRGULA) {
                            removeToken();
                            return true;
                        }
                    }
                }
            }
        }
        
        if (currentToken == Token.PC_INICIO) {
            return new BlocoHandler(tokens).handle();
        }
        
        setCodError(16);
        return false;
    }

}
