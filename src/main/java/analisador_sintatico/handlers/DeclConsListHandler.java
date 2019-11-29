/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisador_sintatico.handlers;

import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import model.TokenModel;

/**
 *
 * @author Jefferson
 */
public class DeclConsListHandler extends AbstractHandler{

    public DeclConsListHandler(ArrayList<TokenModel> tokens, DefaultMutableTreeNode noPai) {
        super(tokens, noPai);
    }

    @Override
    public boolean handle() {
        DefaultMutableTreeNode declConsList = new DefaultMutableTreeNode("DeclConsList");
        if (new DeclConstHandler(tokens, declConsList).handle() && new DeclConsList2Handler(tokens, declConsList).handle()) {
            this.noPai.add(declConsList);
            return true;
        } else {
            return false;
        }
    }
    
}
