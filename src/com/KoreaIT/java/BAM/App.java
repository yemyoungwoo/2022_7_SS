package com.KoreaIT.java.BAM;

import java.util.Scanner;

import com.KoreaIT.java.BAM.controller.ArticleController;
import com.KoreaIT.java.BAM.controller.Controller;
import com.KoreaIT.java.BAM.controller.MemberController;

public class App {

	public App() {
	}

	public void run() {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);

		articleController.makeTestData();
		MemberController.makeTestData();
		while (true) {

			System.out.printf("명령어 ) ");
			String cmd = sc.nextLine().trim();
			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}
			if (cmd.equals("exit")) {
				break;
			}
			String[] cmdBits = cmd.split(" "); // article detail
			if (cmdBits.length == 1) {
				System.out.println("명령어를 확인해주세요");
				continue;
			}
			String controllerName = cmdBits[0]; // article
			String actionMethodName = cmdBits[1]; // detail
			Controller controller = null;
			if (controllerName.equals("article")) {
				controller = articleController;
			} else if (controllerName.equals("member")) {
				controller = memberController;
			} else {
				System.out.println("존재하지 않는 명령어입니다.");
				continue;
			}
			controller.doAction(cmd, actionMethodName);
		}
		System.out.println("==프로그램 끝==");
		sc.close();
	}

}
