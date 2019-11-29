/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisador_sintatico.handlers;

import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import model.ErrorModel;
import model.Token;
import model.TokenModel;

/**
 *
 * @author Miqueas
 */
public class DeclVarHandler extends AbstractHandler {

    public DeclVarHandler(ArrayList<TokenModel> tokens, DefaultMutableTreeNode noPai) {
        super(tokens, noPai);
    }

    @Override
    public boolean handle() {
        DefaultMutableTreeNode declVar = new DefaultMutableTreeNode("DeclVar");
//        if (nextToken()) {
            if (new IdentificadorHandler(tokens, declVar).handle()) {
//                if (nextToken()) {
                    if (new ConjuntoIdsHandler(tokens, declVar).handle()) {
                    //new ConjuntoIdsHandler(tokens).handle();
                        if (nextToken()) {
                            if (currentToken == Token.DOIS_PONTOS) {
                                declVar.add(new DefaultMutableTreeNode(":"));
                                removeToken();
                                if (new TipoHandler(tokens, declVar).handle()) {
                                    if (nextToken()) {
                                        if (currentToken == Token.PONTO_VIRGULA) {
                                            declVar.add(new DefaultMutableTreeNode(";"));
                                            this.noPai.add(declVar);
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
