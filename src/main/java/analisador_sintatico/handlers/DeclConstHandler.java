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
public class DeclConstHandler extends AbstractHandler{

    public DeclConstHandler(ArrayList<TokenModel> tokens, DefaultMutableTreeNode noPai) {
        super(tokens, noPai);
    }

    @Override
    public boolean handle() {
        DefaultMutableTreeNode declConst = new DefaultMutableTreeNode("DeclConst");
        if(new IdentificadorHandler(tokens, declConst).handle()){
            if(new ConjuntoIdsHandler(tokens, declConst).handle()){
                this.noPai.add(declConst);
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    
}
