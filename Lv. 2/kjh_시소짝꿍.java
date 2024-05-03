package programmers;

import java.util.*;

public class kjh_시소짝꿍 {
    public static long solution(int[] weights) {
        long answer = 0;
        List<Double> distance = new ArrayList<>(Arrays.asList(2.0,3.0,4.0));
        Map<Integer, Integer> dup = new HashMap<>();    //중복되는 무게 카운트 - 중복되는 반복 없이 조합계산을 위함
        List<Integer> weight = new ArrayList<>();   //중복을 제거한 사람들의 무게 리스트
        Arrays.sort(weights);   //오름차순 정렬
        for(int i : weights){
            //dup, weight 초기화
            if(!dup.containsKey(i)) {
                dup.put(i, 0);
                weight.add(i);
            }
            dup.put(i, dup.get(i)+1);
        }
        //각각의 무게 비교
        for(int i=0; i<weight.size()-1; i++){
            for(int j=i+1; j<weight.size(); j++){
                //두 무게의 최대공약수를 구함
                int gcd = gcd(weight.get(i), weight.get(j));
                //각 무게를 최대공약수로 나누어줌
                double x1 = weight.get(i)/gcd;
                double x2 = weight.get(j)/gcd;
                //시소짝꿍인지 판단하기 위해 최대공약수로 나눈 값이 (2,3),(2,4),(3,4) 을 만족하는지 검사
                //(1,2) 로 나오는 경우도 있기 때문에 두배인지도 한번도 확인해준다.
                if((distance.contains(x1) && distance.contains(x2)) || x2/x1==2) {
                    //각 무게의 조합을 계산해 정답에 더해줌
                    answer += ((long)dup.get(weight.get(i))*dup.get(weight.get(j)));
                }
            }
        }
        //같은 무게끼리는 무조건 시소짝꿍이므로 조합 계산
        for(Integer k : dup.values()){
            if(k>1) {
                for(int i=k-1; i>=0; i--) answer += i;
            }
        }
        return answer;
    }

    //최대공약수 구하기 - 유클리드 호제법
    static int gcd(int x, int y){
        if(x%y==0) return y;
        return gcd(y, x%y);
    }
}
