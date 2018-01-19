package lesson1.task3;

public class MyClass {

    private String field1 = "Field1";
    @Save
    private boolean field2 = true;
    @Save
    private String field3 = "Field2";
    private int field4 = 25;
    @Save
    private int field5 = 21;
    private boolean field6 = true;

    @Override
    public String toString() {
        return "MyClass{" +
                "field1='" + field1 + '\'' +
                ", field2=" + field2 +
                ", field3='" + field3 + '\'' +
                ", field4=" + field4 +
                ", field5=" + field5 +
                ", field6=" + field6 +
                '}';
    }
}
