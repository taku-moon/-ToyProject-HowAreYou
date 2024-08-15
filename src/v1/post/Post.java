package v1.post;

public class Post {
    String id;
    String title;
    String content;

    public Post(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }



    @Override
    public String toString() {
        return String.format("작성자: %s\n제목: %s\n내용: %s", id, title, content);
    }
}
