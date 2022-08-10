package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("===시작===");

		Scanner sc = new Scanner(System.in);
		int 마지막번호 = 0;

		List<Article> articles = new ArrayList<>();

		while (true) {
			System.out.printf("명령어)");
			String cmd = sc.nextLine();

			if (cmd.equals("exit")) {
				break;

			}

			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}

			if (cmd.equals("article write")) {
				int 번호 = 마지막번호 + 1;
				마지막번호 = 번호;
				System.out.printf("제목 : ");
				String ss = sc.nextLine();
				System.out.printf("내용 : ");
				String tt = sc.nextLine();

				Article article = new Article(마지막번호, ss, tt);
				articles.add(article);

				System.out.printf("%s번글이 생성되었습니다. \n", 마지막번호);

			} else if (cmd.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다.");
					continue;

				}
				System.out.println("번호    :     제목");
				for (int i = articles.size() - 1; i >= 0 ; i--) {
					Article article = articles.get(i);
					System.out.printf("%d      :      %s\n", article.마지막번호 , article.tt);
				}

			} else {
				System.out.println("존재하지 않는 명령어입니다");
			}

		}

		sc.close();

		System.out.println("===끝===");
	}
}

class Article {
	int 마지막번호;
	String ss;
	String tt;

	Article(int 마지막번호, String ss, String tt) {
		this.마지막번호 = 마지막번호;
		this.tt = tt;
		this.ss = ss;

	}
}
