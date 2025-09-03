public class Knight extends Fighter {
    public Knight(int baseHp, int wp) {
        super(baseHp, wp);
    }

    @Override
    public double getCombatScore() {
        int ground = Battle.GROUND;
        double score;
        if (getWp() == 1) {
            score = getBaseHp();
        } else {
            score = getBaseHp() / 10.0;
        }
         if (Utility.isSquare(ground)) {
            score = getBaseHp()*2;
        }
        return Math.min(score, 999);
    }
}
