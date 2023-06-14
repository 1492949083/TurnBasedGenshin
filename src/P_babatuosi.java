public class P_babatuosi extends Character {
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
    int i = 0;

    public P_babatuosi() {
        // 构造方法:为新增属性赋初值00
        super("温迪","神代射术","高天之歌","风神之诗", 1300, 144, 52, 0,Element.F);
        setNormalAttackBonus(0.408);
        setSkillAttackBonus(2.76);
        setUltimateAttackBonus(1.52);
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
        i = 3;
        return (int) (super.normalAttack()*(this.getUltimateAttackBonus()+1));
    }

    @Override
    public int passiveSkill() {
        if (i>0){
            i -= 1;
            return (int) (super.normalAttack()*(this.getUltimateAttackBonus()+1));
        }else {
            return 0;
        }
    }



}
