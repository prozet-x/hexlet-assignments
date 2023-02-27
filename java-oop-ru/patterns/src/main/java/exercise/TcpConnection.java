package exercise;

import java.util.List;
import java.util.ArrayList;

// BEGIN
import exercise.connections.Connection;
import exercise.connections.Disconnected;

public class TcpConnection {
    private Connection state;
    private String ip;
    private int port;

    public TcpConnection(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.state = new Disconnected(this);
    }

    public String getCurrentState() {
        return state.getCurrentState();
    }

    public void connect() {
        state.connect();
    }

    public void disconnect() {
        state.disconnect();
    }

    public void write(String data) {
        state.write(data);
    }

    public void setState(Connection state) {
        this.state = state;
    }
}
// END
