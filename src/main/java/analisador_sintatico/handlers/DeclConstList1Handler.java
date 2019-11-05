/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisador_sintatico.handlers;

import analisador_sintatico.handlers.AbstractHandler;
import java.util.ArrayList;
import model.TokenModel;

/**
 *
 * @author andrei
 */
public class DeclConstList1Handler extends AbstractHandler {

    public DeclConstList1Handler(ArrayList<TokenModel> tokens) {
        super(tokens);
    }

    @Override
    public boolean handle() {
        if (new DeclConstHandler(tokens).handle()) {
            if (new DeclConsList2Handler(tokens).handle()) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
//        return true;
    }
}


