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
 * @author Jefferson
 */
public class ParametrosHandler extends AbstractHandler {

    public ParametrosHandler(ArrayList<TokenModel> tokens, DefaultMutableTreeNode noPai) {
        super(tokens, noPai);
    }

    @Override
    public boolean handle() {
        DefaultMutableTreeNode parametros = new DefaultMutableTreeNode("Parametros");
        nextToken();
        
        if (currentToken == Token.FECHA_PARENTESES) {
            return true;

        } else if (new DeclVar2Handler(tokens, parametros).handle()) {
            nextToken();
            if (currentToken == Token.FECHA_PARENTESES) {
                if (new ParametrosHandler(tokens, parametros).handle()) {
                    this.noPai.add(parametros);
                    return true;
                }
                return false;
            } else if (currentToken == Token.PONTO_VIRGULA) {
                parametros.add(new DefaultMutableTreeNode(";"));
                removeToken();
                nextToken();
                if (currentToken != Token.FECHA_PARENTESES) {
                    if (new ParametrosHandler(tokens, parametros).handle()) {
                        this.noPai.add(parametros);
                        return true;
                    }
                } 
                return false;
            } else {
                setCodError(10);
                return false;
            }

        } else {
            return false;
        }

    }
}
