package co.stratagem.auctionengine;

import java.util.LinkedList;
import java.util.List;
import co.stratagem.auctionengine.interfaces.ITracker;

/*
 * A ITracker implementation that does not allow users or items with the same name (case sensitive).
 */
public class UniqueTracker implements ITracker {

	private static UniqueTracker sInstance;

	private List<AuctionItem> mAuctions;
	private List<User> mUsers;

	private UniqueTracker() {
		mAuctions = new LinkedList<AuctionItem>();
		mUsers = new LinkedList<User>();
	}

	public static UniqueTracker getInstance() {
		if (sInstance == null) {
			sInstance = new UniqueTracker();
		}
		return sInstance;
	}

	@Override
	public User createUser(String userName) {
		if (userName == null){
			return null;
		}
		for (User user : mUsers){
			if (user.getName().equals(userName)){
				return null;
			}
		}
		User user = new User(userName);
		mUsers.add(user);
		return user;
	}

	@Override
	public AuctionItem createAuction(String itemName) {
		if (itemName == null){
			return null;
		}
		for (AuctionItem item : mAuctions){
			if (item.getName().equals(itemName)){
				return null;
			}
		}
		AuctionItem auction = new AuctionItem(itemName);
		mAuctions.add(auction);
		return auction;
	}

	@Override
	public User findUser(String userName) {
		if (userName != null) {
			for (User user : mUsers){
				if (user.getName().equals(userName)){
					return user;
				}
			}
		}
		return null;
	}

	@Override
	public AuctionItem findAuction(String itemName) {
		if (itemName != null) {
			for (AuctionItem item : mAuctions){
				if (item.getName().equals(itemName)){
					return item;
				}
			}
		}
		return null;
	}

	@Override
	public List<User> getUsers() {
		return mUsers;
	}

	@Override
	public List<AuctionItem> getAuctions() {
		return mAuctions;
	}
}
