public class P_keqing extends Character {


    public P_keqing() {
        // 构造方法:为新增属性赋初值
        super("刻晴", "云来剑法", "星斗归位", "天街巡游", 1450, 136, 46, 0, Element.L);
        setNormalAttackBonus(0.41);
        setSkillAttackBonus(2.26);
        setUltimateAttackBonus(2.67);
    }

    int q = 0;

    @Override
    public int normalAttack() {
        return (int) (super.normalAttack() * (getNormalAttackBonus() + 1));
    }


    @Override
    public int skillAttack() {
        return (int) (super.normalAttack() * getSkillAttackBonus() + 1);
    }

    @Override
    public int ultimateAttack() {
        q = 3;
        return (int) (super.normalAttack() * (this.getUltimateAttackBonus() + 1));
    }

    @Override
    public int passiveSkill() {
        if (q > 0) {
            q -= 1;
            setCritRate(0.12);
        } else {
            setCritRate(0.05);
        }
        return 0;
    }
}