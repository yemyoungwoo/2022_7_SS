package com.KoreaIT.java.BAM.controller;

import com.KoreaIT.java.AM.dto.GmMember;
import com.KoreaIT.java.AM.dto.Member;

public abstract class Controller {
	public static Member loginedMember;
	
	public static GmMember GmloginedMember;
	
	public abstract void doAction(String cmd, String actionMethodName);
	
	public static boolean isLogined() {
		return loginedMember != null;
	}

	
}
