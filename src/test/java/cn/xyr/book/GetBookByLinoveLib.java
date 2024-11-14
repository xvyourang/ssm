package cn.xyr.book;

import org.aspectj.util.FileUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 从bili轻小说爬取小说
 */
public class GetBookByLinoveLib {
    public static void main(String[] args) throws Exception {
        String url = "https://www.linovelib.com/novel/3523/177722.html";
//        String url ="https://www.baidu.com";
        Document document = null;
        for (int i = 0; i < 100; i++) {
            try {
                document = Jsoup.connect(url).get();
                Element body = document.body();
                System.out.println(body);
                break;
            } catch (java.net.ConnectException e) {
                System.out.println(i);
            }
        }
        System.out.println(document);
    }

    @Test
    public void test00() throws Exception {
        String html = FileUtil.readAsString(new File("C:\\Users\\admin\\Desktop\\1.txt"));
        Document document = Jsoup.parse(html);
        List<String> img = getImg(document);
        System.out.println(img);
    }

    /**
     * 获取内容a
     *
     * @param document
     */
    private static String getText(Document document) {
        StringBuffer sb = new StringBuffer();
        Element body = document.body();
        Element mlfy_main_text = body.getElementById("mlfy_main_text");
        Elements elements = mlfy_main_text.getElementsByTag("h1");
        Element h1 = elements.get(0);
        String title = h1.text();
        sb.append(title).append(System.lineSeparator());
        Element textContent = body.getElementById("TextContent");

        Elements ps = textContent.getElementsByTag("p");
        ps.forEach(e -> {
            sb.append(e.text()).append(System.lineSeparator());
        });
        return sb.toString();

    }

    private static List<String> getImg(Document document) {
        List<String> result=new ArrayList<>();
        Element body = document.body();
        Element textContent = body.getElementById("TextContent");
        Elements ps = textContent.getElementsByTag("img");
        ps.forEach(e -> {
            result.add(e.attr("data-src"));
        });
        return result;

    }
    /**
     * 获取下一页的url
     *
     * @return
     */
    private static String getNextPage(Document document) {
        Element body = document.body();
        Elements mlfy_page = body.getElementsByClass("mlfy_page");
        Element element = mlfy_page.get(0);
        Elements allElements = element.getAllElements();
        Optional<Element> first = allElements.stream().filter(e ->
                "下一章".equals(e.text())
        ).findFirst();
        if (first.isPresent()) {
            return first.get().attr("href");
        }
        throw new RuntimeException("1");
    }

}
