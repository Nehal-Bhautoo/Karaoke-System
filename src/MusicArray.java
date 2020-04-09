import java.util.List;

public class MusicArray {
    public static List<String> music;

    public List<String> setArray(List<String> music) {
        MusicArray.music = music;
        return music;
    }

    public static List<String> getMusic() {
        return music;
    }
}
