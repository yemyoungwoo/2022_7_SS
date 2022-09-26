package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.util.Util;

public class App {

	private static List<Article> articles;

	public App() {
		articles = new ArrayList<>();
	}

	public void run() {
		System.out.println("==프로그램 시작==");

		makeTestData();

		Scanner sc = new Scanner(System.in);

		int lastArticleId = 3;

		while (true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine().trim();

			if (cmd.equals("exit")) {
				break;
			}
			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}
			if (cmd.equals("article write")) {
				int id = lastArticleId + 1;
				lastArticleId = id;
				String regDate = Util.getDateStr();
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				Article article = new Article(id, regDate, title, body);
				articles.add(article);

				System.out.printf("%d번 글이 생성되었습니다.\n%s\n", id, regDate);

			} else if (cmd.startsWith("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다");
					continue;
				}
				List<Article> forPrintArticles = articles;
				
				String searchKeyword = cmd.substring("article list".length()).trim();
				if (searchKeyword.length() > 0) {
				System.out.printf("검색어 : %s\n", searchKeyword);

					forPrintArticles = new ArrayList<>();
					
					for (Article article : articles) {
						if (article.title.contains(searchKeyword)) {
							forPrintArticles.add(article);
						}
					}
					if (forPrintArticles.size() == 0) {
						System.out.println("검색결과가 없습니다.");
						continue;
					}
				}
				
				System.out.println("번호    |    제목    |    날짜    |    조회");
				for( int i = forPrintArticles.size() - 1; i >= 0; i--) {
					Article article = forPrintArticles.get(i);
					System.out.printf("%4d    |    %s    |    %s    |    %d\n", article.id, article.title, article.regDate, article.hit);
				}
					
				
			} else if (cmd.startsWith("article detail ")) {
				String[] cmdBits = cmd.split(" ");

				int id = Integer.parseInt(cmdBits[2]);

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}

				foundArticle.increaseHit();

				System.out.printf("번호 : %d\n", foundArticle.id);
				System.out.printf("날짜 : %s\n", foundArticle.regDate);
				System.out.printf("제목 : %s\n", foundArticle.title);
				System.out.printf("내용 : %s\n", foundArticle.body);
				System.out.printf("조회 : %d\n", foundArticle.hit);
			} else if (cmd.startsWith("article modify ")) {
				String[] cmdBits = cmd.split(" ");

				int id = Integer.parseInt(cmdBits[2]);

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				System.out.printf("새 제목 : ");
				String title = sc.nextLine();
				System.out.printf("새 내용 : ");
				String body = sc.nextLine();

				String regDate = Util.getDateStr();
				foundArticle.title = title;
				foundArticle.body = body;

				System.out.printf("%d번 글을 수정했습니다.\n%s\n", id, regDate);

			} else if (cmd.startsWith("article delete ")) {
				String[] cmdBits = cmd.split(" ");

				int id = Integer.parseInt(cmdBits[2]);

				int foundIndex = getArticleIndexById(id);

				if (foundIndex == -1) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}

				articles.remove(foundIndex);
				System.out.printf("%d번 글을 삭제했습니다.\n", id);
			} else {
				System.out.println("존재하지 않는 명령어입니다");
			}

		}

		sc.close();

		System.out.println("==프로그램 끝==");
	}

	private int getArticleIndexById(int id) {
		int i = 0;
		for (Article article : articles) {

			if (article.id == id) {
				return i;
			}
			i++;
		}
		return -1;
	}

	private Article getArticleById(int id) {
		int index = getArticleIndexById(id);

		if (index != -1) {
			return articles.get(index);
		}

		return null;
	}

	private static void makeTestData() {
		System.out.println("테스트를 위한 데이터를 생성합니다.");

		articles.add(new Article(1, Util.getDateStr(), "제목1", "내용1", 10));
		articles.add(new Article(2, Util.getDateStr(), "제목2", "내용2", 20));
		articles.add(new Article(3, Util.getDateStr(), "제목3", "내용3", 30));
	}

}