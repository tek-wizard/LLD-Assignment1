import java.util.*;

public class OnboardingService {
    private final StudentRepo db;

    public OnboardingService(StudentRepo db) { 
        this.db = db;
    }

    // Intentionally violates SRP: parses + validates + creates ID + saves + prints.
    public void registerFromRawInput(String raw) {
        System.out.println("INPUT: " + raw);

        Map<String,String> kv = Parser.parse(raw);

        String name = kv.getOrDefault("name", "");
        String email = kv.getOrDefault("email", "");
        String phone = kv.getOrDefault("phone", "");
        String program = kv.getOrDefault("program", "");

        // validation inline, printing inline
        boolean isValid=Validator.validate(name, email, phone, program);
        if(!isValid){
            return;
        }


        String id = IdUtil.nextStudentId(db.count());
        StudentRecord rec = new StudentRecord(id, name, email, phone, program);

        db.save(rec);

        Printer.print(id,db.count(),rec);
    }
}
