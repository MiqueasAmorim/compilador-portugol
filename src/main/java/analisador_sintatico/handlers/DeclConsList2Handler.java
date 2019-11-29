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
public class DeclConsList2Handler extends AbstractHandler {

    public DeclConsList2Handler(ArrayList<TokenModel> tokens, DefaultMutableTreeNode noPai) {
        super(tokens, noPai);
    }

    @Override
    public boolean handle() {
        DefaultMutableTreeNode declConsList2 = new DefaultMutableTreeNode("DeclConsList2");
        if (nextToken()) {
            if (currentToken == Token.DOIS_PONTOS) {
                declConsList2.add(new DefaultMutableTreeNode(":"));
                removeToken();
                if (nextToken()) {
                    if (new TipoHandler(tokens, declConsList2).handle()) {
                        if (nextToken()) {
                            if (currentToken == Token.OP_IGUAL) {
                                declConsList2.add(new DefaultMutableTreeNode("="));
                                removeToken();
                                if (new ValorHandler(tokens, declConsList2).handle()) {
                                    if (nextToken()) {
                                        if (currentToken == Token.PONTO_VIRGULA) {
                                            declConsList2.add(new DefaultMutableTreeNode(";"));
                                            removeToken();
                                            if (new DeclConsList1Handler(tokens, declConsList2).handle()) {
                                                this.noPai.add(declConsList2);
                                                return true;
                                            } else {
                                                return false;
                                            }
                                        } else {
                                            setCodError(10);
                                            return false;
                                        }
                                    } else {
                                           setCodError(22);
                                        return false;
                                    }
                                } else {
                                        setCodError(204);
                                    return false;
                                }
                            } else {
                                    setCodError(2);
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
                } else {
                    setCodError(1);
                    return false;
                }
            
            }

            if (currentToken == Token.OP_IGUAL) {
                declConsList2.add(new DefaultMutableTreeNode("="));
                removeToken();
                if (new ValorHandler(tokens, declConsList2).handle()) {
                    if (nextToken()) {
                        if (currentToken == Token.PONTO_VIRGULA) {
                            declConsList2.add(new DefaultMutableTreeNode(";"));
                            removeToken();
                            if (new DeclConsList1Handler(tokens, declConsList2).handle()) {
                                this.noPai.add(declConsList2);
                                return true;
                            } else {
                                return false;
                            }
                        } else {
                            setCodError(10);
                            return false;
                        }
                    } else {
                        setCodError(22);
                        return false;
                    }
                } else {
                    setCodError(204);
                    return false;
                }
            } else {
                setCodError(203);
                return false;
            }
        } else {
                setCodError(7);
            return false;
        }
    }
}
