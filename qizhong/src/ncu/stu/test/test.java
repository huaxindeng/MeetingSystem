package ncu.stu.test;

import org.junit.Test;

public class test {
    private static int i;
    public static void main(String[] args) {

    }
    @Test
    public void test(){
        int[] nums = {1,2,3,4,5,6,7};
        String str = "";
        for(int i=0;i<7;i++){
            str+=nums[i];
        }
        System.out.println(str);
        System.out.println(i);
    }
}
