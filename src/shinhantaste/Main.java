package shinhantaste;

import java.util.Scanner;

import shinhantaste.controller.InsertController;
import shinhantaste.controller.SelectController;
import shinhantaste.controller.UpdateController;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static InsertController insertController = new InsertController();
    static SelectController selectController = new SelectController();
    
    public static void main(String[] args) {
       boolean isStop = false;

		while (!isStop) {
	        System.out.println("1. 글쓰기");
	        System.out.println("2. 글조회");
	        System.out.print("작업 선택 >> ");
	        String job = sc.next();
			switch (job) {
			case "1" -> {
				insertController.execute();
			}
			case "2" -> {
				selectController.execute();
			}
			default -> {
				isStop = true;
			}
			}
		}
		System.out.println("프로그램을 종료합니다.");
	}
}
