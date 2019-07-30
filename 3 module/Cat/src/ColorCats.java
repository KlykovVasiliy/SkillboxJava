public enum ColorCats {
    WHITE("Белый"),
    WHITE_BLACK("Белый и черный"),
    WHITE_BLACK_REDHEAD("Белый, черный и рыжий"),
    READHEAD("Рыжий"),
    BLACK("Черный"),
    GRAY("Серый"),
    ;

    private String coatColor;

    ColorCats(String coatColor) {
        this.coatColor = coatColor;
    }


    @Override
    public String toString() {
        return coatColor + " цвет шерсти у кота.";
    }
}
