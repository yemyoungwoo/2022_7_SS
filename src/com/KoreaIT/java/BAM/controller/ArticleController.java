package com.KoreaIT.java.BAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.dto.Member;
import com.KoreaIT.java.AM.util.Util;

public class ArticleController extends Controller {
	private Scanner sc;
	private List<Article> articles;
	private String cmd;
	private String actionMethodName;

	public ArticleController(Scanner sc) {
		this.sc = sc;

		articles = new ArrayList<>();
	}

	public void doAction(String cmd, String actionMethodName) {
		this.cmd = cmd;
		this.actionMethodName = actionMethodName;

		switch (actionMethodName) {
		case "list":
			showList();
			break;
		
		case "write":
			if(isLogined() == false) {
				System.out.println("로그인이 필요합니다.");
				break;
			}
			doWrite();
			break;
		
		case "detail":
			showDetail();
			break;
		
		case "modify":
			if(isLogined() == false) {
				System.out.println("로그인이 필요합니다.");
				break;
			}
			doModify();
			break;
		
		case "delete":
			if(isLogined() == false) {
				System.out.println("로그인이 필요합니다.");
				break;
			}
			doDelete();
			break;
		
		default:
			System.out.println("존재하지 않는 명령어입니다");
			break;
		}
	}

	private void doWrite() {
		
		int id = articles.size() + 1;
		
		String regDate = Util.getDateStr();
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();
		Article article = new Article(id, regDate, title, body);
		articles.add(article);
		System.out.printf("%d번 글이 생성되었습니다\n", id);

	}

	private void showList() {
		if (articles.size() == 0) {
			System.out.println("게시물이 없습니다");
			return;
		}
		String searchKeyword = cmd.substring("article list".length()).trim();
		System.out.printf("검색어 : %s\n", searchKeyword);
		List<Article> forPrintArticles = articles;
		if (searchKeyword.length() > 0) {
			forPrintArticles = new ArrayList<>();
			for (Article article : articles) {
				if (article.title.contains(searchKeyword)) {
					forPrintArticles.add(article);
				}
			}
			if (forPrintArticles.size() == 0) {
				System.out.println("검색 결과가 없습니다");
				return;
			}
		}
		System.out.printf("번호    |   제목   |   	  %7s        |   조회\n", "날짜");
		for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
			Article article = forPrintArticles.get(i);
			System.out.printf("%7d | %6s   | %5s   | %5d\n", article.id, article.title, article.regDate, article.hit);
		}

	}

	private void showDetail() {
		String[] cmdBits = cmd.split(" ");

		if (cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}
		int id = Integer.parseInt(cmdBits[2]);
		Article foundArticle = getArticleById(id);
		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 없습니다\n", id);
			return;
		}
		foundArticle.increaseHit();
		System.out.printf("번호 : %d\n", foundArticle.id);
		System.out.printf("날짜 : %s\n", foundArticle.regDate);
		System.out.printf("제목 : %s\n", foundArticle.title);
		System.out.printf("내용 : %s\n", foundArticle.body);
		System.out.printf("조회 : %d\n", foundArticle.hit);

	}

	private void doModify() {
		String[] cmdBits = cmd.split(" ");
		

		if (cmdBits.length == 0) {
			System.out.println("명령어를 확인해주세요");
			return;
			
		}
		int id = Integer.parseInt(cmdBits[2]);
		Article foundArticle = getArticleById(id);
		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 없습니다\n", id);
			return;
		}
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();
		foundArticle.title = title;
		foundArticle.body = body;
		System.out.printf("%d번 게시물을 수정했습니다\n", id);

	}

	private void doDelete() {
		String[] cmdBits = cmd.split(" ");

		if (cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}
		int id = Integer.parseInt(cmdBits[2]);
		int foundIndex = getArticleIndexById(id);
		if (foundIndex == -1) {
			System.out.printf("%d번 게시물은 없습니다\n", id);
			return;
		}
		articles.remove(foundIndex);
		System.out.printf("%d번 게시물을 삭제했습니다\n", id);
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

	public void makeTestData() {
		System.out.println("테스트를 위한 게시물 데이터를 생성합니다.");
		articles.add(new Article(1, Util.getDateStr(), "제목1", "내용1", 11));
		articles.add(new Article(2, Util.getDateStr(), "제목2", "내용2", 22));
		articles.add(new Article(3, Util.getDateStr(), "제목3", "내용3", 33));
	}

}