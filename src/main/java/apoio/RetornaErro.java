/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;

import model.ErrorModel;

/**
 *
 * @author Miqueas
 */
public class RetornaErro {

    // Aqui ele retorna um erro em formato de string de acordo com
    // o seu identificador, ele tambem recebe um token caso ele
    // precise pegar alguma informação, por exemplo, este primeiro
    // "case" retorna a mensagem de erro "Operador inválido + nome do token".
    // A medida que novos erros forem aparecendo, a gente seta um novo "case" pra ele.
    public static String getError(ErrorModel error) {

        switch (error.getCodigo()) {
            case 1:
                return "Esperado um tipo (inteiro, real, string, char ou booleano), mas nenhum token foi encontrado.";
            case 2:
                return "Esperado um tipo (inteiro, real, string, char ou booleano), mas foi encontrado <" + error.getLexema() + ">.";
            case 3:
                return "Esperado token variavel, mas nenhum token foi encontrado.";
            case 4:
                return "Esperado token variavel, mas foi encontrado <" + error.getLexema() + ">.";
            case 5:
                return "Esperado um identificador, mas nenhum token foi encontrado.";
            case 6:
                return "Esperado um identificador, mas foi encontrado <" + error.getLexema() + ">.";
            default:
                return "";
        }

    }

}
