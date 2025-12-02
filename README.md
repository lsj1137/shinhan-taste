# 🍽️ 신한의 맛 (Shinhan's Taste)

> **신한DS 6기 JDBC & GitHub 실습 프로젝트** > 맛집을 공유하고 평가하는 콘솔 기반(CLI) 게시판 애플리케이션입니다.

<br>

## 📝 프로젝트 소개
**신한의 맛**은 사용자가 직접 방문한 식당에 대한 정보를 등록하고, 별점과 리뷰를 남겨 다른 사람들과 맛집 정보를 공유할 수 있는 서비스입니다. 
Java와 JDBC를 활용하여 DB 연동을 구현하였으며, Git/GitHub을 활용한 팀 협업 프로세스를 익히는 데 중점을 두었습니다.

* **개발 기간**: 2025.11.26 ~ 2025.11.27
* **참여 인원**: 4명

<br>

## 🛠️ 기술 스택 (Tech Stack)
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white"> <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">

<br>

## 📋 주요 기능 (Features)

### 1. 글 쓰기 (Create)
* 제목, 식당 이름, 카테고리(한/중/일/양/기타), 별점, 위치(거리), 비밀번호, 평가를 입력받습니다.
* **유효성 검사**: 필수 항목 미입력 시 재입력 요청.
* **편의성**: 평가 입력 시 'Enter'를 두 번 누르면 입력 종료.

### 2. 글 조회 (Read)
* **목록 조회**:
    * 최신순 정렬
    * 별점순 정렬
    * 음식 종류별(카테고리) 필터링 조회
* **상세 조회**: 글 번호(ID)를 통해 특정 게시글의 상세 내용을 확인합니다.

### 3. 글 수정 (Update)
* 게시글 작성 시 설정한 **비밀번호 4자리**가 일치해야 수정 가능합니다.
* 사용자가 수정을 원하지 않는 필드(엔터 입력)는 기존 데이터를 유지합니다.

### 4. 글 삭제 (Delete)
* **비밀번호 검증**을 통해 본인 확인 후 데이터를 삭제합니다.

<br>

## 👩‍💻 팀원 및 역할 (Roles)

| 이름 | 포지션 | 담당 역할 |
|:---:|:---:|:---|
| **세준** | **팀장** | • Repository 생성 및 브랜치/PR 규칙 수립<br>• 글 쓰기 (평가, 위치, 비밀번호 구현)<br>• 글 삭제 기능 구현 |
| **윤선** | 팀원 | • 글 쓰기 (제목, 식당이름, 별점, 카테고리 구현)<br>• 카테고리 필터링 및 관련 기능 구현 |
| **가은** | 팀원 | • 글 수정 (별점, 평가, 위치 로직 담당) |
| **유나** | 팀원 | • 글 수정 (식당이름, 제목, 카테고리 로직 담당) |

<br>

## 📜 협업 규칙 (Convention)

### 1. 코드 스타일 (Coding Convention)
* **Class Name**: PascalCase (대문자 시작)
* **Variable/Method**: camelCase
* **Database Column**: snake_case
* **Abbreviation**: 대문자 사용 (예: `DTO`, `DAO`)

### 2. 브랜치 전략 (Branch Strategy)
* `main`: 배포 가능한 안정적인 버전
* `develop`: 기능 통합 및 테스트 브랜치
* `feature/기능명`: 각 기능별 개발 브랜치 (예: `feature/insert/title`)

### 3. 커밋 메시지 (Commit Message)
> **형식**: `type : subject` (모두 소문자, 콜론 뒤 띄어쓰기)

| Type | Description |
|:---|:---|
| `feat` | 새로운 기능 추가 |
| `fix` | 버그 수정 |
| `docs` | 문서 수정 |
| `style` | 코드 포맷팅, 세미콜론 누락 등 (코드 변경 없음) |
| `refactor` | 코드 리팩토링 |
| `test` | 테스트 코드 추가/수정 |
| `chore` | 빌드 업무 수정, 패키지 매니저 수정 등 |

<br>

## 🖥️ 실행 화면 예시 (Flow)

### 메인 페이지
```text
(1) 글쓰기
(2) 글조회
(3) 프로그램 종료
번호 미입력 시, 입력하도록 유도
글 상세 페이지
Plaintext

===============================
글번호
───────────────────
식당이름
───────────────────
제목
───────────────────
평가
───────────────────
위치
───────────────────
카테고리
───────────────────
별점
===============================
1. 수정   2. 삭제   0. 글 목록으로
```

## 📂 프로젝트 구조 (Structure)
```
src
└──shinhantaste
    ├─config
    ├─constant
    ├─controller
    ├─dao
    ├─dto
    ├─service
    ├─util
    └─view
```
