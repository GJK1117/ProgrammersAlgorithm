package programmers;

import java.util.*;
class kjh_데이터분석 {
    public static Map<String, Integer> extLoc;
    public static String sortBy;
    public static Queue<Data> pQueue = new PriorityQueue<>();
    public static List<int[]> solution(int[][] data, String ext, int val_ext, String sort_by) {
        List<int[]> answer = new ArrayList<>();
        Map<String, Integer> extLoc = new HashMap<>();
        String[] extVal = {"code", "date", "maximum", "remain"};
        //각 문자열 마다 인덱스 부여
        for(int i=0; i<4; i++) extLoc.put(extVal[i], i);

        //뽑아낼 데이터의 인덱스 값
        Integer extIdx = extLoc.get(ext);
        for(int i=0; i<data.length; i++){
            //기준값 보다 작아서 조건에 충족된다면 우선순위 큐에 넣어줌
            if(data[i][extIdx] < val_ext) {
                pQueue.offer(new Data(data[i], extLoc.get(sort_by)));
            }
        }

        //우선순위 큐에 정렬되어 저장된 리스트들을 answer에 넣어줌
        while(!pQueue.isEmpty()){
            answer.add(pQueue.poll().data);
        }
        return answer;
    }

    static class Data implements Comparable<Data>{
        int[] data; //데이터 리스트
        int idx;    //data 배열에서 정렬된 기준 값의 위치

        public Data(int[] data, int idx){
            this.data = data;
            this.idx = idx;
        }

        @Override
        public int compareTo(Data o){
            if (o.data[o.idx]<this.data[this.idx]) return 1;
            else if (o.data[o.idx]>this.data[this.idx]) return -1;
            return 0;
        }
    }

    public static void main(String[] args) {
        int[][] data = {{1, 20300104, 100, 80}, {2, 20300804, 847, 37}, {3, 20300401, 10, 8}};
        String ext = "date";
        int val_ext = 20300501;
        String sort_by = "remain";
        List<int[]> solution = solution(data, ext, val_ext, sort_by);
        for (int[] ints : solution) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
