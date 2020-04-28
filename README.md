
# TeamHelper

## 0. 개요

    협업지원 서비스

## 1. 개발환경
    * Windows 10
    * JAVA "11.0.5" 2019-10-15 LTS
    * IntelliJ

----

## 2. 주요 기능
    * 그룹에 가입할 수 있음
    * 그룹 그림판
    * 그룹 채팅방
    * 그룹 일정 관리
    * 그룹 파일 공유

___

## 3. 세부 구현 사항
> spring cloud gateway

쿠키가 없을 경우 무조건 resource서버의 index페이지로 연결, JWT의 유효성 검사 후, 요청에 대해 적절히 라우팅

> auth

JWT토큰 발급

> group

MongoDB와 연동 -> 그룹 생성, 그룹 조회, 그룹 멤버 수정

> websocket

STOMP를 사용하여 websocket기능 구현
endpoint 연결 전에, jwt토큰의 유효성 검증 및 Group의 멤버가 맞는지 검사
그 후 활동에 대해서는 사용자확인을 하지 않음

> resource

정적 리소스 저장
