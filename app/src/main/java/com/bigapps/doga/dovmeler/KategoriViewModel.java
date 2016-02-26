package com.bigapps.doga.dovmeler;

/**
 * Created by shadyfade on 26.02.2016.
 */
public class KategoriViewModel {
    private String text;
    private String imageUrl;

    protected KategoriViewModel(String text, String imageUrl) {
        this.text = text;
        this.imageUrl = imageUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
