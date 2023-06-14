public class P_yanfei extends Character {


    public P_yanfei() {
        // 构造方法:为新增属性赋初值
        super("烟绯","火漆制印","丹书立约","凭此结契", 1340, 150, 40, 0,Element.H);
        setNormalAttackBonus(0.5);
        setSkillAttackBonus(1.5);
        setUltimateAttackBonus(2);
    }
    int d=0;


    @Override
    public int normalAttack() {
        if (d>0){
            d-=1;
            return (int) (super.normalAttack()*(getNormalAttackBonus()+2));
        }
        return (int) (super.normalAttack()*(getNormalAttackBonus()+1));
    }

    @Override
    public int skillAttack() {
        return (int) (super.skillAttack()*(this.getSkillAttackBonus()+1));
    }

    @Override
    public int ultimateAttack() {
        d +=2;
        return (int) (super.normalAttack()*(this.getUltimateAttackBonus()+1));
    }
}