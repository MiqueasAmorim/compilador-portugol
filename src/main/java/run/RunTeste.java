/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

import analisador_lexico.AnalisadorLexico;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miqueas
 */
public class RunTeste {
    public static void main(String[] args) {
        try {
            AnalisadorLexico lexical = new AnalisadorLexico();
        } catch (IOException ex) {
            Logger.getLogger(RunTeste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
