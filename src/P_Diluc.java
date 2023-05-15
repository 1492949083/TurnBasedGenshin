public class P_Diluc extends Character {
    /**
     * 构造方法:为新增属性赋初值
     * 初始化迪卢克的各项属性,调用父类Character的构造方法
     * 依次为:
     * name 角色名称
     * normalAttackName 普通攻击名称
     * skillAttackName 技能攻击名称
     * ultimateAttackName 大招攻击名称
     * hp 生命值
     * atk 物理攻击力
     * def 物理防御力
     * em 元素精通
     */

    int d = 0;
    public P_Diluc() {
        // 构造方法:为新增属性赋初值

        super("迪卢克","简单一击","大胆开冲","冲的火热", 1600, 149, 61, 0, Element.H);

        setNormalAttackBonus(0.33);
        setSkillAttackBonus(2.2);
        setUltimateAttackBonus(3.4);
        setElementalDmgBonus(0);
    }



    /**
     * 重写普通攻击方法
     * 普通攻击伤害
     * this.getHp()是获取当前生命值
     * this.getHp()/4是当前生命值的1/4
     * super.normalAttack()是普通攻击的伤害
     * this.getHp()/4+super.normalAttack()是普通攻击的伤害+当前生命值的1/4
     * this.setHp是设置当前生命值
     * this.setHp(this.getHp()-this.getHp()/4-super.normalAttack())是当前生命值-当前生命值的1/4-普通攻击的伤害
     * this.getatk()是获取物理攻击力
     * this.getdef()是获取物理防御力
     * this.setatk是设置物理攻击力
     * this.setdef是设置物理防御力
     * 以此类推
     */
    @Override
    public int normalAttack() {
        if (d>0){
            d-=1;
            return (int) (super.normalAttack()*(getNormalAttackBonus()+1)*(getElementalDmgBonus()+1+1));
        }
        return (int) (super.normalAttack()*(getNormalAttackBonus()+1));
    }

    /**
     * 重写技能攻击方法
     */
    @Override
    public int skillAttack() {
        return (int) (super.skillAttack()+(getSkillAttackBonus()+1)*(getElementalDmgBonus()+1));
    }

    /**
     * 重写大招攻击方法
     */
    @Override
    public int ultimateAttack() {
        d +=1;
        return (int) (super.skillAttack()+(getUltimateAttackBonus()+1));
    }
}