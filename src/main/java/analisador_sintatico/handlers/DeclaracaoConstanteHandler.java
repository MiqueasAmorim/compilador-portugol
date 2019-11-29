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
public class DeclaracaoConstanteHandler extends AbstractHandler{

    public DeclaracaoConstanteHandler(ArrayList<TokenModel> tokens, DefaultMutableTreeNode noPai) {
        super(tokens, noPai);
    }

    @Override
    public boolean handle() {
        DefaultMutableTreeNode declaracaoConstante = new DefaultMutableTreeNode("DeclaracaoConstante");
        if(nextToken()){
            if(currentToken == Token.PC_CONSTANTE){
                declaracaoConstante.add(new DefaultMutableTreeNode("constante"));
                removeToken();
                if (new DeclConsListHandler(tokens, declaracaoConstante).handle()) {
                    this.noPai.add(declaracaoConstante);
                    return true;
                } else {
                    return false;
                }
            }else{
                System.out.println("Esperando token CONSTANTE!");
                return false;
            }
        }else{
            System.out.println("Esperando token CONSTANTE MAIS FDA ENCONTRADO!");
            return false;
        }
    }
}
