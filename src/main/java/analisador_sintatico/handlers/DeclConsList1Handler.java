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
public class DeclConsList1Handler extends AbstractHandler {

    public DeclConsList1Handler(ArrayList<TokenModel> tokens, DefaultMutableTreeNode noPai) {
        super(tokens, noPai);
    }

    @Override
    public boolean handle() {
        DefaultMutableTreeNode declConsList1 = new DefaultMutableTreeNode("DeclConsList1");
        if (new DeclConstHandler(tokens, declConsList1).handle()) {
            if (new DeclConsList2Handler(tokens, declConsList1).handle()) {
                this.noPai.add(declConsList1);
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }
}
