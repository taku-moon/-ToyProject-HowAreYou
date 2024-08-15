package v1.main;

import v1.post.Post;

import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Map<String, Integer> member = new HashMap<>();
    static String loginId;
    static List<Post> postList = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            showScreen1();
            showScreen2();
        }
    }

    private static void showScreen1() {
        System.out.println("~.~.~ Welcome to \"How Are You?\" ~.~.~");

        while (true) {
            System.out.println("1. 로그인");
            System.out.println("2. 회원가입");
            System.out.print(">> ");
            int input = sc.nextInt();
            sc.nextLine();

            if (input == 1) {
                System.out.println("로그인 화면입니다");
                System.out.print("ID: ");
                String id = sc.nextLine();
                System.out.print("PW: ");
                int pw = sc.nextInt();

                if (!member.containsKey(id)) {
                    System.out.println("일치하는 아이디가 없습니다.");
                    continue;
                }
                if (member.get(id) != pw) {
                    System.out.println("잘못된 비밀번호입니다.");
                    continue;
                }
                if (member.containsKey(id) && member.get(id) == pw) {
                    System.out.println("로그인 성공");
                    loginId = id;
                    break;
                }

            } else if (input == 2) {
                System.out.println("회원가입 화면입니다");
                System.out.print("ID: ");
                String id = sc.nextLine();
                System.out.print("PW: ");
                int pw = sc.nextInt();

                member.put(id, pw);
                System.out.println("회원가입이 완료 되었습니다");

            } else {
                System.out.println("잘못된 입력입니다");
            }
        }
    }

    private static void showScreen2() {
        System.out.printf("ID: %s님이 접속하셨습니다\n", loginId);
        while (true) {
            System.out.println("1. 게시글 작성");
            System.out.println("2. 게시글 목록");
            System.out.println("3. 게시글 수정");
            System.out.println("4. 게시글 삭제");
            System.out.println("5. 로그아웃");
            System.out.print(">> ");
            int input = sc.nextInt();
            sc.nextLine();

            if (input == 1) {
                System.out.println("게시글 작성 화면입니다");
                System.out.print("제목: ");
                String title = sc.nextLine();
                System.out.print("내용: ");
                String content = sc.nextLine();

                Post post = new Post(loginId, title, content);
                postList.add(post);
                System.out.println("게시글이 작성 되었습니다.");

            } else if (input == 2) {
                System.out.println("게시글 목록 화면입니다");
                for (Post post : postList) {
                    System.out.println(post);
                }

            } else if (input == 3) {
                System.out.print("몇 번째 게시글을 수정하시겠습니까? ");
                int idx = sc.nextInt();
                sc.nextLine();
                Post post = postList.get(idx - 1);
                System.out.println("게시글 수정 화면입니다");
                System.out.print("제목: ");
                String title = sc.nextLine();
                post.setTitle(title);
                System.out.print("내용: ");
                String content = sc.nextLine();
                post.setContent(content);
                System.out.println("게시글이 수정되었습니다.");

            } else if (input == 4) {
                System.out.println("게시글 삭제 화면입니다");
                System.out.print("몇 번째 게시글을 삭제하시겠습니까? ");
                int idx = sc.nextInt();
                sc.nextLine();
                postList.remove(idx - 1);
                System.out.println("게시글이 삭제되었습니다.");

            } else if (input == 5) {
                loginId = null;
                System.out.println("로그아웃 되었습니다.");
                break;
            } else {
                System.out.println("잘못된 입력입니다");
            }
        }
    }
}
