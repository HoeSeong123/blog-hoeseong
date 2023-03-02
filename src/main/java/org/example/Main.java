package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = 0;
        HashMap<Integer, WiseSaying> wiseSayings = new HashMap<>();
       // List<WiseSaying> wiseSayings = new ArrayList<>();

        System.out.println("== 명언 앱 ==");

        while(true) {
            System.out.print("명령) ");

            String command = sc.nextLine();

            if (command.equals("등록")) {
                num++;
                WiseSaying wiseSaying = new WiseSaying();

                System.out.print("명언 : ");
                wiseSaying.setContent(sc.nextLine());

                System.out.print("작가 : ");
                wiseSaying.setAuthor(sc.nextLine());

                System.out.println(num + "번 명언이 등록되었습니다.");

                wiseSayings.put(num, wiseSaying);
            }

            if (command.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                for(int i = num; i > 0; i--) {
                    System.out.println(i + " / " + wiseSayings.get(i).getAuthor() + " / " +  wiseSayings.get(i).getContent());
                }
            }

            if (command.contains("삭제?id=")) {
                int id = Integer.parseInt(command.split("=")[1]);
                System.out.println(id + "번 명언이 삭제되었습니다.");
            }
            if (command.equals("종료")) {
                break;
            }
        }
    }
}

class WiseSaying {
    private String content;
    private String author;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}