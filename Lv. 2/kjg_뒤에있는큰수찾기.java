class Solution {
    public int[] solution(int[] numbers) {
        // numbers 배열 길이 변수
        int len = numbers.length;
        // 정답을 저장할 배열
        int[] answer = new int[len];
        // 마지막 위치는 -1 고정
        answer[len - 1] = -1;
        
        // (마지막 위치 - 1) 인덱스 부터 0 인덱스까지 순회
        for(int i = len - 2; i >= 0; i--){
            // 현재 위치보다 인덱스가 큰 부분을 순차적으로 순회
            for(int j = i + 1; j < len; j++){
                // 뒤에 있는 큰 수일 경우
                if(numbers[i] < numbers[j]){
                    // 이 수를 정답 배열에 저장 후 break;
                    answer[i] = numbers[j];
                    break;
                }
                // 뒤에 있는 큰 수가 아닐때는 정답 배열에 저장된 값을 사용
                else if(numbers[i] >= numbers[j]){
                    // -1일 경우에는 해당 위치보다 큰 수가 뒤에 없음
                    if(answer[j] == -1){
                        answer[i] = -1;
                        break;
                    }
                    // 정답 배열에 저장된 값이 더 큰 수가 될 경우
                    else if(numbers[i] < answer[j]){
                        // 해당 값이 뒤에 있는 큰 수가 될 수 있음
                        answer[i] = answer[j];
                        break;
                    }
                }
            }
        }
        return answer;
    }
}
