package mission.view;

import java.util.Scanner;
import mission.model.Fish;
import mission.model.Pool;
import mission.service.Service;

public class View {
    private Scanner scanner = new Scanner(System.in);

    public String firstInput(){
        System.out.print("물고기 비로 내릴 물고기를 입력해주세요. (ex. [정어리-5],[고등어-2])\n> ");
        return scanner.nextLine();
    }
    public void lastOutput(int num){
        System.out.println(num+"일간 생존했습니다.");
    }
    public void todayOutput(Pool pool){
        int[] fishNumList = pool.getNum();

        System.out.println("============Day"+pool.getDay()+"============");
        for(int i = 0; i < fishNumList.length; i++){
            System.out.println(Fish.Type.values()[i]+": "+fishNumList[i]);
        }
        System.out.println("==============================================\n");
    }
}
