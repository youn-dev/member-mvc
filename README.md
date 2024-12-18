구현 목록
1. 회원가입
   * 회원가입 후 JWT로 토큰 발행하여 cookie에 저장.
   * 쿠키의 만료는 30일로 재활용 가능.
   * httpOnly 옵션을 주어 http 통신으로만 쿠키 읽기 및 전송 가능.
   * sameSite strict 옵션을 주어 지정 도메인에서만 읽기 가능.
2. 로그인 
   * 로그인 후 JWT로 토큰 발행하여 cookie에 저장.
   * 쿠키의 만료는 30일로 재활용 가능.
   * httpOnly 옵션을 주어 http 통신으로만 쿠키 전송 가능.
   * sameSite strict 옵션을 주어 지정 도메인에서만 읽기 가능.
3. 인증
   * 쿠키 또는 Bearer 헤더에 들어오는 JWT를 인터셉터로 구현하여 사전에 확인 후 Local Thread에 저장.
   * 쿠키가 존재하지 않으면 Bearer 헤더 검증 후 없을시 인증 에러 반환
3. 로그아웃
   * 쿠키 내역을 만료시켜 로그아웃 처리
4. 사용자 조회
   * 쿠키 또는 Bearer 헤더에 들어오는 JWT를 기반으로 사용자 조회
5. 자산 추가
    * 중복된 명칭의 자산을 생성하면 에러로 반환하여 방어.
5. 자산 조회
   * 자산 ID로 조회
6. 공간 추가
   * 주어진 엑셀파일을 읽어와 공간을 추가
7. 공간 조회
   * 자산 번호로 공간을 조회하거나 공간 번호로 자산번호와 함께 조회 가능.

API 목록은 http://localhost:8080/swagger-ui/index.html#/ 에서 확인 가능.
