package ru.daniladeveloper.markdownhelper.domain.reformat;

public class TextLine {
    private final String value;
    private boolean isContent;


    public TextLine(String s) {
        this.value = s;
        this.isContent = false;
    }

    public static TextLine justLine(String s) {
        return new TextLine(s + "\n");
    }

    public void mark() {
        this.isContent = true;
    }

    public void unmark() {
        this.isContent = false;
    }

    public String getValue() {
        return value;
    }

    public boolean isContent() {
        return isContent;
    }

    @Override
    public String toString() {
        return "value = " + value;
    }

    /**
     *  TODO: test me!
     * @return is line has structure {number}.{.*}
     */
    public boolean isOrderedListItem() {
        boolean wasDigit = false;
        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) == '.') {
                return wasDigit;
            } else if (Character.isDigit(value.charAt(i))) {
                wasDigit = true;
            }  else if (!wasDigit) {
                return false;
            }

        }
        return false;
    }

}
