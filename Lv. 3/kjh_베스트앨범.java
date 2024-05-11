package programmers;

import java.util.*;

public class kjh_베스트앨범 {
    static Map<String, Integer> playSum = new HashMap<>();  //장르별 전체 재생횟수를 저장할 맵
    static Map<String, PriorityQueue<Song>> genrePQ = new HashMap<>();  //장르별 노래를 저장할 맵, 우선순위 큐에 장르별 노래들이 저장됨
    public static List<Integer> solution(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList<>();
        int len = genres.length;
        for(int i=0; i<len; i++){
            if(!playSum.containsKey(genres[i])) {
                playSum.put(genres[i], 0);
                genrePQ.put(genres[i], new PriorityQueue<>());
            }
            //playSum -> 각 장르별 총 재생횟수를 넣어줌
            playSum.put(genres[i], playSum.get(genres[i])+plays[i]);
            //각 장르별에 우선순위 큐에 현재 음악의 고유번호와 재생횟수를 넣어줌
            genrePQ.get(genres[i]).offer(new Song(i, plays[i]));
        }

        //playSum 의 value(장르별 총 재생횟수) 기준으로 정렬하여 리스트에 저장
        List<String> sortPlaySum = new ArrayList<>(playSum.keySet());
        sortPlaySum.sort(new Comparator<String>(){
            @Override
            public int compare(String o1, String o2){
                //map 자료형의 get 메소드로 각 장르별 총 재생횟수를 비교해 내림차순 정렬해준다.
                return (playSum.get(o1) > playSum.get(o2)) ? -1 : 1;
            }
        });

        //총 재생횟수가 많은 장르 순서대로 answer 저장
        for(String genre : sortPlaySum){
            int cnt = 0;    //들어간 노래를 카운트 해줄 변수
            PriorityQueue<Song> pq = genrePQ.get(genre);
            //현재 장르의 음악들이 저장된 우선순위 큐를 반복
            //큐가 비거나 2개의 음악이 저장될때 까지 반복
            while(!pq.isEmpty() && cnt < 2){
                answer.add(pq.poll().num);
                cnt++;
            }
        }
        return answer;
    }

    //노래의 고유 번호, 재생 횟수
    //Comparable 사용: 재생 횟수가 큰 순으로 정렬 같을 경우 고유 번호가 작은 것이 먼저 오도록 정렬
    static class Song implements Comparable<Song>{
        int num;
        int play;

        public Song(int num, int play){
            this.num = num;
            this.play = play;
        }

        @Override
        public int compareTo(Song o){
            if(this.play==o.play){
                return (this.num<o.num) ? -1 : 1;
            } else {
                return (this.play>o.play) ? -1 : 1;
            }
        }
    }

    public static void main(String[] args) {
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};
        System.out.println(solution(genres, plays));
    }
}
