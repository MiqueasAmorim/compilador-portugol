package editor;



// Adaptado de https://stackoverflow.com/questions/14400946/how-to-change-the-color-of-specific-words-in-a-jtextpane

import java.util.ArrayList;
import java.util.List;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;

public class KeywordStyledDocument extends DefaultStyledDocument {

    private static final long serialVersionUID = 1L;
    private Style _defaultStyle;
    private Style _cwStyle;
    
    private ArrayList<String> keywords;

    public KeywordStyledDocument(Style defaultStyle, Style cwStyle) {
        _defaultStyle = defaultStyle;
        _cwStyle = cwStyle;
        keywords = new ArrayList<>();
        keywords.add("programa");
        keywords.add("inicio");
        keywords.add("fim");
        keywords.add("variavel");
        keywords.add("constante");
        keywords.add("se");
        keywords.add("entao");
        keywords.add("senao");
        keywords.add("enquanto");
        keywords.add("faca");
        keywords.add("repita");
        keywords.add("ate que");
        keywords.add("para");
        keywords.add("ate");
        keywords.add("inteiro");
        keywords.add("real");
        keywords.add("string");
        keywords.add("char");
        keywords.add("booleano");
        keywords.add("leia");
        keywords.add("escreva");
        keywords.add("verdadeiro");
        keywords.add("falso");
        keywords.add("vetor");
        keywords.add("de");
        keywords.add("funcao");
        keywords.add("procedimento");
        keywords.add("caso");
        keywords.add("pare");
        keywords.add("continua");
        keywords.add("resto");
        keywords.add("quociente");
        keywords.add("e");
        keywords.add("ou");
        keywords.add("nao");
        
    }

    public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
        str = str.replaceAll("\t", "    ");
        super.insertString(offset, str, a);   
        refreshDocument();
    }

    public void remove(int offs, int len) throws BadLocationException {
        super.remove(offs, len);
        refreshDocument();
    }

    private synchronized void refreshDocument() throws BadLocationException {
        String text = getText(0, getLength());
        final List<HiliteWord> list = processWords(text);

        setCharacterAttributes(0, text.length(), _defaultStyle, true);
        for (HiliteWord word : list) {
            int p0 = word._position;
            setCharacterAttributes(p0, word._word.length(), _cwStyle, true);
        }
    }

    private List<HiliteWord> processWords(String content) {
        content += " ";
        List<HiliteWord> hiliteWords = new ArrayList<HiliteWord>();
        int lastWhitespacePosition = 0;
        String word = "";
        char[] data = content.toCharArray();

        for (int index = 0; index < data.length; index++) {
            char ch = data[index];
            if (!(Character.isLetter(ch) || Character.isDigit(ch) || ch == '_')) {
                lastWhitespacePosition = index;
                if (word.length() > 0) {
                    if (isReservedWord(word)) {
                        hiliteWords.add(new HiliteWord(word, (lastWhitespacePosition - word.length())));
                    }
                    word = "";
                }
            } else {
                word += ch;
            }
        }
        return hiliteWords;
    }
    
    private final boolean isReservedWord(String word) {
        for (String kw: keywords) {
            if (kw.equals(word))
                return true;
        }
        return false;
    }

}
