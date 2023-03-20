package com.KoreaIT.java.BAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.dto.GmMember;
import com.KoreaIT.java.AM.dto.Member;
import com.KoreaIT.java.AM.util.Util;

public class MemberController extends Controller {
	private Scanner sc;
	private static List<Member> members;
	private static List<GmMember> gmmembers;
	private String cmd;
	private String actionMethodName;

	public MemberController(Scanner sc) {
		this.sc = sc;
		members = new ArrayList<>();
	}

	public void doAction(String cmd, String actionMethodName) {
		this.cmd = cmd;
		this.actionMethodName = actionMethodName;
		
		switch (actionMethodName) {
		case "join":
			doJoin();
			break;

		case "login":
			doLogin();
			break;

		case "gmlogin":
			hdLogin();
			break;
			
		case "logout":
			doLogout();
			break;

		case "list":
			if (isLogined() == false) {
				System.out.println("로그인이 필요합니다");
				break;
			}
			showList();
			break;

		default:
			System.out.println("존재하지 않는 명령어입니다");
			break;
		}
	}

//	private void hdLogin() {
//		System.out.printf("로그인 아이디 : ");
//		String loginId = sc.nextLine();
//		System.out.printf("로그인 비밀번호 : ");
//		String loginPw = sc.nextLine();
//
//		Member member = getMemberByLoginId(loginId);
//
//		if (member == null) {
//			System.out.println("일치하는 회원이 없습니다");
//			return;
//		}
//
//		if (member.loginPw.equals(loginPw) == false) {
//			System.out.println("비밀번호를 다시 입력해주세요");
//			return;
//		}
//
//		loginedMember = member;
//		System.out.printf("로그인 성공! %s님 환영합니다.\n", loginedMember.name);
//
//	}

	private void doLogout() {
		if (isLogined() == false) {
			System.out.println("로그인 상태가 아닙니다");
			return;
		}
		loginedMember = null;
		System.out.println("로그아웃 되었습니다.");
	}

	private void hdLogin() {
		System.out.printf("운영자 로그인 아이디 : ");
		String gmloginId = sc.nextLine();
		System.out.printf("로그인 비밀번호 : ");
		String gmloginPw = sc.nextLine();

		GmMember member = getMemberByGmLoginId(gmloginId);
		
		if(member == null) {
			System.out.println("일치하는 회원이 없습니다.");
			
		GmloginedMember = member;
		System.out.printf("로그인 성공! %s님 환영합니다.\n", loginedMember.name);
		}
	}

	private GmMember getMemberByGmLoginId(String gmloginId) {
		int index = getMemberIndexByLoginGmId(gmloginId);

		if (index == -1) {
			return null;
		}

		return gmmembers.get(index);
	}

	private int getMemberIndexByLoginGmId(String gmloginId) {
		int i = 0;
		for (GmMember members : gmmembers) {
			if (members.gmloginId.equals(gmloginId)) {
				return i;
			}
			i++;
		}
		return -1;
	}

	private void showList() {

		if (members.size() == 0) {
			System.out.println("회원이 없습니다");
			return;
		}
//		if (loginedMember.id == 0) {
//			System.out.println("권한이 없습니다.");
//			return;
//		}

		System.out.println("아이디  - 이름 ");
		List<Member> forPrintMembers = members;
		for (int i = forPrintMembers.size() - 1; i >= 0; i--) {
			Member member = forPrintMembers.get(i);
			System.out.printf("%s - %s\n", member.loginId, member.name);
		}

		int m = 0;
		List<Member> forSumMember = members;
		for (m = 0; m < forSumMember.size(); m++) {
//			Member member = forPrintMembers.get(m); 이게 굳이 필요한가
		}
		System.out.printf("총 : %d명\n", m);
	}

	private void doLogin() {
		if(isLogined() == true) {
			System.out.println("이미 로그인 상태입니다");
			return;
		}
//		String gmloginId = null;
		System.out.printf("로그인 아이디 : ");
		String loginId = sc.nextLine();
		System.out.printf("로그인 비밀번호 : ");
		String loginPw = sc.nextLine();

		Member member = getMemberByLoginId(loginId);
//		GmMember member1 = getMemberByGmLoginId(gmloginId);

		if (member == null) {
			System.out.println("일치하는 회원이 없습니다");
			return;
		}

		if (member.loginPw.equals(loginPw) == false) {
			System.out.println("비밀번호를 다시 입력해주세요");
			return;
		}
//		if 	(member.loginId.equals(gmloginId) == false) {
//			System.out.println("권한이 없습니다.");
//			return;
//		}
			loginedMember = member;
			System.out.printf("로그인 성공! %s님 환영합니다.\n", loginedMember.name);

		}
	private Member getMemberByLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);

		if (index == -1) {
			return null;
		}

		return members.get(index);
	}

	private void doJoin() {
		int id = members.size() + 1;
		String regDate = Util.getDateStr();
		String loginId = null;

		while (true) {

			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();

			if (isJoinableLoginId(loginId) == false) {
				System.out.printf("%s은(는) 이미 사용중인 아이디입니다\n", loginId);
				continue;
			}
			break;
		}

		String loginPw = null;
		String loginPwConfirm = null;

		while (true) {

			System.out.printf("로그인 비밀번호 : ");
			loginPw = sc.nextLine();
			System.out.printf("로그인 비밀번호 확인 : ");
			loginPwConfirm = sc.nextLine();

			if (loginPw.equals(loginPwConfirm) == false) {
				System.out.println("비밀번호를 다시 입력해주세요");
				continue;
			}
			break;
		}
		System.out.printf("이름 : ");
		String name = sc.nextLine();
		Member member = new Member(id, regDate, loginId, loginPw, name);
		members.add(member);
		System.out.printf("%d번 회원님 환영합니다\n", id);
	}

	private boolean isJoinableLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);

		if (index == -1) {
			return true;
		}

		return false;
	}

	private int getMemberIndexByLoginId(String loginId) {
		int i = 0;
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return i;
			}
			i++;
		}

		return -1;
	}

	public static void makeTestData() {
		System.out.println("테스트를 위한 회원 데이터를 생성합니다.");
		members.add(new Member(1, Util.getDateStr(), "test1", "test1", "김철수"));
		members.add(new Member(2, Util.getDateStr(), "test2", "test2", "김영수"));
		members.add(new Member(3, Util.getDateStr(), "test3", "test3", "김영희"));
	}
//
//	public static void hiddenId() {
//		System.out.println("테스트를 위한 운영자 데이터를 생성합니다.");
//		gmmembers.add(new GmMember(1, "GM1", "123", "운영자1"));
//		gmmembers.add(new GmMember(2, "GM2", "123", "운영자2"));
//		gmmembers.add(new GmMember(3, "GM3", "123", "운영자3"));
//	}
}