public class P_leize extends Character {


    public P_leize() {
        // 构造方法:为新增属性赋初值
        super("雷泽","钢脊","利爪与苍雷","雷牙", 1500, 135, 60, 0,Element.L);
        setNormalAttackBonus(0.512);
        setSkillAttackBonus(1.32);
        setUltimateAttackBonus(1.97);
        setElementalDmgBonus(0);
    }
    int q = 0 ;
    @Override
    public int normalAttack() {
        if (q>0){
            q-=1;
            return (int) (super.normalAttack()*(getNormalAttackBonus()+1.6));
        }
        return (int) (super.normalAttack()*(getNormalAttackBonus()+1));
    }


    @Override
    public int skillAttack() {
        if(q>0){
            q-=1;
        return (int) (super.normalAttack()*(this.getSkillAttackBonus()+1.6));
         }
        return (int) (super.normalAttack()*(getSkillAttackBonus()+1));
    }

    @Override
    public int ultimateAttack() {
        q +=3;
        return (int) (super.normalAttack()*(this.getUltimateAttackBonus()+1));
    }
}