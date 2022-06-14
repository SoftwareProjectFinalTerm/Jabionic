import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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

    //url을 입력받고 네이버뉴스,다음뉴스냐에 따라서 다른 태그로 크롤링함. 본문 내용 String으로 리턴
    //예외 상황 발생시 "error"를 리턴
    String classifySite(String url){
        String text = "";
        //네이버일 경우
        if(url.contains("news.naver.com")){
            try {
                //웹에서 내용 가져오기
                Document doc = Jsoup.connect(url).get();
                //내용 중 원하는 부분 가져오기
                Elements contents = doc.select("#dic_area");
                //얻어온 element를 String형태로 변환
                text = contents.text();
                //기사 본문 내용 출력
                System.out.println(text);
                return text;

            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        }
        //다음일 경우
        else if(url.contains("news.v.daum.net")){
            try {
                //웹에서 내용 가져오기
                Document doc = Jsoup.connect(url).get();
                //내용 중 원하는 부분 가져오기
                Elements contents = doc.select("#harmonyContainer > section");
                //얻어온 element를 String형태로 변환
                text = contents.text();
                //기사 본문 내용 출력
                System.out.println(text);
                return text;

            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        }
        else{
            System.out.println("지원되지 않는 사이트");
            return "error";
        }
    }

    public List<ResponseCrawling> crawling(String url) {
        Transfer tf=new Transfer();
        String text = "";
        text=classifySite(url); //본문 내용 가져옴

        //본문내용이 정상적으로 가져왔을시
        if(text!="error"){
            // 결과 확인용 출력 구문
            //for(ResponseCrawling responseCrawling : tf.transfer(text)) {
                //System.out.print(responseCrawling.getResultWord());
                //System.out.println(" / " + responseCrawling.getStressIndex());
            //}
            return tf.transfer(text);
        }
        //url이 잘못되거나 본문 내용 읽을때 오류 발생. 빈 리스트 리턴
        else{
            List<ResponseCrawling> emptyList = Collections.emptyList();
            return emptyList;
        }

    }
}
