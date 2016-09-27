package co.stratagem.auctionengine;

import java.util.List;
import java.util.Random;

import co.stratagem.auctionengine.interfaces.ITracker;

public class Example {

	private static Random sRand = new Random(System.currentTimeMillis());

	public static int randInt(int min, int max) {
		return sRand.nextInt((max - min) + 1) + min;
	}

	public static float randFloat(int min, int max) {
		return randInt(min, max - 1) + sRand.nextFloat();
	}

	public static void main(String[] args) {
		ITracker tracker = UniqueTracker.getInstance();
		
		System.out.println("Creating some auctions...");
		tracker.createAuction("iPhone");
		tracker.createAuction("PlayStation");
		tracker.createAuction("XBOX");
		tracker.createAuction("MacBook");
		tracker.createAuction("Note7");
		tracker.createAuction("iPad");
		tracker.createAuction("ZenBook");
		
		for (AuctionItem item : tracker.getAuctions()){
			System.out.println(item);
		}
		
		System.out.println("\nCreating some users...");
		tracker.createUser("Kostas");
		tracker.createUser("John");
		tracker.createUser("George");
		tracker.createUser("Nick");
		
		for (User user : tracker.getUsers()){
			System.out.println(user);
		}
		
		List<User> users = tracker.getUsers();
		List<AuctionItem> items = tracker.getAuctions();
		int numberOfUsers = users.size();
		int numberOfItems = items.size();
		
		// test 20 random bids
		System.out.println("\nPlacing some random bids...");
		for (int i = 0; i < 20; i++){
			// pick a random price from 1 to 100
			float price = randFloat(1, 100);
			// pick a random user
			User user = tracker.getUsers().get(randInt(0, numberOfUsers - 1));
			// pick a random item
			AuctionItem item = tracker.getAuctions().get(randInt(0, numberOfItems - 1));
			
			Bid winningBid = item.getWinningBid();
			
			if (user.hasBidOn(item) && winningBid.getBidder().equals(user)){
				System.out.println(user.getName() + " skipped bidding because he is already the highest bidder for " + item.getName());
				continue;
			}
			if (user.bidOn(item, price)){
				System.out.println(user.getName() + " successfully bid on " + item.getName() + " for " + price);
			} else {
				System.out.println(user.getName() + " failed to bid on " + item.getName() + " for " + price + ". Current winning bid price is " + winningBid.getPrice());
			}
		}
		
		System.out.println("\nPrinting winning bids for every item...");
		Bid winningBid = null;
		for (AuctionItem item : items){
			winningBid = item.getWinningBid();
			if (winningBid == null){
				System.out.println("No one bid on " + item.getName());
			} else {
				System.out.println(winningBid.getBidder().getName() + " offered " + winningBid.getPrice() + " for " + item.getName());
			}
		}
		
		System.out.println("\nPrinting all bids for every item...");
		for (AuctionItem item : items){
			if (item.hasBids()){
				System.out.println("Bids for " + item.getName() + ":");
				for (Bid bid : item.getBids()){
					System.out.println("\t" + bid);
				}
			} else {
				System.out.println("No bids placed for " + item.getName());
			}
		}
		
		System.out.println("\nPrinting all items each user has bid on...");
		for (User user : users){
			if (user.hasBid()){
				System.out.println(user.getName() + " has bid on:");
				for (AuctionItem item : user.getItemsBidOn()){
					System.out.println("\t" + item);
				}
			} else {
				System.out.println(user.getName() + " has placed no bids");
			}
		}
	}
}
