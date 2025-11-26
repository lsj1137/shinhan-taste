package shinhantaste.controller;

import java.util.Scanner;

import shinhantaste.dto.ArticleDTO;
import shinhantaste.util.InputChecker;
import shinhantaste.util.PrintUtil;

public class InsertController {
	Scanner sc = new Scanner(System.in);

	public void execute() {
		ArticleDTO articleDTO = new ArticleDTO();
		getReview(articleDTO);
		getDistance(articleDTO);
		getPassWord(articleDTO);
		System.out.println(articleDTO);
	}
	
	
	public ArticleDTO getReview(ArticleDTO articleDTO) {
		String review = "";
		String line = null;
		while (true) {
			PrintUtil.request("평가를 입력하세요(100자 이내, 끝내려면 Enter 2번 입력)\n");
			boolean keepWrite = true;
			while (keepWrite = InputChecker.endReviewInput(line = sc.nextLine())) {
				review += line;
				if (keepWrite) {
					review += "\n";
				}
			}
			if (InputChecker.lengthCheck(review, 100)) {
				break;
			} else {
				PrintUtil.alert("100자를 넘길 수 없습니다. 다시 입력해주세요.");
				review = "";
			}
		}
		articleDTO.setReview(review);
		return articleDTO;
	}
	
	public ArticleDTO getDistance(ArticleDTO articleDTO) {
		PrintUtil.request("위치를 입력해주세요(도보 기준 소요시간:분)");
		int distance = sc.nextInt();
		articleDTO.setDistance(distance);
		return articleDTO;
	}
	
	public ArticleDTO getPassWord(ArticleDTO articleDTO) {
		PrintUtil.request("비밀번호를 입력해주세요(4자리 숫자, 필수)");
		String password = sc.next();
		while (!InputChecker.validPassword(password)) {
			PrintUtil.request("잘못된 형식입니다. 다시 입력해주세요");
			password = sc.next();
		}
		articleDTO.setPassword(password);
		sc.nextLine();
		return articleDTO;
	}
}
