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
 * @author Miqueas
 */
public class InstHandler extends AbstractHandler {

    public InstHandler(ArrayList<TokenModel> tokens, DefaultMutableTreeNode noPai) {
        super(tokens, noPai);
    }

    @Override
    public boolean handle() {
        DefaultMutableTreeNode inst = new DefaultMutableTreeNode("Inst");
        nextToken();
        if (currentToken == null) {
            setCodError(17);
            return false;
        }
        if (new IdentificadorHandler(tokens, inst).handle()) {
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
                inst.add(new DefaultMutableTreeNode(":="));
                removeToken();
                if (new ExprHandler(tokens, inst).handle()) {
                    if (!nextToken()) {
                        setCodError(9); // Esperado ";", mas encontrou "fim de arquivo"
                        return false;
                    }
                    if (currentToken == Token.PONTO_VIRGULA) {
                        inst.add(new DefaultMutableTreeNode(";"));
                        removeToken();
                        this.noPai.add(inst);
                        return true;
                    } else {
                        setCodError(10); // Esperado ";", mas outra coisa encontrada
                        return false;
                    }
                }
            }

            if (currentToken == Token.ABRE_COLCHETES) {
                inst.add(new DefaultMutableTreeNode("["));
                removeToken();
                if (new ExprHandler(tokens, inst).handle()) {
                    if (!nextToken()) {
                        setCodError(29); // Esperado "]", mas encontrou "fim de arquivo"
                        return false;
                    }
                    if (currentToken == Token.FECHA_COLCHETES) {
                        inst.add(new DefaultMutableTreeNode("]"));
                        removeToken();
                        if (!nextToken()) {
                            setCodError(31); // Esperado ":=", mas encontrou "fim de arquivo"
                            return false;
                        }
                        if (currentToken == Token.OP_ATRIBUICAO) {
                            inst.add(new DefaultMutableTreeNode(":="));
                            removeToken();
                            if (new ExprHandler(tokens, inst).handle()) {
                                if (!nextToken()) {
                                    setCodError(9); // Esperado ";", mas encontrou "fim de arquivo"
                                    return false;
                                }
                                if (currentToken == Token.PONTO_VIRGULA) {
                                    inst.add(new DefaultMutableTreeNode(";"));
                                    removeToken();
                                    this.noPai.add(inst);
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
                inst.add(new DefaultMutableTreeNode("("));
                removeToken();
                if (new Parametros2Handler(tokens, inst).handle()) {
                    if (!nextToken()) {
                        setCodError(33); // Esperado ")", mas encontrou "fim de arquivo"
                        return false;
                    }
                    if (currentToken == Token.FECHA_PARENTESES) {
                        inst.add(new DefaultMutableTreeNode(")"));
                        removeToken();
                        if (!nextToken()) {
                            setCodError(9); // Esperado ";", mas encontrou "fim de arquivo"
                            return false;
                        }
                        if (currentToken == Token.PONTO_VIRGULA) {
                            inst.add(new DefaultMutableTreeNode(";"));
                            removeToken();
                            this.noPai.add(inst);
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
            inst.add(new DefaultMutableTreeNode("se"));
            removeToken();
            if (new ExprHandler(tokens, inst).handle()) {
                if (!nextToken()) {
                    setCodError(35); // Esperado "ENTAO", mas "fim de arquivo" encontrado
                    return false;
                }
                if (currentToken == Token.PC_ENTAO) {
                    inst.add(new DefaultMutableTreeNode("entao"));
                    removeToken();
                    if (new InstHandler(tokens, inst).handle()) {
                        nextToken();
                        if (currentToken == Token.PC_SENAO) {
                            if (new ContSeHandler(tokens, inst).handle()) {
                                this.noPai.add(inst);
                                return true;
                            }
                            return false;
                        }
                        this.noPai.add(inst);
                        return true;
                    }
                } else {
                    setCodError(36); // Esperado "ENTAO", mas outro token encontrado
                    return false;
                }
            }
        }
        if (currentToken == Token.PC_ENQUANTO) {
            inst.add(new DefaultMutableTreeNode("enquanto"));
            removeToken();
            if (new ExprHandler(tokens, inst).handle()) {
                if (!nextToken()) {
                    setCodError(37); // Esperado "FACA", mas "fim de arquivo" encontrado
                    return false;
                }
                if (currentToken == Token.PC_FACA) {
                    inst.add(new DefaultMutableTreeNode("faca"));
                    removeToken();
                    if (new InstHandler(tokens, inst).handle()) {
                        this.noPai.add(inst);
                        return true;
                    }
                    return false;
                } else {
                    setCodError(38); // Esperado "FACA", mas outro token encontrado
                    return false;
                }
            }
            return false;
        }

        if (currentToken == Token.PC_REPITA) {
            inst.add(new DefaultMutableTreeNode("repita"));
            removeToken();
            if (new InstHandler(tokens, inst).handle()) {
                if (!nextToken()) {
                    setCodError(39); // Esperado "ATE", mas "fim de arquivo" encontrado
                    return false;
                }
                if (currentToken == Token.PC_ATE) {
                    inst.add(new DefaultMutableTreeNode("ate"));
                    removeToken();
                    if (new ExprHandler(tokens, inst).handle()) {
                        if (!nextToken()) {
                            setCodError(9); // Esperado ";", mas "fim de arquivo" encontrado
                            return false;
                        }
                        if (currentToken == Token.PONTO_VIRGULA) {
                            inst.add(new DefaultMutableTreeNode(";"));
                            removeToken();
                            this.noPai.add(inst);
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
            inst.add(new DefaultMutableTreeNode("para"));
            removeToken();
            if (new IdentificadorHandler(tokens, inst).handle()) {
                if (!nextToken()) {
                    setCodError(31); // Esperado ":=", mas "fim de arquivo" encontrado
                    return false;
                }
                if (currentToken == Token.OP_ATRIBUICAO) {
                    inst.add(new DefaultMutableTreeNode(":="));
                    removeToken();
                    nextToken();
                    if (currentToken == Token.INTEIRO || currentToken == Token.IDENTIFICADOR) {
                        inst.add(new DefaultMutableTreeNode(getCurrentLexema()));
                        removeToken();
                        if (!nextToken()) {
                            setCodError(39); // Esperado "ATE", mas "fim de arquivo" encontrado
                            return false;
                        }
                        if (currentToken == Token.PC_ATE) {
                            inst.add(new DefaultMutableTreeNode("ate"));
                            removeToken();
                            nextToken();
                            if (currentToken == Token.INTEIRO || currentToken == Token.IDENTIFICADOR) {
                                inst.add(new DefaultMutableTreeNode(getCurrentLexema()));
                                removeToken();
                                if (!nextToken()) {
                                    setCodError(37); // Esperado "FACA", mas "fim de arquivo" encontrado
                                    return false;
                                }
                                if (currentToken == Token.PC_FACA) {
                                    inst.add(new DefaultMutableTreeNode("faca"));
                                    removeToken();
                                    if (new InstHandler(tokens, inst).handle()) {
                                        this.noPai.add(inst);
                                        return true;
                                    }
                                    return false;
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
            inst.add(new DefaultMutableTreeNode("pare"));
            removeToken();
            if (!nextToken()) {
                setCodError(9); // Esperado ";", mas "fim de arquivo" encontrado
                return false;
            }
            if (currentToken == Token.PONTO_VIRGULA) {
                inst.add(new DefaultMutableTreeNode(";"));
                removeToken();
                this.noPai.add(inst);
                return true;
            } else {
                setCodError(10); // Esperado ";", mas outro token encontrado
                return false;
            }
        }

        if (currentToken == Token.PC_CONTINUA) {
            inst.add(new DefaultMutableTreeNode("continua"));
            removeToken();
            if (!nextToken()) {
                setCodError(9); // Esperado ";", mas "fim de arquivo" encontrado
                return false;
            }
            if (currentToken == Token.PONTO_VIRGULA) {
                inst.add(new DefaultMutableTreeNode(";"));
                removeToken();
                this.noPai.add(inst);
                return true;
            } else {
                setCodError(10); // Esperado ";", mas outro token encontrado
                return false;
            }
        }

        if (currentToken == Token.PC_LEIA) {
            inst.add(new DefaultMutableTreeNode("leia"));
            removeToken();
            if (!nextToken()) {
                setCodError(102); // Esperado "(", mas "fim de arquivo" encontrado
                return false;
            }
            if (currentToken == Token.ABRE_PARENTESES) {
                inst.add(new DefaultMutableTreeNode("("));
                removeToken();
                if (new IdentificadorHandler(tokens, inst).handle() && new ConjuntoIdsHandler(tokens, inst).handle()) {
                    if (!nextToken()) {
                        setCodError(33); // Esperado ")", mas "fim de arquivo" encontrado
                        return false;
                    }
                    if (currentToken == Token.FECHA_PARENTESES) {
                        inst.add(new DefaultMutableTreeNode(")"));
                        removeToken();
                        if (!nextToken()) {
                            setCodError(9); // Esperado ";", mas "fim de arquivo" encontrado
                            return false;
                        }
                        if (currentToken == Token.PONTO_VIRGULA) {
                            inst.add(new DefaultMutableTreeNode(";"));
                            removeToken();
                            this.noPai.add(inst);
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
            inst.add(new DefaultMutableTreeNode("escreva"));
            removeToken();
            if (!nextToken()) {
                setCodError(102); // Esperado "(", mas "fim de arquivo" encontrado
                return false;
            }
            if (currentToken == Token.ABRE_PARENTESES) {
                inst.add(new DefaultMutableTreeNode("("));
                removeToken();
                if (new ConteudoHandler(tokens, inst).handle() && new MaisConteudoHandler(tokens, inst).handle()) {
                    if (!nextToken()) {
                        setCodError(33); // Esperado ")", mas "fim de arquivo" encontrado
                        return false;
                    }
                    if (currentToken == Token.FECHA_PARENTESES) {
                        inst.add(new DefaultMutableTreeNode(")"));
                        removeToken();
                        if (!nextToken()) {
                            setCodError(9); // Esperado ";", mas "fim de arquivo" encontrado
                            return false;
                        }
                        if (currentToken == Token.PONTO_VIRGULA) {
                            inst.add(new DefaultMutableTreeNode(";"));
                            removeToken();
                            this.noPai.add(inst);
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
            if (new BlocoHandler(tokens, inst).handle()) {
                this.noPai.add(inst);
                return true;
            }
            return false;
        }

        //setCodError(16);
        return false;
    }

}
