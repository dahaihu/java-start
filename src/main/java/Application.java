import cache.lru.LRU;
import service.GuavaCacheService;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


class Pair<T> {

    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}

class DateInter extends Pair<Date> {

    @Override
    public void setValue(Date value) {
        super.setValue(value);
    }

    @Override
    public Date getValue() {
        return super.getValue();
    }
}

public class Application {
    public static class Person {
        public String name;

        public Person(String name) {
            this.name = name;
        }

        public void Say() {
            System.out.println("hello person " + name);
        }
    }

    public static class Teacher extends Person {
        public Teacher(String name) {
            super(name);
        }

        @Override
        public void Say() {
            System.out.println("hello teacher " + name);
        }
    }

    public static class Student extends Teacher {
        public Student(String name) {
            super(name);
        }

        @Override
        public void Say() {
            System.out.println("hello student " + name);
        }
    }

    public static void main(String[] args) {
        List<? extends Teacher> list;
        List<Student> listStudent = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            listStudent.add(new Student("student " + i));
        }
        list = listStudent;
        for (Teacher s : list) {
            s.Say();
        }
    }
}
