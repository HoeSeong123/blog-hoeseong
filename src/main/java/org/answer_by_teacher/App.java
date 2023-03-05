package org.answer_by_teacher;


import org.answer_by_teacher.System.controller.SystemController;
import org.answer_by_teacher.wiseSaying.controller.WiseSayingController;
import org.answer_by_teacher.wiseSaying.entity.WiseSaying;

import java.util.*;

public class App {
    public void run() {
        System.out.println("== 명언 앱 ==");



        SystemController systemController = new SystemController();
        WiseSayingController wiseSayingController = new WiseSayingController();

        while(true) {
            System.out.print("명령) ");
            String command = Container.getScanner().nextLine().trim();

            if (command.equals("종료")) {
                systemController.exit();
                break;

            } else if (command.equals("등록")) {
                wiseSayingController.write();
            } else if (command.equals("목록")) {
                wiseSayingController.list();
            } else if (command.startsWith("삭제")) {
                Rq rq = new Rq(command);

                System.out.printf("actionCode : %s\n", rq.getActionCode());
                System.out.printf("params.id : %s\n", rq.getParam("id"));
                System.out.printf("params.authorName : %s\n", rq.getParam("authorName"));
                System.out.printf("params.content : %s\n", rq.getParam("content"));
            }
        }
    }
}
