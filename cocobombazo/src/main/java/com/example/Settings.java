package com.example;

public class Settings {

    private static double windowHeight = 1100;
    private static double windowWdth = 950;
    private static int rows = 15;
    private static int columns = 15;
    private static double margin = 25.0;
    private static int bombs = 30;
    private static int startFields = 30; // first click shows nb of startFields fields -> difficulty depends on
                                         // startFields

    public static void setBombs(int bombs) {
        Settings.bombs = bombs;
    }

    public static void setColumns(int columns) {
        Settings.columns = columns;
    }

    public static void setRows(int rows) {
        Settings.rows = rows;
    }

    public static void setStartFields(int startFields) {
        Settings.startFields = startFields;
    }

    public static int getBombs() {
        return bombs;
    }

    public static int getStartFields() {
        return startFields;
    }

    public static double getMargin() {
        return margin;
    }

    public static int getRows() {
        return rows;
    }

    public static int getColumns() {
        return columns;
    }

    public static double getWindowHeight() {
        return windowHeight;
    }

    public static double getWindowWdth() {
        return windowWdth;
    }

}
