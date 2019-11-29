/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisador_sintatico.handlers;

import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import model.Token;
import model.TokenModel;

/**
 *
 * @author Jefferson
 */
public class DeclProcHandler extends AbstractHandler {

    public DeclProcHandler(ArrayList<TokenModel> tokens, DefaultMutableTreeNode noPai) {
        super(tokens, noPai);
    }

    @Override
    public boolean handle() {
        DefaultMutableTreeNode declProc = new DefaultMutableTreeNode("DeclProc");
        if (nextToken()) {
            if (currentToken == Token.PC_PROCEDIMENTO) {
                declProc.add(new DefaultMutableTreeNode("procedimento"));
                removeToken();
                if (nextToken()) {
                    //if (currentToken == Token.IDENTIFICADOR) {
                    if (new IdentificadorHandler(tokens, declProc).handle()) {
                        //removeToken();
                        if (nextToken()) {
                            if (currentToken == Token.ABRE_PARENTESES) {
                                declProc.add(new DefaultMutableTreeNode("("));
                                removeToken();
                                if (new ParametrosHandler(tokens, declProc).handle()) {
                                    if (currentToken == Token.FECHA_PARENTESES) {
                                        declProc.add(new DefaultMutableTreeNode(")"));
                                        removeToken();
                                        if (nextToken()) {
                                            if (currentToken == Token.PONTO_VIRGULA) {
                                                declProc.add(new DefaultMutableTreeNode(";"));
                                                removeToken();
                                                nextToken();
                                                if (currentToken == Token.PC_CONSTANTE) {
                                                    if (new DeclaracaoConstanteHandler(tokens, declProc).handle()) {
                                                        nextToken();
                                                        if (currentToken == Token.PC_VARIAVEL) {
                                                            if (new DeclaracaoVariavelHandler(tokens, declProc).handle()) {
                                                                if (new BlocoHandler(tokens, declProc).handle()) {
                                                                    this.noPai.add(declProc);
                                                                    return true;
                                                                }
                                                                return false;
                                                            } else {
                                                                return false;
                                                            }
                                                        } else {
                                                            if (new BlocoHandler(tokens, declProc).handle()) {
                                                                this.noPai.add(declProc);
                                                                return true;
                                                            }
                                                            return false;
                                                        }
                                                    } else {
                                                        return false;
                                                    }
                                                }
                                                if (currentToken == Token.PC_VARIAVEL) {
                                                    if (new DeclaracaoVariavelHandler(tokens, declProc).handle()) {
                                                        if (new BlocoHandler(tokens, declProc).handle()) {
                                                            this.noPai.add(declProc);
                                                            return true;
                                                        }
                                                        return false;
                                                    } else {
                                                        return false;
                                                    }
                                                }
                                                if (new BlocoHandler(tokens, declProc).handle()) {
                                                    this.noPai.add(declProc);
                                                    return true;
                                                }
                                                return false;
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
                declProc.add(new DefaultMutableTreeNode("funcao"));
                removeToken();
                if (nextToken()) {
                    if (new IdentificadorHandler(tokens, declProc).handle()) {
                        //removeToken();
                        if (nextToken()) {
                            if (currentToken == Token.ABRE_PARENTESES) {
                                declProc.add(new DefaultMutableTreeNode("("));
                                removeToken();
                                if (new ParametrosHandler(tokens, declProc).handle()) {
                                    if (currentToken == Token.FECHA_PARENTESES) {
                                        declProc.add(new DefaultMutableTreeNode(")"));
                                        removeToken();
                                        if (nextToken()) {
                                            if (currentToken == Token.DOIS_PONTOS) {
                                                declProc.add(new DefaultMutableTreeNode(":"));
                                                removeToken();
                                                if (new TipoHandler(tokens, declProc).handle()) {
                                                    if (nextToken()) {
                                                        if (currentToken == Token.PONTO_VIRGULA) {
                                                            declProc.add(new DefaultMutableTreeNode(";"));
                                                            removeToken();
                                                            nextToken();
                                                            if (currentToken == Token.PC_CONSTANTE) {
                                                                if (new DeclaracaoConstanteHandler(tokens, declProc).handle()) {
                                                                    nextToken();
                                                                    if (currentToken == Token.PC_VARIAVEL) {
                                                                        if (new DeclaracaoVariavelHandler(tokens, declProc).handle()) {
                                                                            if (new BlocoHandler(tokens, declProc).handle()) {
                                                                                this.noPai.add(declProc);
                                                                                return true;
                                                                            }
                                                                            return false;
                                                                        }
                                                                        return false;
                                                                    } else {
                                                                        if (new BlocoHandler(tokens, declProc).handle()) {
                                                                            this.noPai.add(declProc);
                                                                            return true;
                                                                        }
                                                                        return false;
                                                                    }
                                                                } else {
                                                                    return false;
                                                                }
                                                            }

                                                            if (currentToken == Token.PC_VARIAVEL) {
                                                                if (new DeclaracaoVariavelHandler(tokens, declProc).handle()) {
                                                                    if (new BlocoHandler(tokens, declProc).handle()) {
                                                                        this.noPai.add(declProc);
                                                                        return true;
                                                                    }
                                                                    return false;
                                                                }
                                                                return false;
                                                            }
                                                            if (new BlocoHandler(tokens, declProc).handle()) {
                                                                this.noPai.add(declProc);
                                                                return true;
                                                            }
                                                            return false;
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
