package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.model.postcontent;

import com.hcmute.oosd.project.socialmediabackend.domain.exception.ServiceExceptionFactory;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TextAndImagePostContent extends PostContentBase {

    @Data
    public class TextAndImagePostData {
        private List<String> images, contents;

        public TextAndImagePostData() {
            this.images = new ArrayList<>();
            this.contents = new ArrayList<>();
        }
    }

    private TextAndImagePostData data;


    public TextAndImagePostContent() {
        super();
        this.data = new TextAndImagePostData();
    }


    public void testImage(){
        System.out.println(this.data.getImages());
    }

    public void addContent(String content){
        this.data.contents.add(content);
    }

    public void addImage(String imageUrl){
        this.data.images.add(imageUrl);
    }

    @Override
    public boolean validate(Boolean throwWhenFailed) {
        if (this.data == null) {
            if (throwWhenFailed)
                throw ServiceExceptionFactory.badRequest()
                        .addMessage("Noi dung bai dang khong duoc trong");
            return false;
        }
        if (this.data.contents == null || this.data.contents.size() == 0) {
            if (throwWhenFailed)
                throw ServiceExceptionFactory.badRequest()
                        .addMessage("Noi dung bai dang khong duoc trong");
            return false;
        }
        if (this.data.images == null || this.data.images.size() == 0) {
            if (throwWhenFailed)
                throw ServiceExceptionFactory.badRequest()
                        .addMessage("Phai co it nhat mot hinh anh");
        }
        return true;
    }

}
