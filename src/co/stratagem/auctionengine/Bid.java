package co.stratagem.auctionengine;

public class Bid {
	private User mBidder;
	private float mPrice;
	
	public Bid(User bidder, float price){
		if (bidder == null){
			throw new IllegalArgumentException("Bidder cannot be null");
		}
		if (price <= 0){
			throw new IllegalArgumentException("Price cannot be negative or zero");
		}
		mBidder = bidder;
		mPrice = price;
	}
	
	public User getBidder(){
		return mBidder;
	}
	
	public float getPrice(){
		return mPrice;
	}
	
	@Override
	public String toString(){
		return mBidder.getName() + "'s bid for " + mPrice;
	}	
}
