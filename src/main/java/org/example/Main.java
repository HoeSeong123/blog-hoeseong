package org.example;

import java.io.*;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException, RuntimeException {
        Scanner sc = new Scanner(System.in);

        JSONParser parser = new JSONParser();
        JSONArray jsonArray;

        // JSON 파일 읽기
        Reader reader = new FileReader("src/main/java/org/example/data.json");
        try {
            jsonArray = (JSONArray) parser.parse(reader);
        } catch (Exception e) {
            jsonArray = new JSONArray();
        }

        List<HashMap> wiseSayings = jsonArray;

        long num;
        try {
            num = (long) ((HashMap) jsonArray.get(jsonArray.size() - 1)).get("id");
        } catch (Exception e) {
            num = 0;
        }

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String command = sc.nextLine();

            if (command.equals("등록")) {
                JSONObject jObject = new JSONObject();
                num++;
                jObject.put("id", num);

                System.out.print("명언 : ");
                jObject.put("content", sc.nextLine());

                System.out.print("작가 : ");
                jObject.put("author", sc.nextLine());

                System.out.println(num + "번 명언이 등록되었습니다.");

                wiseSayings.add(jObject);
            }

            if (command.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");

                for (int i = wiseSayings.size(); i > 0; i--) {
                    System.out.println(wiseSayings.get(i - 1).get("id") + " / " + wiseSayings.get(i - 1).get("author") + " / " + wiseSayings.get(i - 1).get("content"));
                }
            }

            if (command.contains("삭제?id=")) {
                Long id = Long.parseLong(command.split("=")[1]);

                for (int i = 0; i < wiseSayings.size(); i++) {
                    if (wiseSayings.get(i).get("id")==id) {
                        wiseSayings.remove(wiseSayings.get(i));
                        System.out.println(id + "번 명언이 삭제되었습니다.");
                        break;
                    }
                    else if(i == wiseSayings.size() - 1) System.out.println(id + "번 명언이 존재하지 않습니다.");
                }

            }

            if (command.contains("수정?id=")) {
                Long id = Long.parseLong(command.split("=")[1]);
                JSONObject jObject = new JSONObject();

                for (int i = 0; i < wiseSayings.size(); i++) {
                    if (wiseSayings.get(i).get("id")==id) {
                        System.out.println("명언(기존) : " + wiseSayings.get(i).get("content"));
                        System.out.print("명언 : ");
                        wiseSayings.get(i).put("content", sc.nextLine());

                        System.out.println("작가(기존) : " + wiseSayings.get(i).get("author"));
                        System.out.print("작가 : ");
                        wiseSayings.get(i).put("author", sc.nextLine());
                    }
                }
            }

            if(command.equals("빌드")) {
                try {
                    FileWriter file = new FileWriter("src/main/java/org/example/data.json");
                    file.write(wiseSayings.toString());
                    file.flush();
                    file.close();
                    System.out.println("data.json 파일의 내용이 갱신되었습니다.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
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