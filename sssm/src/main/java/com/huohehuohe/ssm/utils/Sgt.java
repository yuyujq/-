package com.huohehuohe.ssm.utils;

import org.springframework.stereotype.Component;

@Component
public class Sgt implements CompactDisc {
	
	private String title = "yu jianqiu";
	private String artist = "asdad";
	
	public void play() {
		// TODO 自动生成的方法存根
		System.out.println(title+"  "+artist);
	}

}
