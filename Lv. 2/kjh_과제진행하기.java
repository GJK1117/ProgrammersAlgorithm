package programmers;

import java.util.*;

public class kjh_과제진행하기 {
    public static List<String> solution(String[][] plans) {
        List<String> answer = new ArrayList<>();    //정답 리스트
        PriorityQueue<Work> pq = new PriorityQueue<>(); //과제 시작시간 순서대로 큐에 넣어줌
        Stack<Work> pending = new Stack<>();    //대기하는 과제을 넣을 스택
        for(int i=0; i<plans.length; i++){
            //작업 초기화
            String[] p = plans[i][1].split(":");
            int startTime = Integer.parseInt(p[0]+p[1]);
            pq.offer(new Work(plans[i][0], startTime, Integer.parseInt(plans[i][2])));
        }

        while(!pq.isEmpty()){
            Work work = pq.poll();
            //마지막 과제면 결과값 리스트에 넣고 break
            if(pq.size()==0) {
                answer.add(work.name);
                break;
            }
            //현재 과제끝나는 시간이 다음 과제의 시작시간보다 느릴 경우 -> 현재 과제를 대기상태로 놓고 다음과제를 시작
            if(work.end > pq.peek().start) {
                //다음 과제 시작시간에서 현재 과제 시작시간을 빼서 과제가 진행된 시간 계산
                int x = (pq.peek().start/100 - work.start/100)*60 + (pq.peek().start%100-work.start%100);
                work.minusPlaytime(x);  //현재 과제의 총 진행시간에서 x(진행된시간) 빼줌
                pending.push(work);     //대기 목록에 추가
            } else {    //현재 과제끝나는 시간이 다음 과제의 시작시간보다 빠를 경우
                answer.add(work.name);  //현재 과제 결과값 리스트에 저장
                //다음 과제 시작시간에서 현재과제 끝나는 시간 빼줘서 대기 상태 과제를 진행할 수 있는 시간 계산
                int x = (pq.peek().start/100 - work.end/100)*60 + (pq.peek().start%100-work.end%100);
                //대기중인 과제 진행
                while(!pending.isEmpty()){
                    //대기중인 과제 pop하여 진행시킴
                    Work w = pending.pop();
                    int a = w.playtime - x; //과제를 마치는 시간 - 진행가능한 시간
                    if(a == 0) {
                        //0일경우에는 진행중인 과제는 끝난 것으로 판단 -> 결과값 리스트에 넣고 break
                        answer.add(w.name);
                        break;
                    } else if(a>0) {
                        //0보다 클경우는 현재 진행중인 과제는 완료시키지 못함
                        //과제를 마치는 시간에서 진행가능한시간 빼준후 다시 대기목록으로 push 한 후 break
                        w.minusPlaytime(x);
                        pending.push(w);
                        break;
                    } else {
                        //0보다 작을경우는 현재 진행중인 과제를 끝내고 결과값 리스트에 추가
                        //아직 진행가능한 시간이 남았으므로 진행가능한 시간 새로 초기화한 후 계속 진행
                        answer.add(w.name);
                        x = -a;
                    }
                }
            }
        }
        //아직 대기중인 과제가 있으면 결과값 리스트에 추가
        while(!pending.isEmpty()){
            answer.add(pending.pop().name);
        }

        return answer;
    }

    static class Work implements Comparable<Work>{
        String name;    //과제의 이름
        int start;      //과제 시작 시각
        int end;        //과제 끝 시각(시작 시각 + 과제를 마치는데 걸리는 시간)
        int playtime;   //과제를 마치는데 걸리는 시간

        public Work(String name, int start, int playtime){
            this.name = name;
            this.start = start;
            this.playtime = playtime;
            //start + playtime 으로 end 계산
            this.end = (start/100+(start%100+playtime)/60)*100 + (start%100+playtime)%60;
        }

        //각 과제 진행한 시간 playtime 에서 빼줌
        public void minusPlaytime(int x){
            this.playtime -= x;
        }

        //시작 시간 기준으로 오름차순 정렬
        @Override
        public int compareTo(Work o){
            return (this.start <= o.start) ? -1 : 1;
        }
    }

    public static void main(String[] args) {
        String[][] plans = {{"korean", "11:40", "30"}, {"english", "12:10", "20"}, {"math", "12:30", "40"}};
        System.out.println(solution(plans));
    }
}
