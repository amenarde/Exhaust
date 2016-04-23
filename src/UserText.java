
public enum UserText {
    TITLE_TEXT("<html><p><center>Exhaust</center></p></html>"),
    INSTRUCTIONS("<html><p>" + "<center>The goal of the game is simple; navigate your ship from its start location to "
                             + "the space station. You only have limited fuel, so you may have to take advantage "
                             + "of the gravitational field of celestial bodies to send you along a useful trajectory. "
                             + "But be careful, crashing into a planet is a great way to meet an untimely end.</p>"
                             + "<p></p><p>Press \"PLAY\" to play!</p></center>"
            + "</p></html>");
    private final String text;
    
    private UserText(final String text) {
        this.text = text;
    }
    
    public String getText() {
        return new String(text);
    }
}
