package v3.member;

public class Member {
    private final String name;
    private final String id;
    private String pw;

    public Member(String name, String id, String pw) {
        this.name = name;
        this.id = id;
        this.pw = pw;
    }

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }
}
