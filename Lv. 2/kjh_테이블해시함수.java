package programmers;

import java.util.*;

public class kjh_테이블해시함수 {
    public static int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        //각 컬럼 값을 i로 나눈 나머지의 합을 저장할 리스트
        List<Integer> sum = new ArrayList<>();
        //col 값 기준으로 오름차순 정렬, 같을 경우 첫번째 값 기준으로 내림차순 정렬
        Arrays.sort(data, new Comparator<int[]>(){
            @Override
            public int compare(int[] x1, int[] x2){
                if(x1[col-1] < x2[col-1]) return -1;
                else if(x1[col-1]==x2[col-1]){
                    return (x1[0] > x2[0]) ? -1 : 1;
                }
                else return 1;
            }
        });
        //정렬된 배열에서 row_begin 부터 row_end 까지의 배열(튜플)의 나머지 합을 계산해 sum 리스트에 저장
        for(int i=row_begin; i<=row_end; i++){
            int tmp = 0;
            for(int j=0; j<data[i-1].length; j++){
                tmp += (data[i-1][j]%i);
            }
            sum.add(tmp);
        }
        //각 나머지의 합 xor 연산 수행
        answer = sum.get(0);
        for(int i=1; i<sum.size(); i++) answer = answer ^ sum.get(i);

        return answer;
    }

    public static void main(String[] args) {
        int[][] data = {{2,2,6},{1,5,10},{4,2,9},{3,8,3}};
        int col = 2;
        int row_begin = 2;
        int row_end = 3;
        System.out.println(solution(data, col, row_begin, row_end));
    }
}
