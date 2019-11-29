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
 * @author Miqueas
 */
public class TermoHandler extends AbstractHandler {

    public TermoHandler(ArrayList<TokenModel> tokens, DefaultMutableTreeNode noPai) {
        super(tokens, noPai);
    }

    @Override
    public boolean handle() {
        DefaultMutableTreeNode termo = new DefaultMutableTreeNode("Termo");
        if (new UnarioHandler(tokens, termo).handle() && new Termo2Handler(tokens, termo).handle()) {
            this.noPai.add(termo);
            return true;
        }
        return false;
    }
    
}
