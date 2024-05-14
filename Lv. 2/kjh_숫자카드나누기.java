package programmers;

public class kjh_숫자카드나누기 {
    public static int solution(int[] arrayA, int[] arrayB) {
        //철수(arrayA), 영희(arrayB)
        //조건 1: 철수가 가진 카드들에 적힌 모든 숫자를 나눌 수 있고 영희가 가진 카드들에 적힌 모든 숫자들 중 하나도 나눌 수 없는 양의 정수 a
        int answer1 = findAnswer(arrayA, arrayB);
        //조건 2: 영희가 가진 카드들에 적힌 모든 숫자를 나눌 수 있고, 철수가 가진 카드들에 적힌 모든 숫자들 중 하나도 나눌 수 없는 양의 정수 a
        int answer2 = findAnswer(arrayB, arrayA);
        //둘다 0이어서 조건에 만족하지 않으면 0을 리턴
        if(answer1==0 && answer2==0) return answer1;
        //이외의 더 큰 값을 리턴
        return (answer1 >= answer2) ? answer1 : answer2;
    }

    static int findAnswer(int[] arr1, int[] arr2){
        int ans = arr1[0];
        boolean sta = false;

        //한 사람의 카드들에 적힌 모든 숫자를 나눌 수 있는 지 판단해주기 위해선 배열의 모든 숫자에 대한 최대공약수를 구하면 된다.
        //유클리드 호제법을 사용하여 배열의 모든 값들에 대한 최대공약수를 구해준다.
        for(int i=1; i<arr1.length; i++){
            ans = gcd(ans, arr1[i]);
        }
        //다른 사람의 카드들에 적힌 모든 숫자들 중 하나도 나눌 수 없는 양의 정수를 얻기 위해
        //위에서 구한 최대공약수 값을 이용해 이 값과 나누어 지지 않으면 조건에 충족(false 유지), 나누어지면 true를 넣어주어 판별해준다.
        for(int i=0; i<arr2.length; i++){
            if((arr2[i]%ans)==0){
                sta = true;
                break;
            }
        }
        //만약 조건에 부적합(true)하면 0 을 반환한다.
        return (sta) ? 0 : ans;
    }

    //유클리드 호제법으로 최대공약수 구함
    static int gcd(int x, int y){
        if(y==0) return x;
        return gcd(y, x%y);
    }

    public static void main(String[] args) {
        int[] arrayA = {10,20};
        int[] arrayB = {5,17};
        System.out.println(solution(arrayA, arrayB));
    }
}
