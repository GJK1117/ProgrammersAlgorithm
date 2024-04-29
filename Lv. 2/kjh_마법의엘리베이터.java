package programmers;

public class kjh_마법의엘리베이터 {
    public static int solution(int storey) {
        int answer = 0;
        while(storey>0) {
            int mod = storey % 10;
            //맨 끝자리수를 판단해주며 storey를 0으로 만들며 횟수를 세준다.
            //나머지가 5보다 크면 10에서 나머지를 뺀 값을 더해주고, 5보다 작으면 나머지를 빼주어야 최소횟수를 만족할 수 있다.
            //하지만 나머지가 5일경우에는 더해주었을 때와 빼주었을 때 값이 같으므로 따로 예외 처리를 해주어야한다.
            //이때는 바로 앞의 숫자의 유무를 따져준다.
            //예를 들어 265 일 경우 1의 자리가 5이므로 앞의 십의자리 6을 검사한다. 이때 더 최소횟수를 만족하기 위해선
            //5에 5만큼 더해주어 십의 자리를 7로 만들어 주는 것이 최소횟수를 만족하기 때문에 현재 나머지가 5이고
            //바로 앞의 수가 5보다 클 경우에도 나머지를 더해준다.
            if((mod==5 && storey/10%10>=5) || mod > 5){
                storey += (10-mod);
                answer += (10-mod);
            } else {
                storey -= mod;
                answer += mod;
            }

            storey /= 10;
        }
        return answer;
    }

    public static void main(String[] args) {
        int storey = 16;
        System.out.println(solution(storey));
    }
}
