import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
public class Game {
    // 创建两个玩家角色对象
    Character[] players = {new Bot(),new P_Diluc(), new M_qqr(), new P_kong(), new P_babatuosi(), new P_hutao(), new P_ying(),new M_leiyingshushi(),new P_leize(),new P_keqing(),new P_yanfei()};
    Character[] enemies = {new Bot(), new P_Diluc(), new M_qqr(), new P_kong(), new P_babatuosi(), new P_hutao(), new P_ying(),new M_leiyingshushi(),new P_leize(),new P_keqing(),new P_yanfei()};
    private String PlayerName;
    private String EnemyName;

    private int nldMax = 4;
    private int nldn = 1;

    private int nlds = 1;

    private int nldu = 2;


    public Game() {
    }

    public Game(String PlayerName, String EnemyName) {
        this.PlayerName = PlayerName;
        this.EnemyName = EnemyName;
    }




    //文本游戏界面
    public void GameStart() {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < players.length; i++) {
            System.out.println(i + ". " + players[i].getName());
        }
        System.out.print("请选择你的角色:");
        int playerIndex = sc.nextInt();


        for (int i = 0; i < enemies.length; i++) {
            System.out.println(i + ". " + enemies[i].getName());
        }


        System.out.print("请选择对手:");
        int enemyIndex = sc.nextInt();

        PlayerName = players[playerIndex].getName();
        EnemyName = enemies[enemyIndex].getName();

        Game game = new Game(PlayerName, EnemyName);

        System.out.println("你选择了" + game.PlayerName + "作为你的角色");
        System.out.println("你的对手是" + game.EnemyName);


        /*
         \033[0m 关闭所有属性
         \033[30m 黑色字
         \033[31m 红色字
         \033[32m 绿色字
         \033[33m 黄色字
         \033[34m 蓝色字
         \033[35m 紫色字
         \033[36m 天蓝字
         \033[37m 白色字
         \033[0m 清除颜色
         \033[1m 加粗字体
         \033[4m 下划线
         \033[5m 闪烁
         \033[7m 反显
         \033[8m 消隐
         游戏循环
        */


