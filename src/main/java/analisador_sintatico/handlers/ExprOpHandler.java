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
 * @author Miqueas
 */
public class ExprOpHandler extends AbstractHandler {

    public ExprOpHandler(ArrayList<TokenModel> tokens) {
        super(tokens);
    }

    @Override
    public boolean handle() {
        return (new TermoHandler(tokens).handle() && new ExprOp2Handler(tokens).handle());
    }
    
}
