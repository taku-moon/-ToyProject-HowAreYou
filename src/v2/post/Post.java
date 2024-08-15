package v2.post;

public class Post {
    private final String id;
    private String title;
    private String content;

    public Post(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format("작성자: %s, 제목: \"%s\", 내용: \"%s\"", id, title, content);
    }
}
