
public class MemberOrder extends UniformOrder{
	private boolean blackgloves;
	private boolean greyplume;
	
	public boolean isBlackgloves() {
		return blackgloves;
	}
	public void setBlackgloves(boolean blackgloves) {
		this.blackgloves = blackgloves;
	}
	public boolean isGreyPlume() {
		return greyplume;
	}
	public void setGreyPlume(boolean greyPlume) {
		this.greyplume = greyPlume;
	}
	
	public double getSubTotal(Prices pUniformPrices)
	{
		double subTotal=0;
		
		if (greyplume)
			subTotal += pUniformPrices.getGreyPlumePrice();
		if (blackgloves)
			subTotal += pUniformPrices.getBlackglovesPrice();
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
