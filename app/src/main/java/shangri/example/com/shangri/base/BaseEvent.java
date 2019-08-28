package shangri.example.com.shangri.base;

/**
 * 接收基础消息
 * Created by chengaofu on 2017/7/18.
 */

public class BaseEvent<T> {
    private int eventId;
    private T data;

    public BaseEvent(T data){
        this.data = data;
    }

    public BaseEvent(int eventId, T data) {
        this.eventId = eventId;
        this.data = data;
    }

    public BaseEvent(int eventId) {
        this.eventId = eventId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
