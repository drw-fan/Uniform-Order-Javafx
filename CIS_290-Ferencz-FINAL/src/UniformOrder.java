public abstract class UniformOrder {

	final double TAX = .06;
	
	protected String name;
	protected boolean drillmasters;
	protected String drillmastersSize;
	protected boolean shako;
	protected String shakoSize;
	protected boolean bibs;
	protected String bibsSize;
	protected boolean jacket;
	protected String jacketSize;
	protected boolean trackPants;
	protected String trackPantsSize;
	protected boolean memberShirt;
	protected String memberShirtSize;
	protected boolean memberJacket;
	protected String memberJacketSize;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isShako() {
		return shako;
	}
	public void setShako(boolean shako) {
		this.shako = shako;
	}
	public String getShakoSize() {
		return shakoSize;
	}
	public void setShakoSize(String shakoSize) {
		this.shakoSize = shakoSize;
	}

	public boolean isDrillmasters() {
		return drillmasters;
	}
	public void setDrillmasters(boolean drillmasters) {
		this.drillmasters = drillmasters;
	}
	public String getDrillmastersSize() {
		return drillmastersSize;
	}
	public void setDrillmastersSize(String drillmastersSize) {
		this.drillmastersSize = drillmastersSize;
	}

	public boolean isBibs() {
		return bibs;
	}
	public void setBibs(boolean bibs) {
		this.bibs = bibs;
	}
	public String getBibsSize() {
		return bibsSize;
	}
	public void setBibsSize(String bibsSize) {
		this.bibsSize = bibsSize;
	}
	public boolean isJacket() {
		return jacket;
	}
	public void setJacket(boolean jacket) {
		this.jacket = jacket;
	}
	public String getJacketSize() {
		return jacketSize;
	}
	public void setJacketSize(String jacketSize) {
		this.jacketSize = jacketSize;
	}
	public boolean isTrackPants() {
		return trackPants;
	}
	public void setTrackPants(boolean trackPants) {
		this.trackPants = trackPants;
	}
	public String getTrackPantsSize() {
		return trackPantsSize;
	}
	public void setTrackPantsSize(String trackPantsSize) {
		this.trackPantsSize = trackPantsSize;
	}
	public boolean isMemberShirt() {
		return memberShirt;
	}
	public void setMemberShirt(boolean memberShirt) {
		this.memberShirt = memberShirt;
	}
	public String getMemberShirtSize() {
		return memberShirtSize;
	}
	public void setMemberShirtSize(String memberShirtSize) {
		this.memberShirtSize = memberShirtSize;
	}
	public boolean isMemberJacket() {
		return memberJacket;
	}
	public void setMemberJacket(boolean memberJacket) {
		this.memberJacket = memberJacket;
	}
	public String getMemberJacketSize() {
		return memberJacketSize;
	}
	public void setMemberJacketSize(String memberJacketSize) {
		this.memberJacketSize = memberJacketSize;
	}
	

	
}
