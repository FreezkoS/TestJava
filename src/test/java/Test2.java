import org.testng.annotations.Test;

public class Test2 {
    @Test //(priority = 2, groups = "simple", dependsOnGroups = {"regress"})
    public void test2(){
        System.out.println("Test1");
    }
}
