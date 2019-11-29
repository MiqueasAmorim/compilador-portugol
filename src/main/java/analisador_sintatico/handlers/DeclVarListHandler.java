/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisador_sintatico.handlers;

import static analisador_sintatico.handlers.AbstractHandler.currentToken;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import model.Token;
import model.TokenModel;

/**
 *
 * @author Miqueas
 */
public class DeclVarListHandler extends AbstractHandler {

    public DeclVarListHandler(ArrayList<TokenModel> tokens, DefaultMutableTreeNode noPai) {
        super(tokens, noPai);
    }

    @Override
    public boolean handle() {
//        if (nextToken()) {
            DefaultMutableTreeNode declVarList = new DefaultMutableTreeNode("DeclVarList");
            if (new DeclVarHandler(tokens, declVarList).handle() && new DeclVarList2Handler(tokens, declVarList).handle()) {
                this.noPai.add(declVarList);
                return true;
            } else {
                return false;
            }
//        } else {
//            return false;
//        }  
    }
    
}
