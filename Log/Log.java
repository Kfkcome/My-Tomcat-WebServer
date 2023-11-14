package Log;

public class Log {
    public static void warn(String meg){
        Long l = System.currentTimeMillis();
        System.out.println(l +"warn:"+meg);
    }
}
