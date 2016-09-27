package co.stratagem.auctionengine.interfaces;

import java.util.List;
import co.stratagem.auctionengine.AuctionItem;
import co.stratagem.auctionengine.User;

public interface ITracker {
	public User createUser(String userName);
	public AuctionItem createAuction(String itemName);
	
	public List<User> getUsers();
	public List<AuctionItem> getAuctions();
	
	public User findUser(String userName);
	public AuctionItem findAuction(String itemName);
}
