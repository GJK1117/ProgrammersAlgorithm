package programmers;

import java.util.*;

public class kjh_가장많이받은선물 {
    public static int[][] present;
    public static int[][] value;
    public static boolean[][] visited;
    public static int[] result;
    public static Map<String, Integer> people = new HashMap<>();
    public static int solution(String[] friends, String[] gifts) {
        result = new int[friends.length];
        present = new int[friends.length][friends.length];
        value = new int[friends.length][3];
        visited = new boolean[friends.length][friends.length];
        for(int i=0; i<friends.length; i++){
            people.put(friends[i], i);
        }
        for(int i=0; i<gifts.length; i++){
            String[] s = gifts[i].split(" ");
            Integer give = people.get(s[0]);
            Integer take = people.get(s[1]);
            present[give][take] += 1;
            value[give][0] +=1;
            value[give][2] = value[give][0] - value[give][1];

            value[take][1] +=1;
            value[take][2] = value[take][0] - value[take][1];
        }
        for(int i=0; i<friends.length; i++){
            for(int j=0; j<friends.length; j++) {
                giveAndTakePresent(i, j);
            }
        }
        int max = 0;
        for(int i=0; i<result.length; i++){
            if(result[i]>=max) max = result[i];
        }
        return max;
    }

    public static void giveAndTakePresent(int give, int take){
        if(visited[give][take]) return;

        if(present[give][take] > present[take][give]){
            result[give]++;
        }
        else if(present[give][take] < present[take][give]){
            result[take]++;
        }
        else {
            if(value[give][2] > value[take][2]) result[give]++;
            else if(value[give][2] < value[take][2]) result[take]++;
        }

        visited[give][take] = true;
        visited[take][give] = true;
    }

    public static void main(String[] args) {
        String[] friends = {"muzi", "ryan", "frodo", "neo"};
        String[] gifts = {"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"};
        System.out.println(solution(friends, gifts));
    }
}
