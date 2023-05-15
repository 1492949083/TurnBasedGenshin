import java.util.Scanner;

public class Start {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("欢迎来到回合制原神!");
        System.out.println("1.单人模式  2.双人模式");
        System.out.print("选择你要玩的模式:");
        try {
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("你选择了单人模式");
                    while (true) {
                        Game game = new Game();
                        game.GameStart();
                        System.out.print("是否继续游戏?(y/n)");
                        String sr = sc.next();
                        if (sr.equals("n")) {
                            break;
                        }
                    }
                    break;
                case 2:
                    System.out.println("你选择了双人模式");
                    while (true) {
                        Game game = new Game();
                        game.PVPGameStart();
                        System.out.print("是否继续游戏?(y/n)");
                        String sr = sc.next();
                        if (sr.equals("n")) {
                            break;
                        }
                    }
                    break;
                default:
                    System.out.println("\33[33m"+"输入错误,请重新输入!"+"\33[0m");
                    main(args);   // 递归调用main方法
            }
        } catch (Exception e) {
            System.out.println("\33[31m"+"输入错误,惩罚你重新开始!!!"+"\33[0m");
            main(args);   // 递归调用main方法
        }


    }
}
