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
public class ConjuntoIdsHandler extends AbstractHandler {

    public ConjuntoIdsHandler(ArrayList<TokenModel> tokens, DefaultMutableTreeNode noPai) {
        super(tokens, noPai);
    }

    @Override
    public boolean handle() {
        DefaultMutableTreeNode conjuntoIds = new DefaultMutableTreeNode("ConjuntoIds");
//        if (nextToken()) {
            nextToken();
            if (currentToken == Token.VIRGULA) {
                conjuntoIds.add(new DefaultMutableTreeNode(","));
                removeToken();
                if (new IdentificadorHandler(tokens, conjuntoIds).handle() && new ConjuntoIdsHandler(tokens, conjuntoIds).handle()) {
                    this.noPai.add(conjuntoIds);
                    return true;
                } else {
                    return false;
                }
            } else if (currentToken == Token.DOIS_PONTOS) {
                return true;
            }
//        } else {
//            return false;
//        }
        return true;
    }
    
}
