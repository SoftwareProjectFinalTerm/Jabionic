public class ResponseCrawling {
    private char[] resultWord;
    private int stressIndex;

    public ResponseCrawling(char[] resultWord, int stressIndex) {
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
