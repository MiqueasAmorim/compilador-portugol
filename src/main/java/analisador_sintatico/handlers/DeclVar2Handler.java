/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisador_sintatico.handlers;

import static analisador_sintatico.handlers.AbstractHandler.tokens;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import model.Token;
import model.TokenModel;

/**
 *
 * @author Jefferson
 */
public class DeclVar2Handler extends AbstractHandler {

    public DeclVar2Handler(ArrayList<TokenModel> tokens, DefaultMutableTreeNode noPai) {
        super(tokens, noPai);
    }

    @Override
    public boolean handle() {
        DefaultMutableTreeNode declVar2 = new DefaultMutableTreeNode("DeclVar2");
        if (new IdentificadorHandler(tokens, declVar2).handle()) {
            if (new ConjuntoIdsHandler(tokens, declVar2).handle()) {
                if (nextToken()) {
                    if (currentToken == Token.DOIS_PONTOS) {
                        declVar2.add(new DefaultMutableTreeNode(":"));
                        removeToken();
                        if (new TipoHandler(tokens, declVar2).handle()) {
                            this.noPai.add(declVar2);
                            return true;
                        }  
                        return false;
                    } else {
                        setCodError(1); // Esperado ":", mas encontrado outra coisa
                        return false;
                    }
                } else {
                    setCodError(7);  // Esperado dois pontos, mas encontrou nenhum token
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
