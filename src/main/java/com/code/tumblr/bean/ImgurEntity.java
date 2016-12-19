package main.java.com.code.tumblr.bean;

/**
 * Created by Code.Ai on 16/7/17.
 */
public class ImgurEntity {
    private Boolean looping;
    private Integer width;
    private Integer height;
    private Long size;
    private String url;
    private Boolean forceGif;
    private Boolean prefer_video;
    private String hash;

    public Boolean getLooping() {
        return looping;
    }

    public void setLooping(Boolean looping) {
        this.looping = looping;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getForceGif() {
        return forceGif;
    }

    public void setForceGif(Boolean forceGif) {
        this.forceGif = forceGif;
    }

    public Boolean getPrefer_video() {
        return prefer_video;
    }

    public void setPrefer_video(Boolean prefer_video) {
        this.prefer_video = prefer_video;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
