package com.KoreaIT.java.AM.dto;

import com.KoreaIT.java.BAM.dto.Dto;

public class GmMember extends Dto {
	public String gmloginId;
	public String gmloginPw;
	
	public GmMember(int GmId, String gmloginId, String gmloginPw, String Gmname) {
		
		this.gmloginId = gmloginId;
		this.gmloginPw = gmloginPw;
		this.GmId = GmId;
		this.Gmname = Gmname;
	
	}
}
