package v3.main;

import v3.member.Member;
import v3.post.Post;

import java.time.LocalDateTime;
import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Map<String, Member> memberList = new HashMap<>();
    static List<Post> postList = new ArrayList<>();
    static Member currentMember = null;

    public static void main(String[] args) {
        while (true) {
            printScreen1();
            printScreen2();
        }
    }

    private static void printScreen1() {
        System.out.println("< Welcome to \"How Are You?\" >");

        while (true) {
            System.out.println("-------------------");
            System.out.println("1.로그인 | 2.회원가입");
            System.out.println("-------------------");
            System.out.print(">> ");
            int input = getInputAsInt();
            if (input == -1) {
                continue;
            }

            if (input == 1) {
                if (Login())
                    break;
            } else if (input == 2) {
                SignUp();
            } else {
                printInputErrorMsg();
            }
        }
    }

    private static boolean  Login() {
        System.out.println(">> 로그인 화면 <<");

        System.out.print("ID: ");
        String id = sc.nextLine();
        if (!memberList.containsKey(id)) {
            System.out.println("존재하지 않는 아이디입니다.\n");
            return false;
        }

        System.out.print("PW: ");
        String pw = sc.nextLine();
        if (!memberList.get(id).getPw().equals(pw)) {
            System.out.println("비밀번호가 일치하지 않습니다.\n");
            return false;
        }

        currentMember = memberList.get(id);
        System.out.println(">> 로그인 성공 <<\n");
        return true;
    }

    private static void SignUp() {
        System.out.println(">> 회원가입 화면 <<");

        String name = getValidName();
        String id = getValidId();
        String pw = getValidPw();

        memberList.put(id, new Member(name, id, pw));
        System.out.println(">> 회원가입 성공 <<\n");
    }

    private static String getValidName() {
        String name;
        while (true) {
            System.out.print("name: ");
            name = sc.nextLine();
            if (!name.matches("[가-힣]+")) {
                System.out.println("이름을 한글로 입력해 주세요.");
                continue;
            }
            break;
        }
        return name;
    }

    private static String getValidId() {
        String id;
        while (true) {
            System.out.print("ID: ");
            id = sc.nextLine();
            if (memberList.containsKey(id)) {
                System.out.println("사용할 수 없는 아이디입니다.");
                continue;
            }
            if (!(id.matches("[a-z0-9]+"))) {
                System.out.println("아이디는 소문자+숫자만 가능합니다.");
                continue;
            }
            if (5 > id.length() || id.length() > 15) {
                System.out.println("아이디는 5 ~ 15자리까지 가능합니다.");
                continue;
            }
            break;
        }
        return id;
    }

    private static String getValidPw() {
        String pw;
        while (true) {
            System.out.print("PW: ");
            pw = sc.nextLine();
            System.out.print("PW Check: ");
            String pwCheck = sc.nextLine();
            if (!pw.equals(pwCheck)) {
                System.out.println("비밀번호 확인이 일치하지 않습니다.");
                continue;
            }
            break;
        }
        return pw;
    }

    private static void printScreen2() {
        System.out.printf("ID: %s님이 접속하셨습니다.\n", currentMember.getId());

        while (true) {
            System.out.println("------------------------------------------------------------------");
            System.out.println("1.게시글 작성 | 2.게시글 목록 | 3.게시글 수정 | 4.게시글 삭제 | 5.로그아웃");
            System.out.println("------------------------------------------------------------------");
            System.out.print(">> ");
            int input = getInputAsInt();
            if (input == -1) {
                continue;
            }

            switch (input) {
                case 1 -> createPost();
                case 2 -> viewPosts();
                case 3 -> modifyPost();
                case 4 -> deletePost();
                case 5 -> {
                    currentMember = null;
                    System.out.println(">> 로그아웃 성공 <<\n");
                    return;
                }
                default -> printInputErrorMsg();
            }
        }
    }

    private static void createPost() {
        System.out.println(">> 게시글 작성 화면 <<");

        System.out.print("제목: ");
        String title = sc.nextLine();

        System.out.print("내용: ");
        String content = sc.nextLine();

        postList.add(new Post(currentMember.getId(), title, content, LocalDateTime.now(), LocalDateTime.now()));
        System.out.println(">> " + postList.size() + "번째 게시글 등록 성공 <<\n");
    }

    private static void viewPosts() {
        if (checkPostListIsEmpty()) {
            return;
        }

        System.out.println("1. 전체 게시글 목록 보기");
        System.out.println("2. 내가 작성한 게시글 목록 보기");
        System.out.print(">> ");
        int input2 = getInputAsInt();
        if (input2 == -1) {
            return;
        }

        if (input2 == 1) {
            System.out.println(">> 전체 게시글 목록 화면 <<");
            for (int i = postList.size() - 1; i >= 0; i--) {
                System.out.println(i + 1 + ") " + postList.get(i));
            }

        } else if (input2 == 2) {
            System.out.println(">> 내가 작성한 게시글 목록 화면 <<");
            for (int i = postList.size() - 1; i >= 0; i--) {
                if (postList.get(i).getId().equals(currentMember.getId())) {
                    System.out.println(i + 1 + ") " + postList.get(i));
                }
            }

        } else {
            printInputErrorMsg();
            return;
        }

        System.out.println();
    }

    private static void modifyPost() {
        if (checkPostListIsEmpty()) {
            return;
        }

        System.out.println("몇 번째 게시글을 수정하시겠습니까?");
        System.out.print(">> ");
        int input2 = getInputAsInt();
        if (input2 < 1 || postList.size() < input2) {
            printInputErrorMsg();
            return;
        }

        if (!postList.get(input2-1).getId().equals(currentMember.getId())) {
            System.out.println("다른 사람이 작성한 게시글은 수정할 수 없습니다.\n");
            return;
        }

        System.out.println(">> 게시글 수정 화면 <<");
        Post post = postList.get(input2-1);

        System.out.print("제목(수정): ");
        post.setTitle(sc.nextLine());

        System.out.print("내용(수정): ");
        post.setContent(sc.nextLine());

        post.setModifyDateTime(LocalDateTime.now());

        System.out.println(">> 게시글 수정 성공 <<\n");
    }

    private static void deletePost() {
        if (checkPostListIsEmpty()) {
            return;
        }

        System.out.println("몇 번째 게시글을 삭제하시겠습니까?");
        System.out.print(">> ");
        int input2 = getInputAsInt();
        if (input2 < 1 || postList.size() < input2) {
            printInputErrorMsg();
            return;
        }

        if (!postList.get(input2-1).getId().equals(currentMember.getId())) {
            System.out.println("다른 사람이 작성한 게시글은 삭제할 수 없습니다.\n");
            return;
        }

        postList.remove(input2 - 1);
        System.out.println(">> 게시글 삭제 성공 <<\n");
    }

    private static boolean checkPostListIsEmpty() {
        if (postList.isEmpty()) {
            System.out.println("등록된 게시글이 없습니다.\n");
            return true;
        }
        return false;
    }

    private static int getInputAsInt() {
        int input;

        try {
            input = sc.nextInt();
        } catch (RuntimeException e) {
            printInputErrorMsg();
            return -1;
        } finally {
            sc.nextLine();
        }

        return input;
    }

    private static void printInputErrorMsg() {
        System.out.println("잘못된 입력입니다.\n");
    }
}
