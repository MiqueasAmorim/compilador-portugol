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
public class DeclProcHandler extends AbstractHandler {

    public DeclProcHandler(ArrayList<TokenModel> tokens) {
        super(tokens);
    }

    @Override
    public boolean handle() {
        if (nextToken()) {
            if (currentToken == Token.PC_PROCEDIMENTO) {
                removeToken();
                if (nextToken()) {
                    if (currentToken == Token.IDENTIFICADOR) {
                        removeToken();
                        if (nextToken()) {
                            if (currentToken == Token.ABRE_PARENTESES) {
                                removeToken();
                                if (new ParametrosHandler(tokens).handle()) {
                                    if (currentToken == Token.FECHA_PARENTESES) {
                                        removeToken();
                                        if (nextToken()) {
                                            if (currentToken == Token.PONTO_VIRGULA) {
                                                removeToken();
                                                nextToken();
                                                if (currentToken == Token.PC_CONSTANTE) {
                                                    if (new DeclConstanteHandler(tokens).handle()) {
                                                        nextToken();
                                                        if (currentToken == Token.PC_VARIAVEL) {
                                                            if (new DeclaracaoVariavelHandler(tokens).handle()) {
                                                                return (new BlocoHandler(tokens).handle());
                                                            } else {
                                                                return false;
                                                            }
                                                        } else {
                                                            return new BlocoHandler(tokens).handle();
                                                        }
                                                    } else {
                                                        return false;
                                                    }
                                                }
                                                if (currentToken == Token.PC_VARIAVEL) {
                                                    if (new DeclaracaoVariavelHandler(tokens).handle()) {
                                                        return (new BlocoHandler(tokens).handle());
                                                    } else {
                                                        return false;
                                                    }
                                                }
                                                return (new BlocoHandler(tokens).handle());
                                            } else {
                                                setCodError(10); //Esperado ";" , mas encontrou outra cisa.
                                                return false;
                                            }
                                        } else {
                                            setCodError(9); //Esperado ";" , mas FDA encontrado.
                                            return false;
                                        }
                                    } else {
                                        setCodError(104); //Esperado ")", mas encontrou outra coisa.
                                        return false;
                                    }
                                } else {
                                    return false;
                                }
                            } else {
                                setCodError(103); //Esperado "(", mas encontrou outra coisa.
                                return false;
                            }
                        } else {
                            setCodError(102);//Esperado "(" , mas FDA encontrado.
                            return false;
                        }
                    } else {
                        setCodError(6); //Esperado identificador, mas encontrou outra coisa.
                        return false;
                    }
                } else {
                    setCodError(5); //Esperado identificador, mas FDA encontrado.
                    return false;
                }
            }
            if (currentToken == Token.PC_FUNCAO) {
                removeToken();
                if (nextToken()) {
                    if (currentToken == Token.IDENTIFICADOR) {
                        removeToken();
                        if (nextToken()) {
                            if (currentToken == Token.ABRE_PARENTESES) {
                                removeToken();
                                if (new ParametrosHandler(tokens).handle()) {
                                    if (currentToken == Token.FECHA_PARENTESES) {
                                        removeToken();
                                        if (nextToken()) {
                                            if (currentToken == Token.DOIS_PONTOS) {
                                                removeToken();
                                                if (new TipoHandler(tokens).handle()) {
                                                    if (nextToken()) {
                                                        if (currentToken == Token.PONTO_VIRGULA) {
                                                            removeToken();
                                                            nextToken();
                                                            if (currentToken == Token.PC_CONSTANTE) {
                                                                if (new DeclConstanteHandler(tokens).handle()) {
                                                                    nextToken();
                                                                    if (currentToken == Token.PC_VARIAVEL) {
                                                                        if (new DeclaracaoVariavelHandler(tokens).handle()) {
                                                                            return (new BlocoHandler(tokens).handle());
                                                                        } else {
                                                                            return false;
                                                                        }
                                                                    } else {
                                                                        return (new BlocoHandler(tokens).handle());
                                                                    }
                                                                } else {
                                                                    return false;
                                                                }
                                                            }

                                                            if (currentToken == Token.PC_VARIAVEL) {
                                                                if (new DeclaracaoVariavelHandler(tokens).handle()) {
                                                                    return (new BlocoHandler(tokens).handle());
                                                                } else {
                                                                    return false;
                                                                }
                                                            }
                                                            
                                                            return (new BlocoHandler(tokens).handle());
                                                        } else {
                                                            setCodError(10); //Esperado ";" , mas encontrou outra cisa.
                                                            return false;
                                                        }
                                                    } else {
                                                        setCodError(9); //Esperado ";" , mas FDA encontrado.
                                                        return false;
                                                    }
                                                } else {
                                                    return false;
                                                }
                                            } else {
                                                setCodError(8);
                                                return false;
                                            }
                                        } else {
                                            setCodError(7); //Esperado ":", mas FDA.
                                            return false;
                                        }
                                    } else {
                                        setCodError(104);//Esperado ")" , mas encontrou outra coisa.
                                        return false;
                                    }
                                } else {
                                    return false;
                                }
                            } else {
                                setCodError(103); //Esperado "(", mas encontrou outra coisa.
                                return false;
                            }
                        } else {
                            setCodError(102); // Esperado "(", mas FDA encontrado.
                            return false;
                        }
                    } else {
                        setCodError(6); //Esperado Identificador, mas encontrou outra coisa.
                        return false;
                    }
                } else {
                    setCodError(5);
                    return false;
                }
            } else {
                setCodError(106);
                return false;
            }
        } else {
            setCodError(100);
            return false;
        }
    }

}
