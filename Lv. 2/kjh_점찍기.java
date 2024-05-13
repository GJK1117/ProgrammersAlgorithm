package programmers;

public class kjh_점찍기 {
    public static long solution(int k, int d) {
        //원의 방정식을 떠올리면 쉽게 풀수 있다.
        //문제를 해석하면 결국 x,y 정수가 양수인 범위에서 d보다 작은 좌표값을 구하는 문제이다.
        //이것을 이용해서 원의 방정식을 세우면 (kx)^2 + (ky)^2 = d^2 이 된다.
        //이것을 정리하면 x^2 + x^2 = d^2/k^2 이 되고 y 로 정리하면 y = sqrt(d^2/k^2 - x^2) 이된다.
        //y값을 구하는 이유는 원위 방정식 위에 있는 각 y값을 구하면 그 값이 아래 포함된 정수인 좌표의 값을 의미하기 때문이다.
        //이때 y값이 실수일 수도 있으므로 정수 좌표를 구하기 위해 floor 함수로 내림을 해준다.
        //k와 d는 주어지므로 그대로 넣은 후 전개하여 구하면된다.
        //radius : 반지름의 제곱값 여기서 d^2/k^2 인 값이다.
        double radius = Math.pow(d, 2)/Math.pow(k, 2);
        //y가 0인 경우 좌표도 포함되므로 미리 더해놓는다.
        long answer = (long)Math.sqrt(radius)+1;
        //x가 0부터 반지름까지 반복하며 y값(포함된 좌표 개수)을 정답 값에 더해준다.
        for(int x=0; x<=Math.sqrt(radius); x++){
            answer += dotCount(x, radius);
        }
        return answer;
    }

    //함수 식: y = sqrt(d^2/k^2 - x^2)
    static long dotCount(int x, double radius){
        //sqrt(radius-x^2) 계산한후 내림을 해준뒤 반환한다.
        //정답을 long 으로 반환하므로 double -> long 으로 형변환 해준 후 반환한다.
        return (long)Math.floor(Math.sqrt(radius-Math.pow(x, 2)));
    }

    public static void main(String[] args) {
        System.out.println(solution(2, 4));
    }
}
