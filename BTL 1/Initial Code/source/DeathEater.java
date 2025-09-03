public class DeathEater extends Monster implements Combatable {
	private Complex manas;
	public DeathEater(Complex mana) {
		super(mana);
		this.manas = mana;
	}

	@Override
	public double getCombatScore() {
		return manas.getMagnitude();
	}
}
