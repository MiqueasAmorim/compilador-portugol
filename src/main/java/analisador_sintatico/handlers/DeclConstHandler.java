/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisador_sintatico.handlers;

import java.util.ArrayList;
import model.TokenModel;

/**
 *
 * @author Jefferson
 */
public class DeclConstHandler extends AbstractHandler{

    public DeclConstHandler(ArrayList<TokenModel> tokens) {
        super(tokens);
    }

    @Override
    public boolean handle() {
        if(new VariavelHandler(tokens).handle()){
            if(new ConjuntoIdsHandler(tokens).handle()){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    
}
