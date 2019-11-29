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
public class IdentificadorHandler extends AbstractHandler {
    
    public IdentificadorHandler(ArrayList<TokenModel> tokens, DefaultMutableTreeNode noPai) {
        super(tokens, noPai);
    }

    @Override
    public boolean handle() {
        if (nextToken()) {
            if (currentToken == Token.IDENTIFICADOR) {
                DefaultMutableTreeNode identificador = new DefaultMutableTreeNode("Identificador");
                identificador.add(new DefaultMutableTreeNode(getCurrentLexema()));
                this.noPai.add(identificador);
                removeToken();
                return true;
            } else {
                setCodError(6);
                return false;
            }
        } else {
            setCodError(5);
            return false;
        }
    }
    
}
