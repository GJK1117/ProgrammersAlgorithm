import java.util.*;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        int len = weights.length;
        // hashmap을 사용하여 100~1000사이의 901가지의 숫자만 고려함
        Map<Integer, Integer> weightCount = new HashMap<>();
        
        // 각 무게를 hashmap에 추가
        for (int weight : weights) {
            // 값이 이미 존재하는 경우는 값 반환, 아니면 0을 반환하도록 getOrDefault함수를 사용하여 빈도 수를 카운트
            weightCount.put(weight, weightCount.getOrDefault(weight, 0) + 1);
        }
        
        // 존재하는 수들을 순회, ketSet함수를 통해 키 값을 순회
        for (int weight : weightCount.keySet()) {
            int count = weightCount.get(weight);
            
            // 조건 1 : 값이 같은 경우, 값이 하나 이상 있다면 같은 값이 존재 할 수 있음
            if (count > 1) {
                answer += (long) count * (count - 1) / 2;
            }
            
            // 조건 2: weights[i] * 4 == weights[j] * 3
            // 3으로 나누어 떨어지는지 확인, 그 후 값이 존재하는지 확인
            if (weight % 3 == 0 && weightCount.containsKey(weight / 3 * 4)) {
                answer += (long) count * weightCount.get(weight / 3 * 4);
            }
            
            // 조건 3: weights[i] * 3 == weights[j] * 2
            // 2로 나누어 떨어지는지 확인, 그 후 값이 존재하는지 확인
            if (weight % 2 == 0 && weightCount.containsKey(weight / 2 * 3)) {
                answer += (long) count * weightCount.get(weight / 2 * 3);
            }
            
            // 조건 4: weights[i] * 2 == weights[j]
            // 값이 두 배인 것이 존재하는 지 확인
            if (weightCount.containsKey(weight * 2)) {
                answer += (long) count * weightCount.get(weight * 2);
            }
        }
        
        // 결과 반환
        return answer;
    }
}
