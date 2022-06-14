import java.util.ArrayList;
import java.util.List;

public class ResponseCrawling {
    String rawText;
    private List<ResponseWord> responseWords = new ArrayList<>();

    public String getRawText() {
        return rawText;
    }

    public void setRawText(String rawText) {
        this.rawText = rawText;
    }

    public List<ResponseWord> getResponseWords() {
        return responseWords;
    }

    public void setResponseWords(List<ResponseWord> responseWords) {
        this.responseWords = responseWords;
    }
}
