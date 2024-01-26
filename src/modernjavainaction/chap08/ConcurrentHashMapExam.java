package modernjavainaction.chap08;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Concurrent Hash Map 은 내부 자료구조의 특정 부분만 잠궈 동시 추가, 갱신 작업을 허용한다.
 * 따라서 동기화된 Hashtable 버전에 비해 읽기 쓰기 연산 성능이 월등하다.
 */
public class ConcurrentHashMapExam {
    public static void main(String[] args) {
        /**
         * 스레드 안전성 제공
         */
        ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
    }
}
