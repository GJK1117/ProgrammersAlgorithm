public class kjg_바탕화면정리{
    public int[] solution(String[] wallpaper) {
        // 초기 값 설정
        int lux = 50, luy = 50, rdx = 0, rdy = 0;

        // 바탕화면 순회
        for(int i = 0; i < wallpaper.length; i++){
            for(int j = 0; j < wallpaper[0].length(); j++){
                // 파일 위치 찾음
                if(wallpaper[i].charAt(j)=='#'){
                    // 드래그할 영역을 정의, 좌상단, 우하단 좌표를 설정
                    if(lux >= i) lux = i;
                    if(luy >= j) luy = j;
                    // 우하단은 우하단에 있는 프로그램까지 포함해야 하므로, +1 처리하여 구함.
                    if(rdx < i + 1) rdx = i + 1;
                    if(rdy < j + 1) rdy = j + 1;
                }
            }
        }

        // 결과 배열 초기화 및 반환
        int[] answer = {lux, luy, rdx, rdy};
        return answer;
    }
}
