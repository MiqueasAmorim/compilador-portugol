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
public class DeclConsListHandler extends AbstractHandler{

    public DeclConsListHandler(ArrayList<TokenModel> tokens) {
        super(tokens);
    }

    @Override
    public boolean handle() {
        return (new DeclConstHandler(tokens).handle() && new DeclConsList2Handler(tokens).handle());
    }
    
}
