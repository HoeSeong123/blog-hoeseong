package org.answer_by_teacher.wiseSaying.entity;

public class WiseSaying {
    private long id;
    private String content;
    private String authorName;

    public WiseSaying(long id, String content, String author) {
        this.id = id;
        this.content = content;
        this.authorName = author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
