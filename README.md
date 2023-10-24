# JUnit5 Test

1. 단위 테스트
2. TDD: Test Driven Development
3. DisplayName, BDD 스타일
4. Spring & JPA 기반 테스트
5. Mock
6. 더 나은 테스트
7. Spring REST Docs

## 테스트 케이스 세분화하기

- 요구사항이 왔을 경우
    - 요구사항에 해피케이스만 있는지 예외 케이스 여부를 확인해봐야 한다
    - 범위, 구간, 날짜 등이 포함된 요구사항이라면 테스트시에 경계값을 테스트하는 것이 좋다
    - 범위, 구간, 날짜 등이 포함됐을 경우에는 예외 케이스는 경계값 벗어나는 값을 테스트하면 된다
