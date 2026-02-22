public class Printer {
    static void print(String id,int count,StudentRecord rec){
        System.out.println("OK: created student " + id);
        System.out.println("Saved. Total students: " + count);
        System.out.println("CONFIRMATION:");
        System.out.println(rec);
    }
}
