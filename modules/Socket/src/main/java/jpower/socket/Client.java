package jpower.socket;

import jpower.core.utils.IOUtils;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private ObjectOutputStream objectOut;
    private ObjectInputStream objectIn;

    Client(Socket socket) throws IOException {
        this.socket = socket;
        this.reader = IOUtils.createBufferedReader(socket.getInputStream());
        this.writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.objectOut = new ObjectOutputStream(socket.getOutputStream());
        this.objectIn = new ObjectInputStream(socket.getInputStream());
    }

    public void write(String text) {
        writer.write(text);
        writer.flush();
    }

    public void writeObject(Object object) throws IOException {
        objectOut.writeObject(object);
        objectOut.flush();
    }

    public void writeLine(String line) {
        write(line + '\n');
    }

    public Object readObject() throws IOException, ClassNotFoundException {
        return objectIn.readObject();
    }

    public String readLine() throws IOException {
        return reader.readLine();
    }

    public Socket getSocket() {
        return socket;
    }

    public void end() throws IOException {
        socket.close();
    }
}
