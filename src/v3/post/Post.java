package v3.post;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Post {
    private final String id;
    private String title;
    private String content;
    private final LocalDateTime createDateTime;
    private LocalDateTime modifyDateTime;

    public Post(String id, String title, String content, LocalDateTime createDateTime, LocalDateTime modifyDateTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDateTime = createDateTime;
        this.modifyDateTime = modifyDateTime;
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

    public void setModifyDateTime(LocalDateTime modifyDateTime) {
        this.modifyDateTime = modifyDateTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초");
        String createFormattedDateTime = createDateTime.format(formatter);
        String modifyFormattedDateTime = modifyDateTime.format(formatter);

        return String.format("작성자: %s, 제목: \"%s\", 내용: \"%s\", 작성일: %s, 수정일: %s",
                            id, title, content, createFormattedDateTime, modifyFormattedDateTime);
    }
}
