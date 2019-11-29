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
public class ContParametrosHandler extends AbstractHandler {

    public ContParametrosHandler(ArrayList<TokenModel> tokens, DefaultMutableTreeNode noPai) {
        super(tokens, noPai);
    }

    @Override
    public boolean handle() {
        DefaultMutableTreeNode contParametros = new DefaultMutableTreeNode("ContParametros");
        nextToken();
        if (currentToken == Token.VIRGULA) {
            contParametros.add(new DefaultMutableTreeNode(","));
            removeToken();
            if (new ExprHandler(tokens, contParametros).handle() && new ContParametrosHandler(tokens, contParametros).handle()) {
                this.noPai.add(contParametros);
                return true;
            }
            return false;
        }
        return true;
    }
    
}
