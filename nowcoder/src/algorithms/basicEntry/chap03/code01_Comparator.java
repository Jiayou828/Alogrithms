package algorithms.basicEntry.chap03;

import java.util.Arrays;
import java.util.Comparator;

public class code01_Comparator {

    //重载比较运算符  <   >   =   <=   >=
    //对于任意比较器，首先需要指定两个对象 o1   o2
    //返回负数时：认为o1应该排在o2的前面
    //返回正数时：认为o2应该排在o1的前面
    //返回0时：谁排在前面都可以

    public static class Mycomp implements Comparator<Student> {
        // 先按id排序，如果id相等，再按age排序
        @Override
        public int compare(Student o1, Student o2) {
            if (o1.id == o2.id)
                return o1.age - o2.age;
            return o1.id - o2.id;
        }
    }

    public static class Student {
        String name;
        int id;
        int age;

        public Student(String name, int id, int age) {
            this.name = name;
            this.id = id;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", id=" + id +
                    ", age=" + age +
                    '}';
        }
    }

    public static void main(String[] args) {
        Student lili = new Student("lili", 1, 12);
        Student lucy = new Student("lucy", 1, 11);
        Student kele = new Student("kele", 2, 33);
        Student lulu = new Student("lulu", 2, 10);

        Student[] arrs = new Student[] {lili, lucy, kele, lulu};

        System.out.println(Arrays.toString(arrs));
        Arrays.sort(arrs, new Mycomp());
        System.out.println(Arrays.toString(arrs));
    }
}
