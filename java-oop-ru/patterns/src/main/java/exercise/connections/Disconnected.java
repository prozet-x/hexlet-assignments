package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Disconnected implements Connection {
    private TcpConnection connection;

    public Disconnected(TcpConnection connection) {
        this.connection = connection;
    }

    @Override
    public String getCurrentState() {
        return "disconnected";
    }

    @Override
    public void connect() {
        connection.setState(new Connected(connection));
    }

    @Override
    public void disconnect() {
        System.out.println("Error. Already disconnected");
    }

    @Override
    public void write(String data) {
        System.out.println("Error. Disconnected");
    }
}
// END
