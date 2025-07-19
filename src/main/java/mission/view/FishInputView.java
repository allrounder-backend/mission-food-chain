package mission.view;

import api.Console;

public class FishInputView {

    public String inputFish(){
        System.out.println("물고기 비로 내릴 물고기를 입력해주세요. (ex. [정어리-5],[고등어-2])");
        System.out.print(">");

        return Console.readLine();
    }
}
