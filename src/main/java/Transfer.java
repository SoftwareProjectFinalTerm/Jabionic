import java.util.ArrayList;
import java.util.List;

public class Transfer {

    /**
     * 크롤링 한 문자열 전체를 인자로 넘기면, 각 단어를 문자 배열 형태로 바꾸어 리스트에 저장
     * ex) apple banana -> <['a', 'p', 'p', 'l', 'e'], ['b', 'a', 'n', 'a', 'n', 'a']>
     *
     * @param text 크롤링 해 온 문자열 데이터
     * @return 문자배열 형태로 구성된 리스트
     */
    public ResponseCrawling transfer(String text) {
        String[] words;
        List<ResponseWord> responseWords = new ArrayList<>();
        ResponseCrawling responseCrawling = new ResponseCrawling();

        words = getDeletedSpaceStr(text).trim().split(" ");     // 띄어쓰기를 기준으로 단어 구분

        for (String word : words) {
            if(word.equals("")) {                  // 공백은 제거
                continue;
            }
            char[] charArray = word.trim().toCharArray();
            responseWords.add(new ResponseWord(charArray, getIndexOfStress(charArray)));
        }

        responseCrawling.setResponseWords(responseWords);
        responseCrawling.setRawText(text);

        return responseCrawling;
    }

    /**
     * 문자열에서 '(,)'를 포함하여 그 사이에 있는 내용을 삭제
     * @param text 문자열
     * @return () 삭제 된 문자열
     */
    private String getDeletedSpaceStr(String text) {
        int start, end, length = 0;

        while(text.contains("(")) {
            start = text.indexOf('(');
            end = text.indexOf(')');
            length = text.length();

            text = text.substring(0, start) + text.substring(end + 1, length);
        }

        return text;
    }

    /**
     * 강조할 글자의 index를 반환
     * 2글자 이하면 앞글자
     * 5글자 이상이면 3번째 글자
     * 그 외는 가운데, ex) 바'나'나, 워터'멜'론
     * @param word 문자배열로 이루어진 단어
     * @return 강조할 음절의 index
     */
    private int getIndexOfStress(char[] word) {
        int length = word.length;

        if(length <= 2) {
            return 0;
        } else if(length > 4) {
            return 2;
        }
        return length / 2;
    }
}
