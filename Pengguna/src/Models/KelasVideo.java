package Models;

public class KelasVideo {
    private int id, idKelas, orderVideo;
    private String judulVideo, linkVideo;

    public KelasVideo(int id, int idKelas, int orderVideo, String judulVideo, String linkVideo) {
        this.id = id;
        this.idKelas = idKelas;
        this.orderVideo = orderVideo;
        this.judulVideo = judulVideo;
        this.linkVideo = linkVideo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdKelas() {
        return idKelas;
    }

    public void setIdKelas(int idKelas) {
        this.idKelas = idKelas;
    }

    public int getOrderVideo() {
        return orderVideo;
    }

    public void setOrderVideo(int orderVideo) {
        this.orderVideo = orderVideo;
    }

    public String getJudulVideo() {
        return judulVideo;
    }

    public void setJudulVideo(String judulVideo) {
        this.judulVideo = judulVideo;
    }

    public String getLinkVideo() {
        return linkVideo;
    }

    public void setLinkVideo(String linkVideo) {
        this.linkVideo = linkVideo;
    }
}
