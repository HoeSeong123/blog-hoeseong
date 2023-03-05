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
            Rq rq = new Rq(command);

            switch (rq.getActionCode()) {
                case "종료" :
                    systemController.exit();
                    return;
                case "등록" :
                    wiseSayingController.write();
                    break;
                case "목록" :
                    wiseSayingController.list();
                    break;
                case "삭제" :
                    wiseSayingController.remove();
                    break;
            }
        }
    }
}
