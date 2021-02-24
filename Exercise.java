package onlineExercise;


//Total calories burned = Duration (in minutes)*(MET*3.5*weight in kg)/200

import java.util.EnumMap;

public class Exercise {
    //int MET;
    public String exer_name;

    public enum exer_intensity{
        BASIC, MODERATE, HEAVY
    }

    EnumMap<exer_intensity, Integer> metMap = new EnumMap<>(exer_intensity.class);

    public Exercise(String exer_name,int metbasic, int metmoderate, int metheavy){
        this.exer_name = exer_name;
        metMap.put(exer_intensity.BASIC, metbasic);
        metMap.put(exer_intensity.MODERATE, metmoderate);
        metMap.put(exer_intensity.HEAVY, metheavy);
    }

    public int calculate_calories(exer_intensity intensity, int weight, int duration){
        return (int) (duration * (metMap.get(intensity) * 3.5 * weight)/200);
    }
}
