package com.KoreaIT.java.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.dto.GmMember;
import com.KoreaIT.java.AM.dto.Member;
import com.KoreaIT.java.BAM.controller.Controller;

public class GmMemberController extends Controller{
	
	private Scanner sc;
	private static List<Member> members;
	private static List<GmMember> gmmembers;
	private String cmd;
	private String actionMethodName;

	public GmMemberController(Scanner sc) {
		this.sc = sc;
		members = new ArrayList<>();
	}

	
	public void doAction(String cmd, String actionMethodName) {
		this.cmd = cmd;
		this.actionMethodName = actionMethodName;
		
		switch (actionMethodName) {
		
		case "gmlogin":
			hdLogin();
			break;
			
		default:
			System.out.println("존재하지 않는 명령어입니다");
			break;
		}
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

//	public static void makeTestData() {
//		System.out.println("테스트를 위한 운영자 데이터를 생성합니다.");
//		gmmembers.add(new GmMember(1, "GM1", "123", "운영자1"));
//		gmmembers.add(new GmMember(2, "GM2", "123", "운영자2"));
//		gmmembers.add(new GmMember(3, "GM3", "123", "운영자3"));
//	}
}
