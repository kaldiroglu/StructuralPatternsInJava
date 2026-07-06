package dev.kaldiroglu.dp.structural.adapter.electricity.pluggable.electricity.abstractOperations;

public class UKTurkishPowerAdapter extends PowerAdapter {
	private UKPowerSource ukPowerSource;

	public UKTurkishPowerAdapter(UKPowerSource ukPowerSource){
		System.out.println("\nUSTurkishPowerAdapter: Converting from USPowerSource to TurkishPowerSource");
		this.ukPowerSource = ukPowerSource;
	}

	@Override
	protected void deliver() {
		ukPowerSource.flipToggle();
	}

	@Override
	protected void cut() {
		ukPowerSource.flipToggle();
	}
}
