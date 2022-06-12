import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class JsoupTest {

    Transfer tf = new Transfer();

    /**
     * JsoupTest jsouptest = new JsoupTest();
     * jsouptest(사용자 입력 url);
     * 을 통해 크롤링 문자열을 전처리 한 결과 text를 반환받을 수 있습니다.
     *
     * @param url
     * @return
     */
    public List<ResponseCrawling> crawling(String url) {
        String text = "";
        try {
            //웹에서 내용 가져오기
            Document doc = Jsoup.connect(url).get();
            //내용 중 원하는 부분 가져오기
            Elements contents = doc.select("#dic_area");
            //얻어온 element를 String형태로 변환
            text = contents.text();

            //기사 본문 내용 출력
            System.out.println(text);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // 결과 확인용 출력 구문
        for(ResponseCrawling responseCrawling : tf.transfer(text)) {
            System.out.print(responseCrawling.getResultWord());
            System.out.println(" / " + responseCrawling.getStressIndex());
        }

        return tf.transfer(text);
    }
}
