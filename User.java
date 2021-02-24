package onlineExercise;

import java.util.*;
import java.util.stream.Collectors;

import com.sun.jdi.request.ExceptionRequest;
import org.javatuples.Quartet;


public class User {
    String name;
    int weight;
    //Quartet<String, String, Integer, Integer> exerciseInput;
    public static Set<Exercise> exercises = new HashSet<>(3);
    public static Set<Quartet> userExerciseSet = new HashSet<>();
    int calories_burnt;

    //initializing User object with user name
    public User(String name){
        this.name = name;
        System.out.println(this.name);
    }


    //storing pre-defined standard data for each exercise
    public void BasicExerciseSet(){
        exercises.add(new Exercise("WALKING", 4, 6, 8));    //metabolic equivalence
        exercises.add(new Exercise("CYCLING", 7, 10, 14));
        exercises.add(new Exercise("RUNNING", 9, 13, 16));
    }

    //adding new exercise with its standard data to exercises set
    public boolean addExercise(String exercise_name, int metBasic, int metModerate, int metHeavy){
        if(uniqueExercise(exercise_name)) {
            exercises.add(new Exercise(exercise_name, metBasic, metModerate, metHeavy));
            return true;
        }
        return false;
    }

    //checking whether the newly added exercise is unique ----- should return true
    public static boolean uniqueExercise(String exer_name){
        return(!exercises.stream().anyMatch(e -> e.exer_name.equals(exer_name))) ? true : false;
    }

    //checking whether the added performed exercise is unique --- should return true if performed exercise is new
    public static boolean AddedExercise(String exer_name){
        return (!userExerciseSet.stream().anyMatch(t -> t.getValue0().equals(exer_name))) ? true : false;
    }

    //checking whether the added performed exercise do exist in the user profile --- should return true if yes
    public boolean existingExercise(String exer_name){
        return (exercises.stream().anyMatch(e -> e.exer_name.equals(exer_name))) ? true : false;
    }

    //adding exercises done on the day by the user
    public boolean addUserExercise(String exer_name, String intensity, int duration){
        if(existingExercise(exer_name) && AddedExercise(exer_name)) {
            int calories = 0;
            for (Exercise e : exercises) {
                if (e.exer_name.equals(exer_name)) {
                    calories = e.calculate_calories(Exercise.exer_intensity.valueOf(intensity), this.weight, duration);
                }
            }
            userExerciseSet.add(new Quartet<String, String, Integer, Integer>(exer_name, intensity, duration, calories));
            return true;
        }
        else return false;
    }

    //calculates total calories based on user inputs and standard data for each exercise
    public void calculate_calories(){
        //Set<Exercise> exerciseList = retMetDataSet();
        for(Quartet t : userExerciseSet){
            this.calories_burnt += (int)t.getValue3();
        }
    }

    public void displayPerformedExercise(){
        userExerciseSet.forEach(System.out::println);
    }

    public void displayExercise(){
        for(Exercise s: exercises){
            System.out.println(s.exer_name + ": " + s.metMap);
        }
    }
}
