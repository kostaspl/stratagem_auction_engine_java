package co.stratagem.auctionengine;

import java.util.HashSet;
import java.util.Set;

public class User {
	private String mName;
	private Set<AuctionItem> mItemsBidOn;
	
	public User(String name){
		if (name == null || name.trim().isEmpty()){
			throw new IllegalArgumentException("User name cannot be empty");
		}
		mName = name;
		mItemsBidOn = new HashSet<AuctionItem>();
	}
	
	public String getName(){
		return mName;
	}
	
	public Set<AuctionItem> getItemsBidOn(){
		return mItemsBidOn;
	}
	
	public boolean hasBid(){
		return !mItemsBidOn.isEmpty();
	}
	
	public boolean bidOn(AuctionItem item, float price){
		if (item == null){
			throw new IllegalArgumentException("Item cannot be null");
		}
		return item.bid(this, price);
	}
	
	public boolean hasBidOn(AuctionItem item){
		if (item == null){
			throw new IllegalArgumentException("Item cannot be null");
		}
		return mItemsBidOn.contains(item);
	}
	
	public boolean isHighestBidderFor(AuctionItem item){
		if (!hasBidOn(item)){
			return false;
		}
		return item.getWinningBid().getBidder().equals(this);
	}
	
	@Override
	public String toString(){
		return mName;
	}
}