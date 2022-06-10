import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JsoupTest {
    public static void main(String[] args) {
        Transfer tf = new Transfer();
        String text = "";
        try{
            //(네이버 뉴스기사 전용)url입력
            String url="https://n.news.naver.com/mnews/article/293/0000039337?sid=105";
            //웹에서 내용 가져오기
            Document doc= Jsoup.connect(url).get();
            //내용 중 원하는 부분 가져오기
            Elements contents=doc.select("#dic_area");
            //얻어온 element를 String형태로 변환
            text=contents.text();

            //기사 본문 내용 출력
            System.out.println(text);

        }catch (IOException e){
            e.printStackTrace();
        }

        text = tf.getDeletedSpaceStr(text);

        for(char[] word : tf.transfer(text)) {
            System.out.print(word);
            System.out.print(" ");
            System.out.println(word[tf.getIndexOfStress(word)]);
        }
    }
}
