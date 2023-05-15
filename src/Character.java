public abstract class Character {
    public Character() { }
    public enum Element {
        W,   //无
        H,   //火
        S,   //水
        C,   //草
        L,   //雷
        F,   //风
        Y,   //岩
        B    //冰
    }

    private String name;         //角色名称
    private String normalAttackName = "普通攻击"; //普通攻击名称
    private String skillAttackName = "技能攻击";  //技能攻击名称
    private String ultimateAttackName = "大招攻击"; //大招攻击名称
    private int hp;              //生命值
    private int maxHp;           //最大生命值
    private int atk;             //攻击力
    private int def;             //防御力
    private int em;              //元素精通
    private int elementalEnergy = 0;//元素充能值

    private double normalAttackBonus = 0;    //普通攻击加成
    private double skillAttackBonus = 0;     //技能攻击加成
    private double ultimateAttackBonus = 0;  //大招攻击加成
    private double physicalDmgBonus = 0;   //物理伤害加成
    private double elementalDmgBonus = 0;  //元素伤害加成
    private double critRate = 0;      //暴击率
    private double critDmg = 0;       //暴击伤害
    private Element element;     //元素类型

    private Element attachedElement = Element.W; //附着元素类型

    public Character(String name, int hp, int atk, int def, int em, Element element) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.atk = atk;
        this.def = def;
        this.em = em;
        this.element = element;
    }
    public Character(String name, String normalAttackName,String skillAttackName,String ultimateAttackName, int hp, int atk, int def, int em, Element element) {
        this.name = name;
        this.normalAttackName = normalAttackName;
        this.skillAttackName = skillAttackName;
        this.ultimateAttackName = ultimateAttackName;
        this.hp = hp;
        this.maxHp = hp;
        this.atk = atk;
        this.def = def;
        this.em = em;
        this.element = element;
    }

    //获取属性
    public String getName() {
        return name;
    }

    public String getNormalAttackName() {
        return normalAttackName;
    }

    public String getSkillAttackName() {
        return skillAttackName;
    }

    public String getUltimateAttackName() {
        return ultimateAttackName;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public int getEm() {
        return em;
    }

    public Element getElement() {
        return element;
    }

    public double getNormalAttackBonus() {
        return normalAttackBonus;
    }

    public double getSkillAttackBonus() {
        return skillAttackBonus;
    }

    public double getUltimateAttackBonus() {
        return ultimateAttackBonus;
    }

    public double getPhysicalDmgBonus() {
        return physicalDmgBonus;
    }

    public double getElementalDmgBonus() {
        return elementalDmgBonus;
    }

    public double getCritRate() {
        return critRate;
    }

    public double getCritDmg() {
        return critDmg;
    }


    //设置属性
    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public void setEm(int em) {
        this.em = em;
    }

    public void setNormalAttackBonus(double normalAttackBonus) {
        this.normalAttackBonus = normalAttackBonus;
    }

    public void setSkillAttackBonus(double skillAttackBonus) {
        this.skillAttackBonus = skillAttackBonus;
    }

    public void setUltimateAttackBonus(double ultimateAttackBonus) {
        this.ultimateAttackBonus = ultimateAttackBonus;
    }

    public void setPhysicalDmgBonus(double physicalDmgBonus) {
        this.physicalDmgBonus = physicalDmgBonus;
    }

    public void setElementalDmgBonus(double elementalDmgBonus) {
        this.elementalDmgBonus = elementalDmgBonus;
    }

    public void setCritRate(double critRate) {
        this.critRate = critRate;
    }

    public void setCritDmg(double critDmg) {
        this.critDmg = critDmg;
    }

    // 普通攻击
    public int normalAttack() {
        return atk;      // 直接返回攻击力作为伤害
    }

    // 技能攻击
    public int skillAttack() {
        return atk * 2;  // 技能攻击力默认为物理攻击力的2倍
    }

    // 大招攻击
    public int ultimateAttack() {
        return atk * 3;  // 大招攻击力为物理攻击力的3倍
    }

    // 被动技能
    public int passiveSkill() {
        // 默认没有被动技能
        return 0;
    }

    // 防御方法
    public int defense(int dmg) {
        // 减去角色的防御力得到最终伤害
        return dmg - this.def;
    }

    // 回复生命方法
    public void heal(int val) {
        // 生命值增加输入值
        this.hp += val;
    }

    // 充能方法
    public void charge(int val) {
        // 充能值增加输入值
        this.elementalEnergy += val;
    }

    // 死亡检查方法
    public boolean isDead() {
        // 如果生命值<=0 则死亡
        return this.hp <= 0;
    }
}
