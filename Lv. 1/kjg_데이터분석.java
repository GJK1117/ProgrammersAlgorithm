import java.util.*;

public class kjg_데이터분석 {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        // data의 index와 문자열 매칭
        HashMap<String, Integer> map = new HashMap<>(){{
            put("code", 0);
            put("date", 1);
            put("maximum", 2);
            put("remain", 3);
        }};
        // 비교하고자 하는 값, 정렬하고자 하는 값의 index 정의
        int ext_idx = map.get(ext);
        int sort_idx = map.get(sort_by);

        // 원하는 데이터만 추출할 임시 리스트
        List<int[]> tmp = new ArrayList<>();;
        // 제거(넣고 싶은 데이터만 answer에 추가)
        for(int[] row : data){
            if(row[ext_idx] < val_ext){
                tmp.add(row);
            }
        }

        // 정렬하고자 하는 값을 기준으로 행을 오름차순 정렬
        tmp.sort(Comparator.comparingInt(o -> o[sort_idx]));

        // 정답 배열 정의
        int[][] answer = new int[tmp.size()][];

        // 임시 리스트에 있는 데이터를 정답 배열에 추가
        for(int i = 0; i < tmp.size(); i++){
            answer[i] = tmp.get(i);
        }

        // 정답 반환
        return answer;
    }
}
