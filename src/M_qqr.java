public class M_qqr extends Character{
    public M_qqr(){
        super("丘丘人",  "挥拳", "砸石块", "跳舞",1000, 120, 5, 0, Element.W);
    }
    int maxHp = this.getMaxHp();

    @Override
    public int skillAttack() {
        // 重写技能攻击方法
        this.setHp(this.getHp() - maxHp/8);
        System.out.println("丘丘人因为技能攻击而受伤! 减去了" + "\033[31m" + maxHp/8 + "\033[0m" + "点生命值");
        return super.skillAttack()*2 + maxHp/8;
    }

    @Override
    public int ultimateAttack() {
        // 重写大招攻击方法
        this.setHp(this.getHp() - maxHp/4);
        System.out.println("丘丘人因为大招攻击而受伤! 减去了" + "\033[31m" + maxHp/4 + "\033[0m" + "点生命值");
        return super.ultimateAttack()*3 + maxHp/4;
    }

    @Override
    public int passiveSkill(){
        if (this.getHp() < maxHp/2){
            setAtk((int) (getAtk()*1.2));
            System.out.println("丘丘人发狂了!");
        }else {
            System.out.println("丘丘人正常发挥!");
        }
        return 0;
    }
}
