import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

class DiaryEntry {
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private static int lastId = 0;
    private final static String DATA_DIRECTORY_NAME = "data";

    private int id;
    private final LocalDateTime createdAt;
    private String title, content;

    DiaryEntry(String title, String content) {
        this(++lastId, LocalDateTime.now(), title, content);
    }

    DiaryEntry(int id, LocalDateTime createdAt, String title, String content) {
        this.id = id;
        this.createdAt = createdAt;
        this.title = title;
        this.content = content;
    }

    public static List<DiaryEntry> loadAll() {
        List<DiaryEntry> entryList = new LinkedList<>();
        List<List<String>> entryStringsList = StorageManager.directoryChildrenLines(DATA_DIRECTORY_NAME);

        for (List<String> entryStrings : entryStringsList) {
            int id = Integer.parseInt(entryStrings.get(0));
            LocalDateTime createdAt = parseDateTimeString(entryStrings.get(1));
            String title = entryStrings.get(2),
                content = entryStrings.get(3);
            entryList.add(new DiaryEntry(id, createdAt, title, content));
        }
        return entryList;
    }

    public String simpleString() {
        return String.format("id: %d, created at: %s, title: %s", id, getDateTimeString(), title);
    }

    @Override
    public String toString() {
        return String.format("\tid: %d\n\tcreated at: %s\n\ttitle: %s\n\tcontent: %s", id, getDateTimeString(), title, content);
    }

    private static LocalDateTime parseDateTimeString(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString);
    }

    private String getDateTimeString() {
        return createdAt.format(formatter);
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void save() {
        List<String> fieldString = new LinkedList<>();
//        fieldString.add(((Integer) id).toString());
        fieldString.add("" + id);
        fieldString.add("" + createdAt);
        fieldString.add(title);
        fieldString.add(content);

        StorageManager.writeLines(getFileName(), fieldString);
    }

    public String getFileName() {
        return String.format("%s/%02d.txt", DATA_DIRECTORY_NAME, id);  // "data/01.txt"
    }

    public void delete() {
        StorageManager.deleteFile(getFileName());
    }
}
