public class P_hutao extends Character {


    public P_hutao() {
        // 构造方法:为新增属性赋初值
        super("胡桃","往生秘传枪法","蝶引来生","安神秘法", 1211, 132, 68, 0,Element.F);
        setNormalAttackBonus(0.469);
        setSkillAttackBonus(0);
        setUltimateAttackBonus(3.03);
    }


    int s = 0;
    int maxHp = this.getMaxHp();

    @Override
    public int normalAttack() {
        int nre = (int) (super.normalAttack()*(this.getNormalAttackBonus()+1)*(getElementalDmgBonus()+1));
        if (s==2) {
            setAtk((int) (getAtk()+this.getMaxHp()*0.036));
            s-=1;
            return nre;
        }else if (s==1){
            s-=1;
            nre = (int) (super.normalAttack()*(this.getNormalAttackBonus()+1)*(getElementalDmgBonus()+1));
            setAtk((int) (getAtk()-this.getMaxHp()*0.036));
            return nre;
        }
        return nre;
    }

    @Override
    public int skillAttack() {
        s=2;
        setHp((int) (getHp() - (getHp()*0.3)));
        return 0;
    }

    @Override
    public int ultimateAttack() {
        if (this.getHp() < maxHp/2){
            setHp((int) (getHp()+(maxHp*0.08)));
        }else {
            setHp((int) (getHp()+(maxHp*0.06)));
        }
        if (getHp()>maxHp){
            setHp(maxHp);
        }
        return (int) (super.normalAttack()*(this.getUltimateAttackBonus()+1)*(getElementalDmgBonus()+1));
    }

    int ys = 0;
    @Override
    public int passiveSkill(){
        if (this.getHp() < maxHp/2 && ys == 0){
            ys+=1;
            setElementalDmgBonus(getElementalDmgBonus()+0.12);
        }else if (this.getHp() < maxHp/2 && ys != 0){
            setElementalDmgBonus(getElementalDmgBonus()-0.12);
            ys=0;
            ys+=1;
            setElementalDmgBonus(getElementalDmgBonus()+0.12);
        }
        return 0;
    }
}


