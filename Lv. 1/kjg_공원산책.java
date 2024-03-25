import java.util.*;

public class kjg_공원산책 {
    // 위치 리팩토링, cluade 3 opus 사용, 이전 코드는 좌표 값을 hashmap에 넣을 생각을 하지 못했음.
    private static final Map<String, int[]> DIRECTIONS = new HashMap<>();
    static {
        DIRECTIONS.put("N", new int[]{-1, 0});
        DIRECTIONS.put("S", new int[]{1, 0});
        DIRECTIONS.put("W", new int[]{0, -1});
        DIRECTIONS.put("E", new int[]{0, 1});
    }

    public int[] solution(String[] park, String[] routes) {
        // 시작 위치 설정
        int[] start = findStart(park);
        int h = start[0], w = start[1];
        // 공원의 최대 높이, 너비 설정, 문자열의 길이는 length()를 사용
        int hmax = park.length, wmax = park[0].length();

        // 경로대로 이동
        for (String route : routes) {
            // 공백으로 방향과, 이동 거리를 구분해서 받음
            String[] operate = route.split(" ");
            String op = operate[0];
            // parseInt로 문자열을 int형으로 변환
            int len = Integer.parseInt(operate[1]);

            // 이동 방향 설정
            int[] direction = DIRECTIONS.get(op);
            int dh = direction[0], dw = direction[1];
            // 이동한 새로운 위치
            int newH = h + dh * len, newW = w + dw * len;

            // 이동한 위치가 적절한지 판단
            if (isValidMove(park, hmax, wmax, h, w, dh, dw, len)) {
                // 허용 범위 내 이동이었다면 새로운 위치로 이동
                h = newH;
                w = newW;
            }
        }

        // 도착 위치 좌표 반환
        return new int[]{h, w};
    }

    // 시작 위치 찾기, cluade 3 opus 사용, stream api로 최적화하여 가독성 높임
    private int[] findStart(String[] park) {
        return Arrays.stream(park)
                .flatMapToInt(String::chars)
                .filter(c -> c == 'S')
                .findFirst()
                .stream()
                .mapToObj(c -> new int[]{Arrays.asList(park).indexOf(new String(new char[]{(char) c})), park[Arrays.asList(park).indexOf(new String(new char[]{(char) c}))].indexOf((char) c)})
                .findFirst()
                .orElse(new int[]{0, 0});
    }

    // 이동이 가능한지 판단, cluade 3 opus 사용, stream api로 최적화하여 가독성 높임
    private boolean isValidMove(String[] park, int hmax, int wmax, int h, int w, int dh, int dw, int len) {
        return Arrays.stream(park)
                .limit(h + dh * len + 1)
                .skip(h + dh * len)
                .flatMapToInt(String::chars)
                .limit(w + dw * len + 1)
                .skip(w + dw * len)
                .allMatch(c -> c != 'X');
    }
}