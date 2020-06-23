package com.util;

import java.util.Scanner;

public class Apple {
	int ss = 0;
	Scanner s;
	public void getInput(){
		s = new Scanner(System.in);
		ss = s.nextInt();
	}
}
class Test{
	public static void main(String[] args) {
		Apple apple = new Apple();
		apple.getInput();
		System.out.println(apple.ss);
	}
}