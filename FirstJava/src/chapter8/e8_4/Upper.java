package chapter8.e8_4;

public class Upper<T extends Number> {
    private T msg;

    public T getMsg() {
        return msg;
    }

    public void setMsg(T msg) {
        this.msg = msg;
    }
}