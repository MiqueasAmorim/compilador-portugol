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
public class BlocoHandler extends AbstractHandler {

    public BlocoHandler(ArrayList<TokenModel> tokens, DefaultMutableTreeNode noPai) {
        super(tokens, noPai);
    }

    @Override
    public boolean handle() {
        DefaultMutableTreeNode bloco = new DefaultMutableTreeNode("Bloco");
        if (nextToken()) {
            if (currentToken == Token.PC_INICIO) {
                bloco.add(new DefaultMutableTreeNode("inicio"));
                removeToken();
                if (new InstrucoesHandler(tokens, bloco).handle()) {
                    if (nextToken()) {
                        if (currentToken == Token.PC_FIM) {
                            bloco.add(new DefaultMutableTreeNode("fim"));
                            removeToken();
                            if (nextToken()) {
                                if (currentToken == Token.PONTO_VIRGULA) {
                                    bloco.add(new DefaultMutableTreeNode(";"));
                                    removeToken();
                                    this.noPai.add(bloco);
                                    return true;
                                } else {
                                    setCodError(10);// Esperado ";", mas encontrou outra coisa
                                }
                            } else {
                                setCodError(9);// Esperado ";", mas encontrou "fim de arquivo"
                            }
                        } else {
                            setCodError(21);// Esperado "FIM", mas encontrou outra coisa
                        }
                    } else {
                        setCodError(20);// Esperado "FIM", mas "fim de arquivo" encontrado
                    }
                }
            } else {
                setCodError(19);// Esperado "INICIO", mas outra coisa encontrada
            }
        } else {          
            setCodError(18);// Esperado "INICIO", mas encontrou "fim de arquivo"
        }
        return false;
    }

}
