package com.huohehuohe.ssm.utils;

import org.springframework.stereotype.Component;

@Component
public class Sgt implements CompactDisc {
	
	private String title = "yu jianqiu";
	private String artist = "asdad";
	
	public void play() {
		// TODO �Զ����ɵķ������
		System.out.println(title+"  "+artist);
	}

}
