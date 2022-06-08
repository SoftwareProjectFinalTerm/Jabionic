import java.util.ArrayList;
import java.util.List;

public class Transfer {
    private String[] words;
    private List<char[]> returnWords = new ArrayList<>();

    /**
     * 크롤링 한 문자열 전체를 인자로 넘기면, 각 단어를 문자 배열 형태로 바꾸어 리스트에 저장
     * ex) apple banana -> <['a', 'p', 'p', 'l', 'e'], ['b', 'a', 'n', 'a', 'n', 'a']>
     *
     * @param text 크롤링 해 온 문자열 데이터
     * @return 문자배열 형태로 구성된 리스트
     **/
    public List<char[]> transfer(String text) {
        words = text.trim().split(" ");     // 띄어쓰기를 기준으로 단어 구분

        for (String word : words) {
            if(word.equals("")) {                   // 공백은 제거
                continue;
            }
            returnWords.add(word.trim().toCharArray());
        }

        return returnWords;
    }

    /**
     * 문자열에서 '(,)'를 포함하여 그 사이에 있는 내용을 삭제
     * @param text 문자열
     * @return () 삭제 된 문자열
     **/
    public String getDeletedSpaceStr(String text) {
        int start, end, length = 0;

        while(text.contains("(")) {
            start = text.indexOf('(');
            end = text.indexOf(')');
            length = text.length();

            text = text.substring(0, start) + text.substring(end + 1, length);
        }

        return text;
    }
}
