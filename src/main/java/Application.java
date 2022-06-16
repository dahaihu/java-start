import annotation.MyAnno;
import model.ObjectToJsonConverter;
import model.Person;


public class Application {
    @MyAnno(studentName = "Chaitanya",
            stuAddress = "Agra, India")
    public static void test() {
    }
    public static void main(String[] args) throws ObjectToJsonConverter.JsonSerializationException {
        Person person = new Person("soufiane", "cheouati", "34");
        ObjectToJsonConverter serializer = new ObjectToJsonConverter();
        String jsonString = serializer.convertToJson(person);
        System.out.println(jsonString);
    }
}
