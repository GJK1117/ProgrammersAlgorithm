public class kjg_크기가작은부분문자열 {
    public int solution(String t, String p) {
        // 정답 변수 선언
        int answer = 0;
        // t, s의 길이 저장
        int t_size = t.length(), p_size = p.length();
        // String t, s를 char[]로 변환
        char[] t_char = t.toCharArray(), p_char = p.toCharArray();

        // t_size - p_size 위치까지 t를 순회
        for(int i = 0; i <= t_size - p_size; i++){
            // true면 p보다 작은 부분문자열, 아닌 경우 false
            boolean check = true;
            // p_size만큼 p를 순회
            for(int j = 0; j < p_size; j++){
                // 각 자리수를 비교했을 때, t_char가 p_char보다 크면 false
                if(t_char[i + j] > p_char[j]){
                    check=false;
                    break;
                }
                // 큰 자리수부터 비교하므로, 큰 자리수가 작으면 작은 자리 수는 비교할 필요 없음
                else if(t_char[i + j] < p_char[j]) break;
            }
            // check가 true면 answer 증가
            if(check) answer++;
        }

        // 정답 반환
        return answer;
    }
}
