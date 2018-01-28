# 스트림 소스와 분해성

1. parallel

소스 / 분해성
ArrayList / 훌륭함
LinkedList / 나쁨
IntStream.range / 훌륭함
Stream.iterate / 나쁨
HashSet / 좋음
TreeSet / 좋음

2. recursiveTask 
if (태스크가 충분히 작거나 더 이상 분할할 수 없으면) {
    순차적으로 태스크 계산
} else {
    태스크를 두 서브태스크로 분할
    태스크가 다시 서브태스크로 분할되로록 이 메서드를 재귀적으로 호출함
    모든 서브태스크의 연산이 완료될 때까지 기다림
    각 서브태스크의 결과를 합침
}

ForkJoin 프레임워크 작업훔치기.


