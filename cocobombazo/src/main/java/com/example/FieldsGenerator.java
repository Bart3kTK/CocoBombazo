package com.example;
import java.util.ArrayList;
import java.util.Random;

public class FieldsGenerator {

    private static int actualField = -1;
    private static ArrayList<Integer> generatedStartFields = new ArrayList<>();
    private static int[][] generatedField = new int[Settings.getColumns()][Settings.getRows()];
    private static Random random = new Random();


    public static void genBombAndNumbers()
    {
        ArrayList<Integer> sFields = generatedStartFields;
        int [][] bombAndOther = new int[Settings.getColumns()][Settings.getRows()];
        int bombs = 0;

        /* setting start index (-2 is possible place to place bomb) */
        for(int a = 0; a < Settings.getColumns(); a++)
        {
            for(int b = 0; b < Settings.getRows(); b++)
            {
                bombAndOther[a][b] = -2;
            }
        }

        /* Setting start fields (100% no bomb) and protecting their neighbours */
        for(int b : sFields)
        {
            int actualCol = b % Settings.getColumns();
            int actualRow = b / Settings.getColumns();
            bombAndOther[actualCol][actualRow] = 10;

            for (int n : getNeighbors(b))
            {

                int actualNCol = n % Settings.getColumns();
                int actualNRow = n / Settings.getColumns();

                if(bombAndOther[actualNCol][actualNRow] != 10)
                    bombAndOther[actualNCol][actualNRow] = -3;
            }
        }

        /* Putting bombs in available gaps */
        while (bombs < Settings.getBombs())
        {
            int randomCol = random.nextInt(Settings.getColumns());
            int randomRow = random.nextInt(Settings.getRows());

            if (bombAndOther[randomCol][randomRow] == -2){
                bombAndOther[randomCol][randomRow] = -1;
                bombs ++;
            }
        }

        /* Counting bombs in neighbourhood and setting this result*/
        for(int a = 0; a < Settings.getColumns(); a++)
        {
            for(int b = 0; b < Settings.getRows(); b++)
            {
                if(bombAndOther[a][b] == 10 || bombAndOther[a][b] == -1) continue;
                
                int counter = 0;

                for (int e : getNeighbors(b*Settings.getColumns() + a)){
                    if(bombAndOther[e % Settings.getColumns()][e / Settings.getColumns()] == -1) counter++;
                }
                bombAndOther[a][b] = counter;
            }
        }
        generatedField = bombAndOther;
    }


    /* Generating random start fields in first click neighbourhood  */
    public static void generateStartFields()
    {
        int point = actualField;

        if (actualField >= 0)
        {
            ArrayList<Integer> startFields = new ArrayList<>();
            
            while(Settings.getStartFields() >= startFields.size())
            {
                for (int id : getNeighbors(point))
                {
                    if (!startFields.contains(id))
                    {
                        startFields.add(id);
                        continue;
                    }
                }
                point = startFields.get(random.nextInt(startFields.size()));
            }
            generatedStartFields = startFields;
        }
        else generatedStartFields = null;
    }


    /* It returns ArrayList of int index of neighbours */
    public static ArrayList<Integer> getNeighbors(int index)
    {
        ArrayList<Integer> n = new ArrayList<>();
        int row = index / Settings.getColumns() - 1;
        int column = index % Settings.getColumns() - 1;

        for(int a = 1 ; a < 10 ; a++)
        {
            if(column >= 0 && column < Settings.getColumns() && row >= 0 && row < Settings.getRows())
                n.add(row*Settings.getRows() + column);

            if(a % 3 == 0)
            {
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
    public static int[][] getGeneratedFields() {
        return generatedField;
    }
    public static ArrayList<Integer> getSFields(){
        return generatedStartFields;
    }    
    public static int getActualField() {
        return actualField;
    }
}
