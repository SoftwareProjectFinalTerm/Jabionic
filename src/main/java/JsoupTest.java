import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JsoupTest {
    /**
     * JsoupTest jsouptest = new JsoupTest();
     * jsouptest(사용자 입력 url);
     * 을 통해 크롤링 문자열을 전처리 한 결과 text를 반환받을 수 있습니다.
     *
     * @param url
     * @return
     */
    //url을 입력받고 네이버뉴스,다음뉴스냐에 따라서 다른 태그로 크롤링함. 본문 내용 String으로 리턴
    //예외 상황 발생시 "error"를 리턴
    String crawling(String url) throws IOException {
        String text = "";
        Elements contents = new Elements();

        Document doc = Jsoup.connect(url).get();

        if (url.contains("naver")) {
            contents = doc.select("#dic_area");
        } else if (url.contains("daum")) {
            contents = doc.select("#harmonyContainer > section");
        }

        text = contents.text();

        return text;
    }

    public ResponseCrawling BionicReading(String text) throws IOException {
        Transfer tf = new Transfer();

        return tf.transfer(text);
    }
}
