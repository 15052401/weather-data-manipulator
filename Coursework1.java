/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework1;
import java.util.*;
/**
 *
 * @author Edward
 */
public class Coursework1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        These initialize the variables that will be used to store the data
        boolean exit = false;
        int number;
        Scanner kb = new Scanner(System.in);
        double[] temp, rainfall;
        String[] months = new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        temp = new double[13];
        rainfall = new double[13];        
//        These get the data values for all the months for rainfall and tempreatrue from the user and checks that they are valid
        System.out.println("Please enter the total rainfall for each month of the year (mm)");
        for (int i=0; i<12; i++){
            input_value(300,0,months,rainfall,"'s total rainfall :",i);
        }
        
        System.out.println("Please enter the highest tempreature for each month of the year");
        for (int i=0; i<12; i++){    
            input_value(60,-30,months,temp,"'s highest tempreature :",i);
        }
        
       while (exit==false){
//           This displays the menu options and prompts the user for an input
          System.out.println("#####################################");
          System.out.println("Main menu \n"
                  +"0: Exit system \n"+"1: Display data \n"+"2: Change a tempreature value \n"+
                  "3: Change a rainfall value \n"+"4: Display total rainfall \n"+"5: Display mean tempreature and rainfall for the year \n"
                  +"6: Display months when the tempreature was below 3°C \n"+"7: Display the months when rainfall was below drought level (an average of 0.75mm a day) \n"+
                  "8: Display months with the highest tempreatures and the months with the lowest tempreatures \n"+
                  "9: Display the wettest and the driest months \n"+"Selection :");
          number = kb.nextInt();
          System.out.println("#####################################");
          
        switch (number)
//            This switch contains all the options displayed in the menu
        {
            case 0:
//                This exits the while loop
              exit=true;
              System.out.println("System exited");
              break;

            case 1:
//                This displays all the inputed data about rainfall and tempreature
                System.out.println("The highest tempreatures for each month were :");
                display_all_data(temp,"°C",months);
                System.out.println("\n"+"The total rainfall for each month were :");
                display_all_data(rainfall,"mm",months);

              break;
            
            case 2:
//                This asks the user for a month to change the tempreature and checks the input is valid and then changes it
                System.out.println("Please enter the number of the month would you like to change: ");
                int choice = kb.nextInt();
                input_value(60,-30,months,temp,"'s highest tempreature :",(choice-1));
              break;
            
            case 3:
//                This asks the user for a month to change the rainfall and checks the input is valid and then changes it
                System.out.println("Please enter the number of the month would you like to change: ");
                choice = kb.nextInt();
                input_value(300,0,months,rainfall,"'s total rainfall :",(choice-1));
              break;
              
            case 4:
//                This displays the total rainfall for the year
                System.out.println("The total rainfall for the year was :"+return_total(rainfall)+"mm");
              break;
            
            case 5:
//                This prints the average tempreature and rain fall for the year
                System.out.println("The avergae tempreature for the year was :"+return_average(return_total(temp)));
                System.out.println("The avergae rainfall for the year was :"+return_average(return_total(rainfall)));
              break;
             
            case 6:
//                This prints the months and tempreatures that were below 3°C
                System.out.println("The months where the tempreature was below 3°C were: ");
//                int[] locations;
                display_specific_data(temp,"°C",months,find_lower_data(temp,3));
              break;
              
            case 7:
//                This prints the months and rainfalls that were below 0.75mm a day
                System.out.println("The months where the total rainfall was below 0.75mm a day were: ");
                display_specific_data(rainfall,"mm",months,find_lower_data(rainfall,22.5));               
              break;
              
            case 8:
//                This displays the month(s) with the highest and lowest tempreature values
                System.out.println("The months with the lowest tempreatures are :");
                display_specific_data(temp,"°C",months,find_extream_low(temp));
                System.out.println("The months with the highest tempreatures are :");
                display_specific_data(temp,"°C",months,find_extream_high(temp)); 
              break;
              
            case 9:
//                This displays the month(s) with the highest and lowest rainfall values
                System.out.println("The months with the least rainfall are :");
                display_specific_data(rainfall,"mm",months,find_extream_low(rainfall));
                System.out.println("The months with the most rainfall are :");
                display_specific_data(rainfall,"mm",months,find_extream_high(rainfall));
              break;

           default:
//              This notifys the user of an invalid input
              System.out.println("Incorrect selection, please enter a vaild choice \n"+"#####################################");
        }
    }
    }
    
    public static int[] find_lower_data(double data[], double value){
//        This finds the values in an array that are below a given value and returns an array of their locations
        int[] results;
        results = new int[13];
        int counter=0;
       
        for (int i=0;i<12;i++){
            if (data[i]<value){
                results[counter]=i;
                counter+=1;
                }   
            }

        results[counter]= -1;
        return results;  
    }
    
    public static int[] find_extream_low(double[] data){
        //        This finds the lowest value(s) in an array and returns their locations
        int[] locations;
        locations = new int[13];
        int counter=0;
       
        for (int i=1;i<12;i++){
            if (data[i]<data[locations[counter]]){
                locations = new int[13];
                locations[0]=i;
                counter=0;
            }
        
            else if(data[i]==data[locations[counter]]){
                counter +=1;
                locations[counter]=i;
            }
        }   
        locations[counter+1]= -1;
        return locations;
    }
    
    public static int[] find_extream_high(double[] data){
//        This finds the highest value(s) in an array and returns their locations
        int[] locations;
        locations = new int[12];
        int counter=0;
       
        for (int i=1;i<12;i++){
            if (data[i]>data[locations[counter]]){
                locations = new int[12];
                locations[0]=i;
                counter=0;
            }
        
            else if(data[i]==data[locations[counter]]){
                counter +=1;
                locations[counter]=i;
            }
        }   
        locations[counter+1]= -1;
        return locations;
    }
    
    public static void display_all_data(double[] data, String unit,String[] date){
//        This takes an array and displays it along with the month and the unit of the data
            for (int i = 0 ; i<12; i++){
                if (data[i]!= 0){
                    System.out.println(date[i]+": "+data[i]+unit);
                }
            }
    }
    
    public static void display_specific_data(double[] data,String unit,String[] date,int[] location){
//        This funcation takes an array and prints out the useful data
        int counter=0;
        while (location[counter] !=-1){
            System.out.println(date[location[counter]]+": "+data[location[counter]]+unit);
            counter+=1;
        }
    }            
    
    public static double return_total(double[] data){
//        This function takes a array and calculates the total of the values
        double total=0;
        for (int i=0; i<12; i++){
            total += data[i];
        }
        return total;
    }
    
    public static double return_average(double total){
//        This function calculates and returns the avergae
        double average = total/12;
        return average;
    }
    
    public static double[] input_value(double upper_bound,double lower_bound, String[] months, double[] data,String message,int month){       
//        This functions gets inputs from the user and stores it in the position of a array passed to it after checking that it is in the desierd range and data type
        Scanner kb = new Scanner(System.in);
            boolean valid=false;
            System.out.println("Please enter "+months[month]+ message );
            while (valid==false){
                if (kb.hasNextDouble()){
                    double value = kb.nextDouble();
                    if (value<upper_bound && value>lower_bound){
                        data[month]=value;
                        valid=true;
                    }
                    else{
                        System.out.println("Invalid entry, please try again: ");
                    }
                }
                else{
                    System.out.println("Invalid entry, please try again: ");
                    kb.next();
                }
            }
        return data;
    }
} 