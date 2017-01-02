package opensource.haptik.task.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Rajan Maurya on 02/01/17.
 */

public class Message implements Parcelable {

    @SerializedName("body")
    String body;

    @SerializedName("username")
    String userName;

    @SerializedName("image-url")
    String imageUrl;

    @SerializedName("message-time")
    String messageTime;

    @SerializedName("is_favourite")
    Boolean isFavourite;

    public Boolean getFavourite() {
        return isFavourite;
    }

    public void setFavourite(Boolean favourite) {
        isFavourite = favourite;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "body='" + body + '\'' +
                ", userName='" + userName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", messageTime='" + messageTime + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.body);
        dest.writeString(this.userName);
        dest.writeString(this.imageUrl);
        dest.writeString(this.messageTime);
    }

    public Message() {
    }

    protected Message(Parcel in) {
        this.body = in.readString();
        this.userName = in.readString();
        this.imageUrl = in.readString();
        this.messageTime = in.readString();
    }

    public static final Parcelable.Creator<Message> CREATOR = new Parcelable.Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel source) {
            return new Message(source);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };
}
