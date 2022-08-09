package com.KoreaIT.java.AM;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("====시작====");
		Scanner sc = new Scanner(System.in);
		System.out.printf("명령어)" );
		String ss = sc.nextLine();
//		System.out.printf("입력한 명령어 : %s\n", ss );
		while (true) {
			System.out.printf("명령어)" );
			String cmd = sc.nextLine();
			if (cmd.equals("exit")) {
				break;
			}
		}
		sc.close();
		System.out.println("====끝====");
		
		
	}
}
