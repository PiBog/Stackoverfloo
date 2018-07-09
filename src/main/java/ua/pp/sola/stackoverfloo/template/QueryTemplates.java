package ua.pp.sola.stackoverfloo.template;

public enum QueryTemplates {

    ALL_TOPICS("SELECT * FROM STACKOVERFLOO.TOPICS"),
    LAST_FIVE_TOPICS_DESC("SELECT * FROM STACKOVERFLOO.TOPICS ORDER BY create_date DESC LIMIT 3");



    private final String text;

    /**
     * @param text
     */
    QueryTemplates(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }

}
