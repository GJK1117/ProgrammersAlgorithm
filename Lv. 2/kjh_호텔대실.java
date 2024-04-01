package programmers;

import java.util.*;

public class kjh_호텔대실 {
    public static int solution(String[][] book_time) {
        int answer = 0;
        List<Time> list = new ArrayList<>();
        //자정을 넘지 않으므로 그냥 크기로 비교해주기 위해 시간을 정수로 바꾼 후 Time 생성하여 list에 추가
        for(int i=0; i<book_time.length; i++){
            String[] s = book_time[i][0].split(":");
            String[] e = book_time[i][1].split(":");
            list.add(new Time(Integer.parseInt(s[0]+s[1]), Integer.parseInt(e[0]+e[1])));
        }
        //시작 시간 기준 정렬
        Collections.sort(list, new Comparator<Time>() {
            @Override
            public int compare(Time o1, Time o2) {
                return Integer.compare(o1.start, o2.start);
            }
        });
        //list 가 빌때 까지 반복
        while(list.size() > 0){
            List<Integer> idx = new ArrayList<>();
            Time t = list.get(0);
            list.remove(0); //현재 Time 리스트에서 제거
            int pTen = t.end + 10;  //현재 Time 의 종료시간에 10분을 더해줌
            for(int i=0; i<list.size(); i++){
                //60분을 넘어가면 넘어간 만큼 시간에 + 해줌
                if(pTen % 100 >= 60) pTen = ((pTen/100 + ((pTen%100)/60)) * 100 + (pTen%100-60));
                //종료시간에 10분을 더한 시간보다 현재 검사하는 list 의 Time 시작시간이 클경우 하나의 룸에 배정 가능하므로 list 에서 삭제될 인덱스 값으로 추가함
                if(list.get(i).start>=pTen) {
                    idx.add(i);
                    pTen = list.get(i).end + 10;    //pTen 현재 Time의 종료시간 기준으로 초기화
                }
            }
            //list 삭제될 인덱스를 저장한 리스트를 순회하며 list의 값을 지워준다.
            //이때 처음부터 반복하며 지워주면, index 오류가 나므로 뒤에서부터 반복하며 list 의 뒤부터 지워준다.
            for(int i=idx.size()-1; i>=0; i--){
                list.remove((int) idx.get(i));
            }
            answer++;
        }
        return answer;
    }

    static class Time{
        int start;  //시작 시간
        int end;    //종료 시간

        public Time(int start, int end){
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        String[][] book = {{"11:01", "17:27"}, {"04:10", "04:20"}, {"07:59", "08:59"}, {"09:10", "10:49"}};
        System.out.println(solution(book));
    }
}
