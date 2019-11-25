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
            if (currentToken == null) {
                setCodError(27); // Esperado ':=' ou '(' ou '[' mas encontrou "fim de arquivo"
                return false;
            }
            if (!(currentToken == Token.OP_ATRIBUICAO || currentToken == Token.ABRE_COLCHETES || currentToken == Token.ABRE_PARENTESES)) {
                setCodError(28); // Esperado ':=' ou '(' ou '[' mas encontrou outra coisa
                return false;
            }

            if (currentToken == Token.OP_ATRIBUICAO) {
                removeToken();
                if (new ExprHandler(tokens).handle()) {
                    if (!nextToken()) {
                        setCodError(9); // Esperado ";", mas encontrou "fim de arquivo"
                        return false;
                    }
                    if (currentToken == Token.PONTO_VIRGULA) {
                        removeToken();
                        return true;
                    } else {
                        setCodError(10); // Esperado ";", mas outra coisa encontrada
                        return false;
                    }
                }
            }

            if (currentToken == Token.ABRE_COLCHETES) {
                removeToken();
                if (new ExprHandler(tokens).handle()) {
                    if (!nextToken()) {
                        setCodError(29); // Esperado "]", mas encontrou "fim de arquivo"
                        return false;
                    }
                    if (currentToken == Token.FECHA_COLCHETES) {
                        removeToken();
                        if (!nextToken()) {
                            setCodError(31); // Esperado ":=", mas encontrou "fim de arquivo"
                            return false;
                        }
                        if (currentToken == Token.OP_ATRIBUICAO) {
                            removeToken();
                            if (new ExprHandler(tokens).handle()) {
                                if (!nextToken()) {
                                    setCodError(9); // Esperado ";", mas encontrou "fim de arquivo"
                                    return false;
                                }
                                if (currentToken == Token.PONTO_VIRGULA) {
                                    removeToken();
                                    return true;
                                } else {
                                    setCodError(10); // Esperado ";", mas outra coisa encontrada
                                    return false;
                                }
                            }
                        } else {
                            setCodError(32); // Esperado ":=", mas outra coisa encontrada
                            return false;
                        }
                    } else {
                        setCodError(30); // Esperado "]", mas encontrou outra coisa
                        return false;
                    }
                }

            }
            if (currentToken == Token.ABRE_PARENTESES) {
                removeToken();
                if (new Parametros2Handler(tokens).handle()) {
                    if (!nextToken()) {
                        setCodError(33); // Esperado ")", mas encontrou "fim de arquivo"
                        return false;
                    }
                    if (currentToken == Token.FECHA_PARENTESES) {
                        removeToken();
                        if (!nextToken()) {
                            setCodError(9); // Esperado ";", mas encontrou "fim de arquivo"
                            return false;
                        }
                        if (currentToken == Token.PONTO_VIRGULA) {
                            removeToken();
                            return true;
                        } else {
                            setCodError(10); // Esperado ";", mas outro token encontrado
                            return false;
                        }
                    } else {
                        setCodError(34);
                        return false;
                    }
                }
            }
        }

        if (currentToken == Token.PC_SE) {
            removeToken();
            if (new ExprHandler(tokens).handle()) {
                if (!nextToken()) {
                    setCodError(35); // Esperado "ENTAO", mas "fim de arquivo" encontrado
                    return false;
                }
                if (currentToken == Token.PC_ENTAO) {
                    removeToken();
                    if (new InstHandler(tokens).handle()) {
                        nextToken();
                        if (currentToken == Token.PC_SENAO) {
                            return new ContSeHandler(tokens).handle();
                        }
                        return true;
                    }
                } else {
                    setCodError(36); // Esperado "ENTAO", mas outro token encontrado
                    return false;
                }
            }
        }
        if (currentToken == Token.PC_ENQUANTO) {
            removeToken();
            if (new ExprHandler(tokens).handle()) {
                if (!nextToken()) {
                    setCodError(37); // Esperado "FACA", mas "fim de arquivo" encontrado
                    return false;
                }
                if (currentToken == Token.PC_FACA) {
                    removeToken();
                    if (new InstHandler(tokens).handle()) {
                        return true;
                    }
                } else {
                    setCodError(38); // Esperado "FACA", mas outro token encontrado
                    return false;
                }
            }
        }

        if (currentToken == Token.PC_REPITA) {
            removeToken();
            if (new InstHandler(tokens).handle()) {
                if (!nextToken()) {
                    setCodError(39); // Esperado "ATE", mas "fim de arquivo" encontrado
                    return false;
                }
                if (currentToken == Token.PC_ATE) {
                    removeToken();
                    if (new ExprHandler(tokens).handle()) {
                        if (!nextToken()) {
                            setCodError(9); // Esperado ";", mas "fim de arquivo" encontrado
                            return false;
                        }
                        if (currentToken == Token.PONTO_VIRGULA) {
                            removeToken();
                            return true;
                        } else {
                            setCodError(10); // Esperado ";", mas outro token encontrado
                            return false;
                        }
                    }
                } else {
                    setCodError(40); // Esperado "ATE", mas outro token encontrado
                    return false;
                }
            }
        }

        if (currentToken == Token.PC_PARA) {
            removeToken();
            if (new VariavelHandler(tokens).handle()) {
                if (!nextToken()) {
                    setCodError(31); // Esperado ":=", mas "fim de arquivo" encontrado
                    return false;
                }
                if (currentToken == Token.OP_ATRIBUICAO) {
                    removeToken();
                    nextToken();
                    if (currentToken == Token.INTEIRO || currentToken == Token.IDENTIFICADOR) {
                        removeToken();
                        if (!nextToken()) {
                            setCodError(39); // Esperado "ATE", mas "fim de arquivo" encontrado
                            return false;
                        }
                        if (currentToken == Token.PC_ATE) {
                            removeToken();
                            nextToken();
                            if (currentToken == Token.INTEIRO || currentToken == Token.IDENTIFICADOR) {
                                removeToken();
                                if (!nextToken()) {
                                    setCodError(37); // Esperado "FACA", mas "fim de arquivo" encontrado
                                    return false;
                                }
                                if (currentToken == Token.PC_FACA) {
                                    removeToken();
                                    return new InstHandler(tokens).handle();
                                } else {
                                    setCodError(38); // Esperado "FACA", mas outro token encontrado
                                    return false;
                                }
                            }
                        } else {
                            setCodError(40); // Esperado "ATE", mas outro token encontrado
                            return false;
                        }
                    }
                } else {
                    setCodError(32); // Esperado ":=", mas outro token encontrado
                    return false;
                }
            }
        }

        if (currentToken == Token.PC_PARE) {
            removeToken();
            if (!nextToken()) {
                setCodError(9); // Esperado ";", mas "fim de arquivo" encontrado
                return false;
            }
            if (currentToken == Token.PONTO_VIRGULA) {
                removeToken();
                return true;
            } else {
                setCodError(10); // Esperado ";", mas outro token encontrado
                return false;
            }
        }

        if (currentToken == Token.PC_CONTINUA) {
            removeToken();
            if (!nextToken()) {
                setCodError(9); // Esperado ";", mas "fim de arquivo" encontrado
                return false;
            }
            if (currentToken == Token.PONTO_VIRGULA) {
                removeToken();
                return true;
            } else {
                setCodError(10); // Esperado ";", mas outro token encontrado
                return false;
            }
        }

        if (currentToken == Token.PC_LEIA) {
            removeToken();
            if (!nextToken()) {
                setCodError(102); // Esperado "(", mas "fim de arquivo" encontrado
                return false;
            }
            if (currentToken == Token.ABRE_PARENTESES) {
                removeToken();
                if (new VariavelHandler(tokens).handle() && new ConjuntoIdsHandler(tokens).handle()) {
                    if (!nextToken()) {
                        setCodError(33); // Esperado ")", mas "fim de arquivo" encontrado
                        return false;
                    }
                    if (currentToken == Token.FECHA_PARENTESES) {
                        removeToken();
                        if (!nextToken()) {
                            setCodError(9); // Esperado ";", mas "fim de arquivo" encontrado
                            return false;
                        }
                        if (currentToken == Token.PONTO_VIRGULA) {
                            removeToken();
                            return true;
                        } else {
                            setCodError(10); // Esperado ";", mas outro token encontrado
                            return false;
                        }
                    } else {
                        setCodError(34); // Esperado ")", mas outro token encontrado
                        return false;
                    }
                }
            } else {
                setCodError(103);// Esperado ")", mas outro token encontrado
                return false;
            }
        }

        if (currentToken == Token.PC_ESCREVA) {
            removeToken();
            if (!nextToken()) {
                setCodError(102); // Esperado "(", mas "fim de arquivo" encontrado
                return false;
            }
            if (currentToken == Token.ABRE_PARENTESES) {
                removeToken();
                if (new ConteudoHandler(tokens).handle() && new MaisConteudoHandler(tokens).handle()) {
                    if (!nextToken()) {
                        setCodError(33); // Esperado ")", mas "fim de arquivo" encontrado
                        return false;
                    }
                    if (currentToken == Token.FECHA_PARENTESES) {
                        removeToken();
                        if (!nextToken()) {
                            setCodError(9); // Esperado ";", mas "fim de arquivo" encontrado
                            return false;
                        }
                        if (currentToken == Token.PONTO_VIRGULA) {
                            removeToken();
                            return true;
                        } else {
                            setCodError(10); // Esperado ";", mas outro token encontrado
                            return false;
                        }
                    } else {
                        setCodError(34); // Esperado ")", mas outro token encontrado
                        return false;
                    }
                }
            } else {
                setCodError(103);// Esperado ")", mas outro token encontrado
                return false;
            }
        }

        if (currentToken == Token.PC_INICIO) {
            return new BlocoHandler(tokens).handle();
        }

        //setCodError(16);
        return false;
    }

}
