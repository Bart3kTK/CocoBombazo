public class Settings {

    private static double windowHeight = 850;
    private static double windowWdth = 700;
    private static int rows = 10;
    private static int columns = 10;
    private static double margin = 50.0;
    private static int bombs = 10;
    private static int startFields = 15; //first click shows nb of startFields fields -> difficulty depends on startFields


    public static int getBombs() {
        return bombs;
    }

    public static int getStartFields() {
        return startFields;
    }

    public static double getMargin() {
        return margin;
    }

    public static int getRows() 
    {
        return rows;
    }

    public static int getColumns() 
    {
        return columns;
    }

    public static double getWindowHeight(){
        return windowHeight;
    }

    public static double getWindowWdth(){
        return windowWdth;
    }

    
}