        while (true) {

            System.out.println("--------------------------------------------------");
            // 显示玩家与敌人当前生命值
            System.out.println("\033[32m" + game.PlayerName + "\033[0m"+"血量:" + players[playerIndex].getHp() + "        " + "\033[33m" + game.EnemyName + "\033[0m" + "血量:" + enemies[enemyIndex].getHp());
            System.out.println();
            System.out.println();
            // 选择技能 以白色 绿色 黄色排序
            System.out.print("\033[32m" + game.PlayerName + "\033[0m" + "你有" + players[playerIndex].getSkillBeans() + "个能量豆," + "请选择技能:");
            System.out.print("\033[0m" + "1."+ players[playerIndex].getNormalAttackName() + "\033[0m" + "  " + "\033[1;32m" + "2."+ players[playerIndex].getSkillAttackName() + "\033[0m" + "  " + "\033[1;33m" + "3."+ players[playerIndex].getUltimateAttackName() + "\033[0m" + "  ");


            // 读取玩家输入
            int choice = sc.nextInt();

            // 根据选择调用技能方法计算伤害
            int pgj = 0;
            String P_attackName;
            //玩家使用被动
            pgj = pgj + players[playerIndex].passiveSkill();
            switch (choice) {
                case 1 -> {
                    if (players[playerIndex].getSkillBeans() < nldMax) {
                        players[playerIndex].setSkillBeans(players[playerIndex].getSkillBeans()+nldn);
                    }
                    if (enemies[enemyIndex].getDef() > players[playerIndex].normalAttack()) {
                        pgj = 0;
                    } else {
                        pgj += players[playerIndex].normalAttack() - enemies[enemyIndex].getDef();
                    }
                    P_attackName = players[playerIndex].getNormalAttackName();
                }
                case 2 -> {
                    if (players[playerIndex].getSkillBeans() >= nlds){
                        players[playerIndex].setSkillBeans(players[playerIndex].getSkillBeans()-nlds);
                        if (enemies[enemyIndex].getDef() > players[playerIndex].skillAttack()) {
                            pgj = 0;
                        } else {
                            pgj += players[playerIndex].skillAttack() - enemies[enemyIndex].getDef();
                        }
                        P_attackName = players[playerIndex].getSkillAttackName();
                    }else {
                        System.out.println("你的能量豆不够！");
                        continue;
                    }

                }
                case 3 -> {
                    if (players[playerIndex].getSkillBeans() >= nldu) {
                        players[playerIndex].setSkillBeans(players[playerIndex].getSkillBeans()-nldu);
                        if (enemies[enemyIndex].getDef() > players[playerIndex].ultimateAttack()) {
                            pgj = 0;
                        } else {
                            pgj += players[playerIndex].ultimateAttack() - enemies[enemyIndex].getDef();
                        }
                        P_attackName = players[playerIndex].getUltimateAttackName();
                    } else {
                        System.out.println("你的能量豆不够！");
                        continue;
                    }
                }
                default -> {
                    System.out.println("输入错误,请重新输入");
                    continue;
                }
            }


            if (Math.random() < players[playerIndex].getCritRate()) {
                pgj = (int) (pgj * (players[playerIndex].getCritDmg()+1));
                System.out.println("暴击！！！");
            }
            // 减去敌人生命值
            int dHp = enemies[enemyIndex].getHp() - pgj; // d - p
            enemies[enemyIndex].setHp(dHp);
            if (pgj == 0) {
                System.out.println("\033[32m" + game.PlayerName + "\033[0m" + "使用了" + "\033[1;31m" + P_attackName + "\033[0m" + "没有造成伤害,或许是加被动了");
            } else{
                System.out.println("\033[32m" + game.PlayerName + "\033[0m" + "使用了" + "\033[1;31m" + P_attackName + "\033[0m" + "对" + "\033[33m" + game.EnemyName + "\033[0m" + "造成了" + "\033[1;31m" + pgj + "\033[0m" + "点伤害"+ "     " + "\033[33m" + game.EnemyName + "\033[0m" + "还剩" + "\033[1;31m" + dHp + "\033[0m" + "点血量");
            }

            // 判断敌人是否死亡
            if (enemies[enemyIndex].getHp() <= 0) {
                System.out.println("\033[33m" + game.EnemyName + "\033[0m" + "死亡");
                System.out.println("\033[32m" + game.PlayerName + "\033[0m" + "获得胜利");
                break;
            }


            // 敌人使用被动
            enemies[enemyIndex].passiveSkill();
            //随机选择敌方技能
            int s = 0;
            if (enemies[enemyIndex].getSkillBeans() >= 2) {
                s = new Random().nextInt(3);
            }else if (enemies[enemyIndex].getSkillBeans() < 1) {
                s = 0;
            } else {
                s = new Random().nextInt(2);
            }
            int dgj = 0;
            String D_attackName = null;
            switch (s) {
                case 0 -> {
                    if (enemies[enemyIndex].getSkillBeans() < nldMax) {
                        enemies[enemyIndex].setSkillBeans(enemies[enemyIndex].getSkillBeans()+nldn);
                    }
                    if (players[playerIndex].getDef() > enemies[enemyIndex].normalAttack()) {
                        dgj = 0;
                    } else{
                        dgj += enemies[enemyIndex].normalAttack() - players[playerIndex].getDef();//意思是敌人的攻击力减去玩家的防御力
                    }
                    D_attackName = enemies[enemyIndex].getNormalAttackName();
                }
                case 1 -> {
                    enemies[enemyIndex].setSkillBeans(enemies[enemyIndex].getSkillBeans()-nlds);
                    if (players[playerIndex].getDef() > enemies[enemyIndex].skillAttack()) {
                        dgj = 0;
                    } else{
                        dgj += enemies[enemyIndex].skillAttack() - players[playerIndex].getDef();
                    }
                    D_attackName = enemies[enemyIndex].getSkillAttackName();
                }
                case 2 -> {
                    enemies[enemyIndex].setSkillBeans(enemies[enemyIndex].getSkillBeans()-nldu);
                    if (players[playerIndex].getDef() > enemies[enemyIndex].ultimateAttack()) {
                        dgj = 0;
                    } else{
                        dgj += enemies[enemyIndex].ultimateAttack() - players[playerIndex].getDef();
                    }
                    D_attackName = enemies[enemyIndex].getUltimateAttackName();
                }
            }
            // 减去玩家生命值
            if (Math.random() < (enemies[enemyIndex].getCritRate()+1)) {
                dgj = (int) (dgj * enemies[enemyIndex].getCritDmg());
                System.out.println("暴击！！！");
            }
            int pHp = players[playerIndex].getHp() - dgj; // p - d
            players[playerIndex].setHp(pHp);
            if (dgj == 0) {
                System.out.println("\033[33m" + game.EnemyName + "\033[0m" + "使用了" + "\033[1;31m" + D_attackName + "\033[0m" + "没有造成伤害,或许是加被动了");
            } else {
                System.out.println("\033[33m" + game.EnemyName + "\033[0m" + "使用了" + "\033[1;31m" + D_attackName + "\033[0m" + "对" + "\033[32m" + game.PlayerName + "\033[0m" + "造成了" + "\033[1;31m" + dgj + "\033[0m" + "点伤害" + "     " + "\033[32m" + game.PlayerName + "\033[0m" + "还剩" + "\033[1;31m" + pHp + "\033[0m" + "点血量");
            }

            // 判断玩家是否死亡
            if (players[playerIndex].getHp() <= 0) {
                System.out.println("\033[32m" + game.PlayerName + "\033[0m" + "死亡");
                System.out.println("\033[33m" + game.EnemyName + "\033[0m" + "获得胜利");
                break;
            }
        }
    }

    //pvp模式
    public void PVPGameStart() {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < players.length; i++) {
            System.out.println(i + ". " + players[i].getName());
        }
        System.out.print("请选择你的角色:");
        int playerIndex = sc.nextInt();


        for (int i = 0; i < enemies.length; i++) {
            System.out.println(i + ". " + enemies[i].getName());
        }


        System.out.print("请选择对手:");
        int enemyIndex = sc.nextInt();

        PlayerName = players[playerIndex].getName();
        EnemyName = enemies[enemyIndex].getName();

        Game game = new Game(PlayerName, EnemyName);

        System.out.println("你选择了" + game.PlayerName + "作为你的角色");
        System.out.println("你的对手是" + game.EnemyName);




        // 游戏循环
        while (true) {

            System.out.println("--------------------------------------------------");
            // 显示p1与p2当前生命值
            System.out.println("\033[32m" + game.PlayerName + "\033[0m" + "血量:" + players[playerIndex].getHp() + "        " + "\033[33m" + game.EnemyName + "\033[0m" + "血量:" + enemies[enemyIndex].getHp());
            System.out.println();
            System.out.println();
            // 选择技能 以白色 绿色 黄色排序
            System.out.print("\033[32m" + game.PlayerName + "\033[0m" + "你有" + players[playerIndex].getSkillBeans() + "个能量豆," + "请选择技能:");
            System.out.print("\033[0m" + "1."+ players[playerIndex].getNormalAttackName() + "\033[0m" + "  " + "\033[1;32m" + "2."+ players[playerIndex].getSkillAttackName() + "\033[0m" + "  " + "\033[1;33m" + "3."+ players[playerIndex].getUltimateAttackName() + "\033[0m" + "  ");
            // 读取p1输入
            int p1 = sc.nextInt();

            // 根据选择调用技能方法计算伤害
            int pgj = 0;
            String P_attackName;
            //p1使用被动
            pgj = pgj + players[playerIndex].passiveSkill();
            switch (p1) {
                case 1 -> {
                    if (players[playerIndex].getSkillBeans() < nldMax) {
                        players[playerIndex].setSkillBeans(players[playerIndex].getSkillBeans()+nldn);
                    }
                    if (enemies[enemyIndex].getDef() > players[playerIndex].normalAttack()) {
                        pgj = 0;
                    } else {
                        pgj += players[playerIndex].normalAttack() - enemies[enemyIndex].getDef();
                    }
                    P_attackName = players[playerIndex].getNormalAttackName();
                }
                case 2 -> {
                    if (players[playerIndex].getSkillBeans() >= nlds){
                        players[playerIndex].setSkillBeans(players[playerIndex].getSkillBeans()-nlds);
                        if (enemies[enemyIndex].getDef() > players[playerIndex].skillAttack()) {
                            pgj = 0;
                        } else {
                            pgj += players[playerIndex].skillAttack() - enemies[enemyIndex].getDef();
                        }
                        P_attackName = players[playerIndex].getSkillAttackName();
                    }else {
                        System.out.println("你的能量豆不够！");
                        continue;
                    }

                }
                case 3 -> {
                    if (players[playerIndex].getSkillBeans() >= nldu) {
                        players[playerIndex].setSkillBeans(players[playerIndex].getSkillBeans()-nldu);
                        if (enemies[enemyIndex].getDef() > players[playerIndex].ultimateAttack()) {
                            pgj = 0;
                        } else {
                            pgj += players[playerIndex].ultimateAttack() - enemies[enemyIndex].getDef();
                        }
                        P_attackName = players[playerIndex].getUltimateAttackName();
                    } else {
                        System.out.println("你的能量豆不够！");
                        continue;
                    }
                }
                default -> {
                    System.out.println("输入错误,请重新输入");
                    continue;
                }
            }


            // 减去敌人生命值
            if (Math.random() < players[playerIndex].getCritRate()) {
                pgj = (int) (pgj * (players[playerIndex].getCritDmg()+1));
                System.out.println("暴击！！！");
            }
            int dHp = enemies[enemyIndex].getHp() - pgj; // d - p
            enemies[enemyIndex].setHp(dHp);
            if (pgj == 0) {
                System.out.println("\033[32m" + game.PlayerName + "\033[0m" + "使用了" + "\033[1;31m" + P_attackName + "\033[0m" + "没有造成伤害,或许是加被动了");
            } else {
                System.out.println("\033[32m" + game.PlayerName + "\033[0m" + "使用了" + "\033[1;31m" + P_attackName + "\033[0m" + "对" + "\033[33m" + game.EnemyName + "\033[0m" + "造成了" + "\033[1;31m" + pgj + "\033[0m" + "点伤害" + "     " + "\033[33m" + game.EnemyName + "\033[0m" + "还剩" + "\033[1;31m" + dHp + "\033[0m" + "点血量");
            }

            // 判断敌人是否死亡
            if (enemies[enemyIndex].getHp() <= 0) {
                System.out.println("\033[33m" + game.EnemyName + "\033[0m" + "死亡");
                System.out.println("\033[32m" + game.PlayerName + "\033[0m" + "获得胜利");
                break;
            }

            System.out.println("--------------------------------------------------");
            // 显示p1与p2当前生命值
            System.out.println("\033[32m" + game.PlayerName + "\033[0m" + "血量:" + players[playerIndex].getHp() + "        " + "\033[33m" + game.EnemyName + "\033[0m" + "血量:" + enemies[enemyIndex].getHp());
            System.out.println();
            System.out.println();
            // 选择技能 以白色 绿色 黄色排序
            System.out.print("\033[33m" + game.EnemyName + "\033[0m" + "你有" + enemies[enemyIndex].getSkillBeans() + "个能量豆," + "请选择技能:");
            System.out.print("\033[0m" + "1."+ enemies[enemyIndex].getNormalAttackName() + "\033[0m" + "  " + "\033[1;32m" + "2."+ enemies[enemyIndex].getSkillAttackName() + "\033[0m" + "  " + "\033[1;33m" + "3."+ enemies[enemyIndex].getUltimateAttackName() + "\033[0m" + "  ");



            // p2使用被动
            enemies[enemyIndex].passiveSkill();
            //读取p2输入
            int p2 = sc.nextInt();
            int dgj;
            String D_attackName;
            switch (p2) {
                case 1 -> {
                    if (enemies[enemyIndex].getSkillBeans() < nldMax) {
                        enemies[enemyIndex].setSkillBeans(enemies[enemyIndex].getSkillBeans()+nldn);
                    }
                    if (players[playerIndex].getDef() > enemies[enemyIndex].normalAttack()) {
                        dgj = 0;
                    } else {
                        dgj = enemies[enemyIndex].normalAttack() - players[playerIndex].getDef();
                    }
                    D_attackName = enemies[enemyIndex].getNormalAttackName();
                }
                case 2 -> {
                    if (enemies[enemyIndex].getSkillBeans() >= nlds) {
                        enemies[enemyIndex].setSkillBeans(enemies[enemyIndex].getSkillBeans()-nlds);
                        if (players[playerIndex].getDef() > enemies[enemyIndex].skillAttack()) {
                            dgj = 0;
                        } else {
                            dgj = enemies[enemyIndex].skillAttack() - players[playerIndex].getDef();
                        }
                        D_attackName = enemies[enemyIndex].getSkillAttackName();
                    } else {
                        System.out.println("你的能量豆不够！");
                        continue;
                    }
                }
                case 3 -> {
                    if (enemies[enemyIndex].getSkillBeans() >= nldu) {
                        enemies[enemyIndex].setSkillBeans(enemies[enemyIndex].getSkillBeans()-nldu);
                        if (players[playerIndex].getDef() > enemies[enemyIndex].ultimateAttack()) {
                            dgj = 0;
                        } else {
                            dgj = enemies[enemyIndex].ultimateAttack() - players[playerIndex].getDef();
                        }
                        D_attackName = enemies[enemyIndex].getUltimateAttackName();
                    } else {
                        System.out.println("你的能量豆不够！");
                        continue;
                    }
                }
                default -> {
                    System.out.println("输入错误,请重新输入");
                    continue;
                }
            }
            // 减去玩家生命值
            if (Math.random() < (enemies[enemyIndex].getCritRate()+1)) {
                dgj = (int) (dgj * enemies[enemyIndex].getCritDmg());
                System.out.println("暴击！！！");
            }
            int pHp = players[playerIndex].getHp() - dgj; // p - d
            players[playerIndex].setHp(pHp);
            if (dgj == 0) {
                System.out.println("\033[33m" + game.EnemyName + "\033[0m" + "使用了" + "\033[1;31m" + D_attackName + "\033[0m" + "没有造成伤害,或许是加被动了");
            } else {
                System.out.println("\033[33m" + game.EnemyName + "\033[0m" + "使用了" + "\033[1;31m" + D_attackName + "\033[0m" + "对" + "\033[32m" + game.PlayerName + "\033[0m" + "造成了" + "\033[1;31m" + dgj + "\033[0m" + "点伤害" + "     " + "\033[32m" + game.PlayerName + "\033[0m" + "还剩" + "\033[1;31m" + pHp + "\033[0m" + "点血量");
            }

            // 判断玩家是否死亡
            if (players[playerIndex].getHp() <= 0) {
                System.out.println("\033[32m" + game.PlayerName + "\033[0m" + "死亡");
                System.out.println("\033[33m" + game.EnemyName + "\033[0m" + "获得胜利");
                break;
            }
        }
    }

    //局域网对战
    public void LAN() {

        ServerSocket serverSocket;
        Socket player2;
        PrintWriter outputPlayer2;
        //建立房间或者加入房间
        System.out.println("请选择:");
        System.out.println("1.建立房间");
        System.out.println("2.加入房间");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch (choice) {
            case 1 -> {
                // System.out.println("请输入端口号(1024-65535):");
                // int port = sc.nextInt();
                try {
                    serverSocket = new ServerSocket(6666);
                    player2 = serverSocket.accept();
                    System.out.println("玩家2已加入游戏");
                    outputPlayer2 = new PrintWriter(player2.getOutputStream(), true);
                    outputPlayer2.println("欢迎加入游戏，你是玩家2");

                } catch (IOException e) {
                    System.out.println("端口号已被占用");
                    e.printStackTrace();
                }
            }
            case 2 -> {
                // System.out.println("请输入ip:");
                // String ip = sc.next();
                // System.out.println("请输入端口号:");
                // int port = sc.nextInt();
                String ip = "127.0.0.1";
                int port = 6666;
                try {
                    Socket socket = new Socket(ip, port);
                    System.out.println("连接成功");

                } catch (IOException e) {
                    System.out.println("连接失败");
                    e.printStackTrace();
                }
            }
        }
    }
}