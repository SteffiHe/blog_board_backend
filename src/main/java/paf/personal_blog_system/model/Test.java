package paf.personal_blog_system.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Test {
    private String test;

    public static void main(String[] args) {
        Test obj = new Test();
        obj.setTest("Hallo");
        System.out.println(obj.getTest());
    }
}