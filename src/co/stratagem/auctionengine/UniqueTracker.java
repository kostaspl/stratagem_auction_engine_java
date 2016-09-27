package co.stratagem.auctionengine;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import co.stratagem.auctionengine.interfaces.ITracker;

/*
 * A ITracker implementation that does not allow users or items with the same name (case sensitive).
 */
public class UniqueTracker implements ITracker {

	private static UniqueTracker sInstance;

	private Map<String, AuctionItem> mAuctions;
	private Map<String, User> mUsers;

	private UniqueTracker() {
		mAuctions = new HashMap<String, AuctionItem>();
		mUsers = new HashMap<String, User>();
	}

	public static UniqueTracker getInstance() {
		if (sInstance == null) {
			sInstance = new UniqueTracker();
		}
		return sInstance;
	}

	@Override
	public User createUser(String userName) {
		if (userName != null && mUsers.containsKey(userName)){
			return null;
		}
		User user = new User(userName);
		mUsers.put(userName, user);
		return user;
	}

	@Override
	public AuctionItem createAuction(String itemName) {
		if (itemName != null && mAuctions.containsKey(itemName)){
			return null;
		}
		AuctionItem auction = new AuctionItem(itemName);
		mAuctions.put(itemName, auction);
		return auction;
	}

	@Override
	public User findUser(String userName) {
		if (userName != null) {
			return mUsers.get(userName);
		}
		return null;
	}

	@Override
	public AuctionItem findAuction(String itemName) {
		if (itemName != null) {
			return mAuctions.get(itemName);
		}
		return null;
	}

	@Override
	public Collection<User> getUsers() {
		return mUsers.values();
	}

	@Override
	public Collection<AuctionItem> getAuctions() {
		return mAuctions.values();
	}
}
