package co.stratagem.auctionengine;

import java.util.LinkedList;
import java.util.List;

public class AuctionItem {
	private String mName;
	private List<Bid> mBids;
	
	public AuctionItem(String name){
		if (name == null || name.trim().isEmpty()){
			throw new IllegalArgumentException("Item name cannot be empty");
		}
		mName = name;
		mBids = new LinkedList<Bid>();
	}
	
	public String getName(){
		return mName;
	}
	
	public List<Bid> getBids(){
		return mBids;
	}
	
	public boolean bid(User user, float price){
		if (user == null){
			throw new IllegalArgumentException("User cannot be null");
		}
		Bid newBid = new Bid(user, price);
		Bid winningBid = getWinningBid();
		if (winningBid != null){
			if (winningBid.getPrice() >= price){
				return false;
			}
		}
		mBids.add(0, newBid);
		user.getItemsBidOn().add(this);
		return true;
	}
	
	public boolean hasBids(){
		return !mBids.isEmpty();
	}
	
	public Bid getWinningBid(){
		if (mBids.isEmpty()){
			return null;
		}
		return mBids.get(0);
	}
	
	@Override
	public String toString(){
		return mName;
	}
}
