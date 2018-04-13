package edu.android.lec06_event;

/**
 * 이미지의 아이디와 이미지 이름을 저장
 * Created by user on 2018-03-14.
 */

public class Ball {
    //멤버 변수(필드)
    private int imagedId;
    private String name;

    //생성자 (멤버변수 있는 생성자)
    //Alt + Insert : Generate( 코드 자동 완성) :  생성자, getter/setter. override.....


    public Ball(int imagedId, String name) {
        this.imagedId = imagedId;
        this.name = name;
    }

    public int getImagedId() {
        return imagedId;
    }

    public void setImagedId(int imagedId) {
        this.imagedId = imagedId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Ball{" +
                "imagedId=" + imagedId +
                ", name='" + name + '\'' +
                '}';
    }
}
