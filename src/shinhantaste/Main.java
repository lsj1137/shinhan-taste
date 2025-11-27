package shinhantaste;

import java.util.Scanner;

import shinhantaste.controller.InsertController;
import shinhantaste.controller.SelectController;
import shinhantaste.controller.UpdateController;
import shinhantaste.util.PrintUtil;

public class Main {

	static Scanner sc = new Scanner(System.in);
	static InsertController insertController = new InsertController();
	static SelectController selectController = new SelectController();

	public static void main(String[] args) {
		boolean isStop = false;
		System.out.println("1. 글쓰기");
		System.out.println("2. 글조회");
		System.out.println("0. 프로그램 종료");

		while (!isStop) {
			System.out.print("작업 선택 >> ");
			String job = sc.next();

			switch (job) {
			case "1" -> {
				insertController.execute();
			}
			case "2" -> {
				selectController.execute();
			}
			case "0" -> {
				isStop = true;
			}
			default -> {
				PrintUtil.alert("잘못된 번호입니다.\n");
				PrintUtil.request("다시 입력해주세요");
			}
			}
		}
		PrintUtil.alert("프로그램을 종료합니다.");
	}
}
