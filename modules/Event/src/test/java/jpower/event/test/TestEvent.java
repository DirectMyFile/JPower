package jpower.event.test;

import java.io.Serializable;

class TestEvent implements Serializable {
    public String getPayload() {
        return "Success";
    }
}
