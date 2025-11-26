package shinhantaste.util;

public class InputChecker {
	// 평가 입력 종료 체크
	public static boolean endReviewInput(String reviewLine) {
		return !reviewLine.trim().isEmpty();
	}
	
	// 입력 길이 체크
	public static boolean lengthCheck(String content, int limit) {
		return content.length()<=limit;
	}
	
	// 숫자 범위 체크
	public static boolean inRange (int cmd, int start, int end) {
		return cmd>=start && cmd<=end;
	}
	
	// 비밀번호 유효성 검증 (4자리, 숫자)
	public static boolean validPassword(String pw) {
		if (pw.length()!=4) return false;
		for (Character c: pw.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}
}
