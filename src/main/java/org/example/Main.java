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
        JSONObject jsonObject;

        // JSON 파일 읽기
        Reader reader = new FileReader("src/main/java/org/example/data.json");
        try {
            jsonObject = (JSONObject) parser.parse(reader);
        } catch(Exception e) {
            jsonObject = new JSONObject();
        }

        long num;
        try {
            num = (long) jsonObject.get("num");
        } catch(NullPointerException e) {
            num = 0;
        }
        // List<WiseSaying> wiseSayings = new ArrayList<>();

        System.out.println("== 명언 앱 ==");

        while(true) {
            System.out.print("명령) ");
            String command = sc.nextLine();


            if (command.equals("등록")) {
                JSONObject jObject = new JSONObject();
                num++;

                System.out.print("명언 : ");
                jObject.put("content", sc.nextLine());

                System.out.print("작가 : ");
                jObject.put("author", sc.nextLine());

                System.out.println(num + "번 명언이 등록되었습니다.");

                jsonObject.put(Long.toString(num), jObject);
            }

            if (command.equals("목록")) {
                List<String> keySet = new ArrayList<>(jsonObject.keySet());
                Collections.reverse(keySet);

                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");

                for(String key : keySet) {
                    if(key.equals("num")) continue;
                    System.out.println(key + " / " + ((HashMap)jsonObject.get(key)).get("author") + " / " + ((HashMap)jsonObject.get(key)).get("content"));
                }
            }

            if (command.contains("삭제?id=")) {
                String id = command.split("=")[1];

                if(jsonObject.containsKey(id) != true) {
                    System.out.println(id + "번 명령이 존재하지 않습니다.");
                } else {
                    System.out.println(id + "번 명언이 삭제되었습니다.");
                    jsonObject.remove(id);
                }
            }

            if (command.contains("수정?id=")) {
                String id = command.split("=")[1];
                JSONObject jObject = new JSONObject();

                System.out.println("명언(기존) : " + ((HashMap)jsonObject.get(id)).get("content"));
                System.out.print("명언 : ");
                jObject.put("content", sc.nextLine());

                System.out.println("작가(기존) : " +((HashMap)jsonObject.get(id)).get("author"));
                System.out.print("작가 : ");
                jObject.put("author", sc.nextLine());

                jsonObject.put(id, jObject);
            }

            if (command.equals("종료")) {
                jsonObject.put("num", num);
                try {
                    FileWriter file = new FileWriter("src/main/java/org/example/data.json");
                    file.write(jsonObject.toString());
                    file.flush();
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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