package shinhantaste;

import java.util.Scanner;

import shinhantaste.controller.InsertController;
import shinhantaste.controller.UpdateController;

public class Main {

	static Scanner sc = new Scanner(System.in);
	static InsertController insertController = new InsertController();
	static UpdateController updateController = new UpdateController();

	public static void main(String[] args) {
		boolean isStop = false;
		System.out.println("1. 글쓰기");
		System.out.println("2. 글조회");
		System.out.print("작업 선택 >> ");
		String job = sc.next();

		while (isStop) {
			switch (job) {
			case "1" -> {
				insertController.execute();
			}
			case "2" -> {
				updateController.execute();
			}
			default -> {
				isStop = true;
			}
			}
		}
		System.out.println("프로그램을 종료합니다.");
	}
}
