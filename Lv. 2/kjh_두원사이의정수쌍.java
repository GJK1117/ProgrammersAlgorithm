package programmers;

public class kjh_두원사이의정수쌍 {
    public static long solution(int r1, int r2) {
        long answer = 0;
        for(int i=(-r2); i<=r2; i++){
            //원의 방정식을 활용해 y를 구한다 -> y^2 = r^2 - x^2
            //r1 원은 외부 점을 판단해야 하므로 ceil 로 올림해준다
            //r2 원은 내부 점을 판단해야 하므로 floor 로 내림해준다
            //두 y점의 차이가 r1, r2 사이에 있는 점의 개수가 된다
            long y1 = (long) Math.ceil(Math.sqrt(Math.pow(r1,2)-Math.pow(i,2)));
            long y2 = (long) Math.floor(Math.sqrt(Math.pow(r2,2)-Math.pow(i,2)));
            //-y인 경우도  있으므로 2 곱해주어야한다
            //y가 x 축에 있을경우 위 아래 개수에서 겹치므로 따로 처리해주었다
            answer += (y1==0 || y2==0) ? ((y2-y1)*2+1) : ((y2-y1+1)*2);
        }
        return answer;
    }

    public static void main(String[] args) {
        int r1 = 2;
        int r2 = 3;
        System.out.println(solution(r1, r2));
    }
}
