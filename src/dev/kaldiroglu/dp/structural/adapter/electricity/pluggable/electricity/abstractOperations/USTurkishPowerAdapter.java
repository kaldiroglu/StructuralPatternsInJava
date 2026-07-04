package dev.kaldiroglu.dp.structural.adapter.electricity.pluggable.electricity.abstractOperations;

public class USTurkishPowerAdapter extends PowerAdapter {
	private USPowerSource usPowerSource;
	private boolean on = false;
	
	public USTurkishPowerAdapter(USPowerSource usPowerSource){
		System.out.println("\nUSTurkishPowerAdapter: Converting from USPowerSource to TurkishPowerSource");
		this.usPowerSource = usPowerSource;
	}

	@Override
	protected void deliver() {
		usPowerSource.pushSwitch();
		convert110To220();
	}

	@Override
	protected void cut() {
		usPowerSource.pushSwitch();
	}

	private void convert110To220(){
		System.out.println("USTurkishPowerAdapter: Converting from 110V to 220V");
	}
}
