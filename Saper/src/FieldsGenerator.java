import java.lang.reflect.Array;
import java.util.ArrayList;

public class FieldsGenerator {

    // -1 - bomb    0-none  1- one bomb in neighborhood .... 
    private static int actualField = 0;


    private ArrayList<Integer> generatedFields = new ArrayList<>();
    private ArrayList<Integer> unvisibleFields = new ArrayList<>(); //1 - in game 0 - out of game

    public FieldsGenerator(){

    }

    public static int getActualField() {
    return actualField;
    }

    public static void setActualField(int actualField) {
        FieldsGenerator.actualField = actualField;
    }
}
