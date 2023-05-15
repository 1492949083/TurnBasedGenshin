public class P_kong extends Character {


    public P_kong() {
        // 构造方法:为新增属性赋初值
        super("空","异邦铁风","风涡剑","风息激荡", 1400, 141, 57, 0,Element.F);
        setNormalAttackBonus(0.445);
        setSkillAttackBonus(2.3);
        setUltimateAttackBonus(3.08);
    }



    @Override
    public int normalAttack() {
        return (int) (super.normalAttack()*(this.getNormalAttackBonus()+1));
    }


    @Override
    public int skillAttack() {
        return (int) (super.skillAttack()*(this.getSkillAttackBonus()+1));
    }


    @Override
    public int ultimateAttack() {
        return (int) (super.ultimateAttack()*(this.getUltimateAttackBonus()+1));
    }
}