
// Adaptado de https://stackoverflow.com/questions/14400946/how-to-change-the-color-of-specific-words-in-a-jtextpane

package editor;

public class HiliteWord {

    int _position;  
    String _word;

    public HiliteWord(String word, int position) {
        _position = position;   
        _word = word;
    }
}
