package online.polp.colorize;

public enum Color {
    RESET("\u001B[0m"),
    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m"),
    WHITE("\u001B[37m");


    private final String color;

    Color(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }


    /**
     * Colorizes the given text with this color
     *
     * @param text the text to colorize
     * @return the colorized text
     */
    public String colorize(String text) {
        return color + text + RESET.getColor();
    }


    /**
     * Prints the given text with this color
     *
     * @param text the text to print
     */
    public void printWithColor(String text) {
        System.out.println(colorize(text));
    }
}
