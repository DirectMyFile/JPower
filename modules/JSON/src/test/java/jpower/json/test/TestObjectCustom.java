package jpower.json.test;

import jpower.json.serialization.JSONKey;

public class TestObjectCustom {
   @JSONKey("message")
   private String actualMessage = "Hello World";
}
