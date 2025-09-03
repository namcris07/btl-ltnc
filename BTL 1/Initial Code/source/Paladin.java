public class Paladin extends Knight {
	private int mbaseHp;
	public Paladin(int baseHp, int wp) {
		super(baseHp, wp);
		this.mbaseHp = baseHp;
	}

	@Override
	public double getCombatScore() {
		int combatScore = mbaseHp * 3;
		int n = isFibonacci(mbaseHp);
		if (n > 2) {
			combatScore = 1000 + n;
		}
		return combatScore;
	}
	
	private int isFibonacci(int num) {
		if (num == 0)
			return 0;
		if (num == 1)
			return 1;
		int a = 0, b = 1, c = 1, n = 1;
		while (c < num) {
			c = a + b;
			a = b;
			b = c;
			n++;
		}
		return (c == num) ? n : -1;
	}
}
