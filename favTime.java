package main;

import java.io.File;
import java.util.Scanner;

public class favTime {
    public static void main(String[] args) throws Exception {
        
        File num = new File("j4.01.in");
        Scanner sc = new Scanner(num);
        
        int input = sc.nextInt();
        System.out.println(input);
        
        System.out.println(findTotalFavTime(input));

    }

    public static int findTotalFavTime(int input){

        // 12hour = 720min
        int remainTime = input%720;
        int hour = 12;
        int minute = 0;
        int total = 0;

        for (int timePassed = 0; timePassed < remainTime; timePassed++){
            if(minute == 59){
                minute = 0;
                if(hour == 12){
                    hour = 1;
                }else{
                    hour++;
                }
            }else{
                minute++;
            }

            int time = hour * 100 + minute;
            if(arithmetic(time)){
                total++;
            }
        }

        // Multiply by 31 because there are a total of 31 special cases in 12 hours (720min)
        // Math.floorDiv
        return total + (input/720 * 31);        
    }

    public static boolean arithmetic(int time){
        int thousands = 0;
        int hundreds = 0;
        int tenth = 0;
        int ones = 0;
        
        if(time > 999){
            thousands = 1;
            hundreds = (time - 1000) / 100;
            tenth = (time - 1000 - (hundreds*100)) / 10;
            ones = time % 10;
             if ((thousands - hundreds) == (hundreds - tenth) && (hundreds - tenth) == (tenth - ones)){
                return true;
            }

        }else{
            hundreds = time / 100;
            tenth = (time - (hundreds*100)) / 10;
            ones = time % 10;

             if((hundreds - tenth) == (tenth - ones)){
                return true;
            }
        }



        return false;
    }


}
