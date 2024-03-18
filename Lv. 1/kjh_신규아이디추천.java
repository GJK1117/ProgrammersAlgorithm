package programmers;

public class kjh_신규아이디추천 {
    public static String solution(String new_id) {
        String answer = "";
        String[] filter = {"~","!","@","#","$","%","^","&","*","(",")","=","+","[","{","]","}",":","?",",","<",">","/"};
        //1단계
        new_id = new_id.toLowerCase();
        String[] new_id_list = new_id.split("");
        //2단계
        for(int i=0; i<filter.length; i++){
            if(new_id.contains(filter[i])) new_id = new_id.replace(filter[i], "");
        }
        //3단계
        boolean sta = true;
        while(sta){
            if(new_id.contains("...")) new_id = new_id.replace("...", ".");
            else if(new_id.contains("..")) new_id = new_id.replace("..", ".");
            else sta = false;
        }
        //4단계
        new_id = removeFirstEnd(new_id);
        //5단계
        if(new_id.length()==0) new_id="a";
        //6단계
        if(new_id.length()>15) {
            new_id = new_id.substring(0, 15);
            new_id = removeFirstEnd(new_id);
        }
        //7단계
        if(new_id.length()<=2) {
            String s = new_id.substring(new_id.length()-1,new_id.length());
            while(new_id.length()<3) {
                new_id += s;
            }
        }
        return new_id;
    }

    //맨앞, 맨뒤 . 문자 지우는 메소드
    static String removeFirstEnd(String str){
        String[] strs = str.split("");
        int len = str.length();
        if(strs[0].equals(".")) {
            str = str.substring(1, str.length());
            if(str.length()<=0) return str;
        }
        if(strs[len-1].equals(".")) str = str.substring(0, str.length()-1);
        return str;
    }

    public static void main(String[] args) {
        String new_id = "...!@BaT#*..y.abcdefghijklm";
        System.out.println(solution(new_id));
    }
}
