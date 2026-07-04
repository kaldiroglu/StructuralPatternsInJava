package dev.kaldiroglu.dp.structural.adapter.electricity.pluggable.electricity.abstractOperations;

public class UKTurkishPowerAdapter extends PowerAdapter {
	private UKPowerSource ukPowerSource;
	private boolean on = false;

	public UKTurkishPowerAdapter(UKPowerSource ukPowerSource){
		System.out.println("\nUSTurkishPowerAdapter: Converting from USPowerSource to TurkishPowerSource");
		this.ukPowerSource = ukPowerSource;
	}

	@Override
	protected void deliver() {
		ukPowerSource.flipToggle();
		convert110To220();
	}

	@Override
	protected void cut() {
		ukPowerSource.flipToggle();
	}

	private void convert110To220(){
		System.out.println("UKTurkishPowerAdapter: Converting from 110V to 220V");
	}
}
