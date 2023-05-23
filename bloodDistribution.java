package main;

import java.io.File;
import java.util.Scanner;

public class bloodDistribution {
    public static void main(String[] args) throws Exception {
        int[] blood = new int[8];
        int[] patient = new int[8];

        File input = new File("s4.3.in");
        Scanner sc = new Scanner(input);
        
        for(int x = 0; x < 8; x++){
            blood[x] = sc.nextInt();
            //System.out.print(blood[x] + " ");
        }
        sc.nextLine();
        for(int x = 0; x < 8; x++){
            patient[x] = sc.nextInt();
            //System.out.print(patient[x] + " ");
        }


        System.out.println(findBloodDistribution(blood, patient));
    }

    public static int findBloodDistribution(int[] blood, int[] patient){
        int total = 0;


        for(int x = 0; x < patient.length; x++){
            if(blood[x] > patient[x]){
                total += patient[x];
                blood[x] -= patient[x];
                patient[x] = 0;
            }else{
                total += blood[x];
                patient[x] -= blood[x];
                blood[x] = 0;
            }
            if(x%2 != 0){
                if(blood[x-1] > 0){
                    if(blood[x-1] > patient[x]){
                        total += patient[x];
                        blood[x-1] -= patient[x];
                        patient[x] = 0;
                    }else{
                        total+=blood[x-1];
                        patient[x] -= blood[x-1];
                        blood[x-1] = 0;
                    }
                }
            }

        }

        int bloodNegative = patient[2] + patient[4];
        int bloodPositive = patient[3] + patient[5];

        if(bloodNegative > 0 && blood[0] > 0){
            if(blood[0] > bloodNegative){
                total += bloodNegative;
                blood[0] -= bloodNegative;
                bloodNegative = 0;
            }else{
                total += blood[0];
                bloodNegative -= blood[0];
                blood[0] = 0;
            }
        }
        
        // AB negative
        if(patient[6] > 0){
            for(int x = 0; x < blood.length - 2; x+=2){
                if(blood[x] > 0){
                    if(patient[6] > blood[x]){
                        total += blood[x];
                        patient[6] -= blood[x];
                        blood[x] = 0;
                    }else{
                        total += patient[6];
                        blood[x] -= patient[6];
                        patient[6] = 0;
                    }
                }
            }
        }


        if(bloodPositive > 0 && blood[1] > 0){
            if(blood[1] > bloodPositive){
                total += bloodPositive;
                blood[1] -= bloodPositive;
                bloodPositive = 0;
            }else{
                total += blood[1];
                bloodPositive -= blood[1];
                blood[1] = 0;
            }
            if(blood[0] > 0){
                if(blood[0] > bloodPositive){
                    total += bloodPositive;
                    blood[0] -= bloodPositive;
                    bloodPositive = 0;
                }else{
                    total += blood[0];
                    bloodPositive -= blood[0];
                    blood[0] = 0;
                }
            }
        }


        // AB positive
        if(patient[7] > 0){
            for(int x = 0; x < blood.length - 2; x++){
                if(blood[x] > 0){
                    if(patient[7] > blood[x]){
                        total += blood[x];
                        patient[7] -= blood[x];
                        blood[x] = 0;
                    }else{
                        total += patient[7];
                        blood[x] -= patient[7];
                        patient[7] = 0;
                    }
                }
            }
        }


        return total;
        
    }
}
