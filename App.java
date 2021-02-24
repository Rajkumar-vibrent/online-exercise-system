package onlineExercise;
import java.util.Scanner;

//*
// User.java:
// -- User(name)
// -- BasicExerciseSet()
// -- addExercise(String exercise_name, int metBasic, int metModerate, int metHeavy)
// -- equals(String exercise_name)
// -- addUserExercise(String exer_name, String intensity, int duration)
// -- calculate_calories(String exer_name, String intensity, int duration)*/

public class App{

    public static void main(String args[]){
        System.out.println("Welcome!");
        //System.out.println("Enter user type(trainer/trainee): ");
        Scanner input = new Scanner(System.in);
        System.out.println("enter your name(8-20 chars): ");
        String name = retName();
        System.out.println("Enter your weight (Kg): ");
        int weight = input.nextInt();
        User person = new User(name);
        person.weight = weight;
        person.BasicExerciseSet();

        boolean Continue = true;
        while(Continue) {
            String choice = retChoice();
            switch (choice) {
                case "A":
                    System.out.println("\nTotal calories burnt by you (" + person.name + ") is: " + getCalories(person) + "\n");
                    person.calories_burnt = 0;
                    break;
                case "B":
                    addExercise(person);
                    break;
                default:
                    break;
            }
            System.out.println("want to continue? (y/n)...");
            input = new Scanner(System.in);
            String output = input.nextLine();
            if(!(output.equals("y") || output.equals("Y"))){
               Continue = false;
            }
        }
    }

    public static void addExercise(User person){
        boolean Continue = true;
        while(Continue) {
            System.out.println("Enter new exercise name: ");
            Scanner input = new Scanner(System.in);
            String exer_name = input.nextLine();
            System.out.println("Enter metabolic equivalence for all three levels (basic, moderate, heavy): ");
            int metBasic = input.nextInt();
            int metModerate = input.nextInt();
            int metHeavy = input.nextInt();
            if(person.addExercise(exer_name, metBasic, metModerate, metHeavy)){
                System.out.println("Successfully added..");
            }
            else {
                System.out.println("It seems like an exercise of this name already exists. Please enter the exercise details again");
                continue;
            }
            System.out.println("\nwant to add more...?");
            input = new Scanner(System.in);
            String output = input.nextLine();
            if(output.equals("y")||output.equals("Y")){
                continue;
            }
            else{
                Continue = false;
                person.displayExercise();
            }
        }
    }

    public static int getCalories(User person){
        boolean Continue = true;
        while(Continue) {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter exercise name: ");
            String name = input.nextLine();

            System.out.println("Enter intensity: ");
            String intensity = input.nextLine();
            if(!validateIntensity(intensity)){
                while(!validateIntensity(intensity)) {
                    System.out.println("\nEntered intensity level not defined\nSelect from following (BASIC/MODERATE/HEAVY) all upper case!");
                    intensity = input.nextLine();
                }
            }

            System.out.println("Enter duration: ");
            int duration = input.nextInt();
            if(!person.addUserExercise(name, intensity, duration)){
                System.out.println("The exercise name u added is either not registered on your profile or u have already added to today's workout list...");
                System.out.println("please try again...!\n");
            }
            System.out.println("more performed exercises to be added...?");
            input = new Scanner(System.in);
            String output = input.nextLine();

            if(output.equals("y")||output.equals("Y")){
                continue;
            }
            else{
                Continue = false;
                person.displayPerformedExercise();
            }
            person.calculate_calories();
        }
        return person.calories_burnt;
    }

    public static boolean validateIntensity(String intensity){
        for (Exercise.exer_intensity e : Exercise.exer_intensity.values()) {
            if (e.name().equals(intensity)) {
                return true;
            }
        }
        return false;
    }


    public static String retChoice(){
        System.out.println("Select from the following (input: (a/b lower or upper case)): \n(a) calculate calories\n(b) add new exercise\n");
        Scanner input = new Scanner(System.in);
        String choice = input.nextLine();
        boolean valid = false;
        while(!valid){
            if(choice.equals("A") || choice.equals("a")) choice = "A";   else if(choice.equals("b") || choice.equals("B")) choice = "B";
            if(choice.equals("A") || choice.equals("B")){
                valid = true;
            }
            else{
                System.out.println("Enter valid input (a/b lower or upper case): ");
                choice = input.nextLine();
            }
        }
        return choice;
    }

    public static String retName(){
        Scanner input = new Scanner(System.in);
        String name = null;
        boolean inputValidated = false;
        while (!inputValidated){
            try {
                name = input.nextLine();
                inputValidate(name);
                inputValidated = true;
            }
            catch (newException e){
                System.out.println(e.getMessage());
            }
        }
        return name;
    }

    public static void inputValidate(String input) throws newException{
        if(input.length() < 8 || input.length() > 20){
            throw new newException("input nickname not belonging in the size range(8-20).. \nPlease retry!");
        }
    }
}