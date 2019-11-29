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
public class ContSeHandler extends AbstractHandler {

    public ContSeHandler(ArrayList<TokenModel> tokens, DefaultMutableTreeNode noPai) {
        super(tokens, noPai);
    }

    @Override
    public boolean handle() {
        DefaultMutableTreeNode contSe = new DefaultMutableTreeNode("ContSe");
        nextToken();
        if (currentToken == Token.PC_SENAO) {
            contSe.add(new DefaultMutableTreeNode("senao"));
            removeToken();
            if (new InstHandler(tokens, contSe).handle()) {
                this.noPai.add(contSe);
                return true;
            } else {
                return false;
            }
        }
        return true;
    }
    
}
