package java3_Homework7;

public class TestedClass {

    @BeforeSuite
    public static void beforeSuiteMethod(){
        System.out.println("Это beforeSuite метод!");
    }

    @AfterSuite
    public static void afterSuiteMethod(){
        System.out.println("Это afterSuite метод!");
    }

    @Test(priority = 10)
    public static void TestMethodPriority10(){
        System.out.println("Это тестовый метод с приоритетом 10");
    }

    @Test
    public static void TestMethodWithDefaultPriority(){
        System.out.println("Это тестовый метод с дефолтным приоритетом");
    }

    @Test(priority = 2)
    public static void TestMethodPriority2(){
        System.out.println("Это тестовый метод с приоритетом 2");
    }
}
