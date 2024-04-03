package programmers;

import java.util.*;

public class kjh_네트워크 {
    static Set<Integer> answer = new HashSet<>();   //정답을 구할 Set
    static int[] degree;    //유니온 파인드 배열
    public static int solution(int n, int[][] computers) {
        degree = new int[n];
        //각 인덱스 값으로 초기화
        for(int i=0; i<n; i++) degree[i]=i;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++) {
                //노드 i와 노드 j가 연결되어 있는 경우 union 연산 수행
                if(computers[i][j]==1) {
                    union(i, j);
                }
            }
        }
        //연결되어 있지만 대표노드 값 다를 수도 있으므로 대표노드 다시 갱신해주어야함 -> union 한번더 수행하여 대표노드 갱신
        //인덱스와 값이 같으면 연결되어 있지 않은 것이 확실하므로 union 수행 X
        for(int i=0; i<n; i++) {
            if(i!=degree[i]) union(i, degree[i]);
        }
        //set은 값이 중복되어 들어가지 않으므로 각 대표노드를 set에 넣어준다.
        for(int i=0; i<n; i++) answer.add(degree[i]);
        //대표노드 개수(=네트워크 개수) 반환
        return answer.size();
    }

    //유니온 파인드 연산을 통해 연결된 노드는 대표노드 값으로 통일
    static void union(int x, int y){
        x = find(x);
        y = find(y);
        if(x != y) degree[y] = x;
    }

    static int find(int x) {
        if(x==degree[x]) return x;
        else return degree[x] = find(degree[x]);
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println(solution(n, computers));
    }
}
