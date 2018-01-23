package lesson2.task2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        StringBuilder result = new StringBuilder();
        String str;

        try (BufferedReader reader = new BufferedReader(new FileReader("lesson2/task2/Contacts.gson"))){

            while ((str = reader.readLine()) != null) {
                result.append(str);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Gson gson = new GsonBuilder().create();
        Person person = (Person)gson.fromJson(result.toString(), Person.class);


        System.out.println(person.toString());

    }

}
