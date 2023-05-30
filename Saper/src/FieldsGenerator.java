import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class FieldsGenerator {

    private static int actualField = -1;
    private static ArrayList<Integer> generatedFields = new ArrayList<>(); // -1 - bomb    0-none  1- one bomb in neighborhood .... 


    private static Random random = new Random();



    public static int getActualField() {
    return actualField;
    }
    public static void genBombAndNumbers(ArrayList<Integer> sFields){
        ArrayList<Integer> bombAndNb = new ArrayList<>();
        ArrayList<Integer> bombInposiblePlaces = new ArrayList<>();
        Set<Integer> set = new HashSet<>(bombInposiblePlaces);
        int bombs = 0;
        for (int a = 0; a < Settings.getRows() * Settings.getColumns(); a++){
            bombAndNb.add(-2);
        }
        for(int b : sFields){
            bombAndNb.set(b, 0);
        }
        for(int c : sFields){
            set.addAll(getNeighbors(c));
        }
        bombInposiblePlaces.addAll(set);
        while (bombs < Settings.getBombs()){
            int nb = random.nextInt(Settings.getRows() * Settings.getColumns());
            if (bombInposiblePlaces.contains(nb) == false){
                bombAndNb.set(nb, -1);
                bombs ++;
            }
        }
        for (int d : bombAndNb){
            if(d == 0 || d == -1) continue;

            int counter = 0;
            for (int e : getNeighbors(bombAndNb.indexOf(d))){
                if(bombAndNb.get(e) == -1) counter++;
            }
            bombAndNb.set(bombAndNb.indexOf(d) , counter);
        }
        generatedFields = bombAndNb;


    }

    public static ArrayList<Integer> generateStartFields(int actualF){
        int point = actualF;
        if (actualF >= 0){
            ArrayList<Integer> startFields = new ArrayList<>();
            
            while(Settings.getStartFields() >= startFields.size()){
                for (int id : getNeighbors(point)){
                    if (!startFields.contains(id))
                    {
                        startFields.add(id);
                        continue;
                    }
                }
                point = startFields.get(random.nextInt(startFields.size()));
            }
            return startFields;
        }
        return null;
    }

    public static ArrayList<Integer> getNeighbors(int index){
        ArrayList<Integer> n = new ArrayList<>();
        int row = index / Settings.getRows() - 1;
        int column = index % Settings.getRows() - 1;

        for(int a = 1 ; a < 10 ; a++){
            if(column >= 0 && column < Settings.getColumns() && row >= 0 && row < Settings.getRows())
                n.add(row*Settings.getRows() + column);
            if(a % 3 == 0){
                column -= 3;
                row ++;
            }
            column++;
        }
        return n;
    }

    public static void setActualField(int actualField) {
        FieldsGenerator.actualField = actualField;
    }
    public static ArrayList<Integer> getGeneratedFields() {
        return generatedFields;
    }
}
