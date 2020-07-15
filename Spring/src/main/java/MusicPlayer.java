public class MusicPlayer {
    Music music;

    public MusicPlayer(Music music) {
        this.music = music;
    }
    public void getMusic(){
        System.out.println("Playing: "+music.getSong());
    }
}
