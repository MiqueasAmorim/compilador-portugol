/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisador_lexico;

import model.TokenModel;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

/**
 *
 * @author Miqueas
 */

public class AnalisadorLexico {
    public static void main(String[] args) throws IOException {

        String rootPath = Paths.get("").toAbsolutePath(). toString();
        String subPath = "/src/main/java/analisador_lexico/";

        String sourceCode = rootPath + subPath + "programa.ptl";

        Lexer lexical = new Lexer(new FileReader(sourceCode));

        TokenModel token;

        while ((token = lexical.yylex()) != null) {
            System.out.println("<" + token.getID() + ", " + token.getNome() + ", " + token.getLexema() + "> (" + token.getLinha() + " - " + token.getColuna() + ")");
        }
    }
}
