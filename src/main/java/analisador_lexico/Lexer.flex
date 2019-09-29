package analisador_lexico;

import model.Token;
import model.TokenModel;


%%

%public
%class Lexer
%line
%column
%type TokenModel

%{

private int id = 0;

private TokenModel createToken(int id, Token nome) {
    return new TokenModel(id, nome, yytext(), yyline, yycolumn);
}

%}

brancos = [\n| |\t]
id = [A-Za-z]*
inteiro = 0|[1-9][0-9]*
real = [0-9]+ \. [0-9]+

%%

{brancos} { /**/ }

/* Pontuação */

"." { id+=1; return createToken(id, Token.PONTO); }
"," { id+=1; return createToken(id, Token.VIRGULA); }
":" { id+=1; return createToken(id, Token.DOIS_PONTOS); }
";" { id+=1; return createToken(id, Token.PONTO_VIRGULA); }
"'" { id+=1; return createToken(id, Token.ASPAS_SIMPLES); }
"(" { id+=1; return createToken(id, Token.ABRE_PARENTESES); }
")" { id+=1; return createToken(id, Token.FECHA_PARENTESES); }

/* Operadores aritméticos */
"+" { id+=1; return createToken(id, Token.OP_ADICAO); }
"-" { id+=1; return createToken(id, Token.OP_SUBTRACAO); }
"*" { id+=1; return createToken(id, Token.OP_MULTIPLICACAO); }
"/" { id+=1; return createToken(id, Token.OP_DIVISAO); }

/* Operadores logicos */
"e" { id+=1; return createToken(id, Token.OP_E); }
"ou" { id+=1; return createToken(id, Token.OP_OU); }
"nao" { id+=1; return createToken(id, Token.OP_NAO); }

/* Operadores relacionais */
"=" { id+=1; return createToken(id, Token.OP_IGUAL); }
"<>" { id+=1; return createToken(id, Token.OP_DIFERENTE); }
">" { id+=1; return createToken(id, Token.OP_MAIOR); }
"<" { id+=1; return createToken(id, Token.OP_MENOR); }
">=" { id+=1; return createToken(id, Token.OP_MAIOR_IGUAL); }
"<=" { id+=1; return createToken(id, Token.OP_MENOR_IGUAL); }

/* Operadores de atribuição */
":=" { id+=1; return createToken(id, Token.OP_ATRIBUICAO); }

/* Palavras chaves */
"programa" { id+=1; return createToken(id, Token.PC_PROGRAMA); }
"inicio" { id+=1; return createToken(id, Token.PC_INICIO); }
"fim" { id+=1; return createToken(id, Token.PC_FIM); }
"variavel" { id+=1; return createToken(id, Token.PC_VARIAVEL); }
"constante" { id+=1; return createToken(id, Token.PC_CONSTANTE); }
"se" { id+=1; return createToken(id, Token.PC_SE); }
"entao" { id+=1; return createToken(id, Token.PC_ENTAO); }
"senao" { id+=1; return createToken(id, Token.PC_SENAO); }
"enquanto" { id+=1; return createToken(id, Token.PC_ENQUANTO); }
"faca" { id+=1; return createToken(id, Token.PC_FACA); }
"repita" { id+=1; return createToken(id, Token.PC_REPITA); }
"ate que" { id+=1; return createToken(id, Token.PC_ATE_QUE); }
"para" { id+=1; return createToken(id, Token.PC_PARA); }
"ate" { id+=1; return createToken(id, Token.PC_ATE); }
"inteiro" { id+=1; return createToken(id, Token.PC_INTEIRO); }
"real" { id+=1; return createToken(id, Token.PC_REAL); }
"string" { id+=1; return createToken(id, Token.PC_STRING); }
"char" { id+=1; return createToken(id, Token.PC_CHAR); }
"booleano" { id+=1; return createToken(id, Token.PC_BOOLEANO); }
"leia" { id+=1; return createToken(id, Token.PC_LEIA); }
"escreva" { id+=1; return createToken(id, Token.PC_ESCREVA); }

{inteiro} { id+=1; return createToken(id, Token.INTEIRO); }
{real} { id+=1; return createToken(id, Token.REAL); }
{id} { id+=1; return createToken(id, Token.IDENTIFICADOR); }
. { throw new RuntimeException("Caractere inválido " + yytext() + " na linha " + yyline + ", coluna " +yycolumn); }

