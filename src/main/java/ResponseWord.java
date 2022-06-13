public class ResponseWord {
    private char[] resultWord;
    private int stressIndex;

    public ResponseWord(char[] resultWord, int stressIndex) {
        this.resultWord = resultWord;
        this.stressIndex = stressIndex;
    }

    public char[] getResultWord() {
        return resultWord;
    }

    public int getStressIndex() {
        return stressIndex;
    }
}
