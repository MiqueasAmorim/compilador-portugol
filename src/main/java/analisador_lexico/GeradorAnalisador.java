/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisador_lexico;
import java.io.File;
import java.nio.file.Paths;
/**
 *
 * @author Miqueas
 */


public class GeradorAnalisador {

    public static void main(String[] args) {

        String rootPath = Paths.get("").toAbsolutePath(). toString();
        String subPath = "/src/main/java/analisador_lexico/";

        String file = rootPath + subPath + "Lexer.flex";

        File sourceCode = new File(file);

        jflex.Main.generate(sourceCode);

    }
}
