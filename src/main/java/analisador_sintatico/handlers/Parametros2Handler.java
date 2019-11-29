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
public class Parametros2Handler extends AbstractHandler {

    public Parametros2Handler(ArrayList<TokenModel> tokens, DefaultMutableTreeNode noPai) {
        super(tokens, noPai);
    }

    @Override
    public boolean handle() {
        DefaultMutableTreeNode parametros2 = new DefaultMutableTreeNode("Parametros2");
        nextToken();
        if (currentToken == Token.FECHA_PARENTESES) {
            return true;
        }
        if (new ExprHandler(tokens, parametros2).handle() && new ContParametrosHandler(tokens, parametros2).handle()) {
            this.noPai.add(parametros2);
            return true;
        }
        return false;
    }
    
}
