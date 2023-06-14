public class M_leiyingshushi extends Character {


    public M_leiyingshushi() {
        // 构造方法:为新增属性赋初值
        super("雷萤术士","雷萤攻击","天雷","雷电攻击", 1120, 135, 50, 0,Element.L);
        setNormalAttackBonus(0.35);
        setSkillAttackBonus(2.8);
        setUltimateAttackBonus(3.66);
    }



    @Override
    public int normalAttack() {
        return (int) (super.normalAttack()*(this.getNormalAttackBonus()+1));
    }


    @Override
    public int skillAttack() {
        return (int) (super.normalAttack()*(this.getSkillAttackBonus()+1));
    }


    @Override
    public int ultimateAttack() {
        return (int) (super.normalAttack()*(this.getUltimateAttackBonus()+1));
    }
}