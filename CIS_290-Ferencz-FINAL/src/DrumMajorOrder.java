
public class DrumMajorOrder extends UniformOrder{
	private boolean whitegloves;
	private boolean blackplume;
	private boolean baton;
	
	public boolean isWhitegloves() {
		return whitegloves;
	}
	public void setWhitegloves(boolean whitegloves) {
		this.whitegloves = whitegloves;
	}
	public boolean isBlackPlume() {
		return blackplume;
	}
	public void setBlackPlume(boolean blackPlume) {
		this.blackplume = blackPlume;
	}
	public boolean isBaton() {
		return baton;
	}
	public void setBaton(boolean baton) {
		this.baton = baton;
	}
	
	public double getSubTotal(Prices pUniformPrices)
	{
		double subTotal=0;
		
		if (baton)
			subTotal += pUniformPrices.getBatonPrice();
		if (blackplume)
			subTotal += pUniformPrices.getBlackplumePrice();
		if (whitegloves)
			subTotal += pUniformPrices.getWhiteglovesPrice();
		if (drillmasters)
			subTotal += pUniformPrices.getDrillmastersPrice();
		if (shako)
			subTotal += pUniformPrices.getShakoPrice();
		if (bibs)
			subTotal += pUniformPrices.getBibsPrice();
		if (jacket)
			subTotal += pUniformPrices.getJacketPrice();
		if (trackPants)
			subTotal += pUniformPrices.getTrackpantsPrice();
		if (memberShirt)
			subTotal += pUniformPrices.getMembershirtPrice();
		if (memberJacket)
			subTotal += pUniformPrices.getMemberjacketPrice();
		
		return subTotal;
	}
	
	public double getTax(Prices pUniformPrices)
	{
		return (getSubTotal(pUniformPrices) * TAX);
	}
	
	public double getTotal(Prices pUniformPrices) {
		return (getSubTotal(pUniformPrices) + getTax(pUniformPrices));
	}



}
