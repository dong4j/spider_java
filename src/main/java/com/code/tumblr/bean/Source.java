package main.java.com.code.tumblr.bean;

/**
 * @author Code.Ai (Email: Code.Ai@outlook.com)
 * @version 0.1
 * @link http://blog.csdn.net/codeai
 * @date 16/2/27
 * @describe
 */

public class Source {
    //视频资源
    private String videoSrc;
    //图片资源
    private String imageSrc;
    public String getVideoSrc() {
        return videoSrc;
    }
    public String getImageSrc(){
        return imageSrc;
    }

    public void setVideoSrc(String videoSrc) {
        this.videoSrc = videoSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public Source() {
    }

    public Source(String videoSrc, String imageSrc) {
        this.videoSrc = videoSrc;
        this.imageSrc = imageSrc;
    }

    @Override
    public String toString() {
        return "Source{" +
                "videoSrc='" + videoSrc + '\'' +
                ", imageSrc='" + imageSrc + '\'' +
                '}';
    }
}

