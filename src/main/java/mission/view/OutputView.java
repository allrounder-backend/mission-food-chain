package mission.view;

public class OutputView {

    public static void resultOutput(int days){
        System.out.println(days + "일간 생존했습니다.");
    }

    public static void errorOutput(String message){
        System.out.println("오류 : " + message);
    }
}
