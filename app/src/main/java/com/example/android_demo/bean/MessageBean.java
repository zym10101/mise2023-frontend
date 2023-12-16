package com.example.android_demo.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author SummCoder
 * @date 2023/12/9 21:57
 */
public class MessageBean {
    private String code;
    private String message;
    private List<Application> data;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<Application> getData() {
        return data;
    }

    public static class Application implements Serializable {

        private String userId;
        private String userName;

        private String photo;
        private CommunityVO communityVO;
        public CommunityVO getCommunityVO(){
            return communityVO;
        }

        public void setCommunityVO(CommunityVO communityVO) {
            this.communityVO = communityVO;
        }

        public String getUserId(){
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo){
            this.photo = photo;
        }

    }
}
