package lesson1.task3;

public class Main {

    public static void main(String[] args) {
        MyClass myClass1 = new MyClass();
        System.out.println(myClass1);
        MyClass myClass2;

        Serializable serializable = new Serializable(myClass1);
        serializable.writeToFile("lesson1/task3/file.txt");

        Deseriazable deseriazable = new Deseriazable("lesson1/task3/file1.txt");
        myClass2 = (MyClass)deseriazable.get(new MyClass());

        System.out.println(myClass2);
    }
}
