import java.util.*;

public class Diary {
    private List<DiaryEntry> entryList = new LinkedList<>();
    private Map<String, List<DiaryEntry>> wordMap = new HashMap<>();

    public Diary() {
        this.entryList = DiaryEntry.loadAll();
        for (DiaryEntry entry : entryList) {
            mapContentWords(entry);
        }
    }

    public void createEntry() {
        String title = DiaryUI.input("\ttitle: "),
            content = DiaryUI.input("\tcontent: ");
        DiaryEntry entry = new DiaryEntry(title, content);
        mapContentWords(entry);
        entryList.add(entry);
        entry.save();
    }

    private void mapContentWords(DiaryEntry entry) {
        String[] words = entry.getContent().split(" ");
        for (String word : words) {
            if (wordMap.get(word) == null) {
                wordMap.put(word, new LinkedList<>());
            }
            wordMap.get(word).add(entry);
        }
    }

    public void listEntries() {
        for (int i = entryList.size() - 1; i >= 0; i--) {
            DiaryUI.print("\t" + entryList.get(i).simpleString());
        }
    }

    public void readEntry(int id) {
        for (DiaryEntry entry : entryList) {
            if (entry.getId() == id) {
                DiaryUI.print("\t" + entry);
            }
        }
    }

    public void deleteEntry(int id) {
        for (DiaryEntry entry : entryList) {
            if (entry.getId() == id) {
                entry.delete();
            }
        }
        Iterator<DiaryEntry> iterator = entryList.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove();
            }
        }
        for (List<DiaryEntry> entries : wordMap.values()) {
            Iterator<DiaryEntry> tagEntryIterator = entries.iterator();
            while (tagEntryIterator.hasNext()) {
                if (tagEntryIterator.next().getId() == id) {
                    tagEntryIterator.remove();
                }
            }
        }
    }

    public void search(String keyword) {
        Set<DiaryEntry> printedEntries = new HashSet<>();
        List<DiaryEntry> entries = wordMap.get(keyword);
        if (entries != null) {
            for (int i = entries.size() - 1; i >= 0; i--) {
                DiaryEntry entry = entries.get(i);
                if (!printedEntries.contains(entry)) {
                    System.out.println(entry);
                    printedEntries.add(entry);
                }
            }
        }
    }
}
