public class kjg_가장가까운같은글자{
    public int[] solution(String s) {
        // String을 reverse하기 위해 StringBuffer 사용
        // 가장 가까운 같은 글자를 구하기 용이하게 reverse 사용
        StringBuffer sb = new StringBuffer(s);
        String reverse = sb.reverse().toString();

        // 결과 배열 선언
        int[] answer = new int[s.length()];
        // 처음 위치는 앞에 문자가 없으므로 무조건 -1
        answer[0] = -1;
        // 1위치부터 s 문자열을 순회
        for(int i = 1; i < s.length(); i++){
            // s 문자열의 i번째 문자부터의 부분 문자열은 reverse 문자열의 뒤에서부터 i번째 문자부터의 부분 문자열과 같음
            String tmp = reverse.substring(s.length() - i - 1, s.length());
            // indexOf(char형 문자, 시작 위치)를 사용하여 자기 자신을 제외한 가장 가까운 같은 문자의 위치를 찾음, 없으면 -1 반환
            answer[i] = tmp.indexOf(s.charAt(i), 1);
        }

        // 결과 반환
        return answer;
    }
}
