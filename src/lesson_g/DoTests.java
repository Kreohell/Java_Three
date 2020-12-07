package lesson_g;
//ad hw
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


public class DoTests {

    private static final int MIN_PRIORITY = 1;
    private static final int MAX_PRIORITY = 10;

    public static void start(Class cls) {
        doingTests(cls);
    }

    private static void doingTests(Class<?> className) throws RuntimeException {
        Map<Integer, Method> map = new TreeMap<>();
        int buffBeforeSuite = 0;
        int buffAfterSuite = 0;
        for (Method method : className.getDeclaredMethods()) {
            if (method.getAnnotation(BeforeSuite.class) != null) {
                map.put(MIN_PRIORITY - 1, method);
                buffBeforeSuite++;
            }
            if (method.getAnnotation(AfterSuite.class) != null) {
                map.put(MAX_PRIORITY + 1, method);
                buffAfterSuite++;
            }
            if (method.getAnnotation(Test.class) != null) {
                Test test = method.getAnnotation(Test.class);
                map.put(test.priority(), method);
            }
            if(buffAfterSuite > 1 | buffBeforeSuite > 1){
                throw new RuntimeException("Illegal count of annotations.");
            }
        }
        try {
            Object testCase = className.newInstance();
            for (Integer key : map.keySet()) {
                map.get(key).invoke(testCase);
            }
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }

    }

    @Test(priority = 3)
    public void Test5() {
        System.out.println("Test №5");
    }
    @Test(priority = 2)
    public void Test2(){
        System.out.println("Test №2");
    }
    @Test(priority = 1)
    public void Test1(){
        System.out.println("Test №1");
    }
    @BeforeSuite
    public void BeforeAll(){
        System.out.println("First method.");
    }
    @AfterSuite
    public void AfterAll() {
        System.out.println("Last method.");
    }

 }
