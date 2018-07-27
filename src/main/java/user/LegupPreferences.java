package user;

public class LegupPreferences {
    private static final String PASTE_KEY = "PASTE_KEY";
    private static final String CUT_KEY = "CUT_KEY";
    private static final String COPY_KEY = "COPY_KEY";
    private static final String REDO_KEY = "REDO_KEY";
    private static final String UNDO_KEY = "UNDO_KEY";
    private static final String SAVE_AS_KEY = "SAVE_AS_KEY";
    private static final String SAVE_KEY = "SAVE_KEY";

    private static LegupPreferences instance;
    private LegupPreferences() {

    }

    public static LegupPreferences getInstance() {
        if(instance == null) {
            instance = new LegupPreferences();
        }
        return instance;
    }

}
