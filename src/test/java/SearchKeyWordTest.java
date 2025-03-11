import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class SearchKeyWordTest {
    public static void main(String[] args) {
        List<Article> articles = new ArrayList<>();

        IntStream.rangeClosed(1,5)
                .forEach(i->articles.add(new Article(i, "제목" + i, "내용" + i)));

        articles.add(new Article(6,"자바는 재밌습니까?", "자바를 처음 공부하는데..."));
        articles.add(new Article(7,"코딩 실력 빨리 늘려면 어떻게?", "코딩 실력이 빨리 늘고 싶은데..."));

        String searchKeyWord = "코딩";

//        articles.stream()
//                .filter(article-> article.subject.contains(searchKeyWord))
//                .forEach(System.out::println);
//        for(Article article : articles){
//            if(article.subject.contains(searchKeyWord)){
//                System.out.println(article);
//            }
//        }
        List<Article> filteredArticles = articles.stream()
                .filter(article-> article.subject.contains(searchKeyWord))
                .toList();
        System.out.println(filteredArticles);
    }
}
class Article {
    int id;
    String subject;
    String content;

    Article(int id, String subject, String content) {
        this.id = id;
        this.subject = subject;
        this.content = content;
    }

    @Override
    public String toString() {
        return "{id: %d, subject: \"%s\", content: \"%s\"}".formatted(id, subject, content);
    }
}