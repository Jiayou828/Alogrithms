package algorithms.basicEntry.chap01;

import java.util.*;

public class hw_GradeSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int numStu = sc.nextInt();
            int upDown = sc.nextInt();
            List<Stu> stuList = new ArrayList<>();
            for (int i = 0; i < numStu; i++) {
                stuList.add(new Stu(sc.next(),sc.nextInt()));
            }
            //降序
            if (upDown == 0) {
                Collections.sort(stuList, new Comparator<Stu>() {
                    @Override
                    public int compare(Stu o1, Stu o2) {
                        return o2.getScore() - o1.getScore();
                    }
                });
            } else if (upDown == 1) {  //升序
                Collections.sort(stuList, new Comparator<Stu>() {
                    @Override
                    public int compare(Stu o1, Stu o2) {
                        return o1.getScore() - o2.getScore();
                    }
                });
            }
            for (int i = 0; i < stuList.size(); i++) {
                System.out.println(stuList.get(i));
            }

        }
    }


}


class Stu {
    private String name;
    private int score;

    Stu(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }

    @Override
    public String toString() {
        return name + " " + score;
    }
}