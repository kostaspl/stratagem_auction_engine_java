package co.stratagem.auctionengine;

import static org.junit.Assert.*;

import org.junit.Test;

public class AuctionItemTest {

	@Test
	public void testAuctionItem() {
		try {
			new AuctionItem(null);
			fail("User with null name");
		} catch (IllegalArgumentException ex) { }
		
		try {
			new AuctionItem("");
			fail("User with empty name");
		} catch (IllegalArgumentException ex) { }
		
		try {
			new AuctionItem(" ");
			fail("User with empty (when trimmed) name");
		} catch (IllegalArgumentException ex) { }
	}

	@Test
	public void testBid() {
		UniqueTracker tracker = UniqueTracker.getInstance();
		User user = tracker.createUser("testBid");
		AuctionItem item = tracker.createAuction("testBid");
		
		try {
			item.bid(null, 10);
			fail("item.bid() with null user");
		} catch (IllegalArgumentException ex) { }
		
		assertTrue(item.getBids().size() == 0);
		assertTrue(item.bid(user, 1));
		assertTrue(item.getBids().size() == 1);
		assertFalse(item.bid(user, 1));
		assertTrue(item.bid(user, 1.5f));
		assertTrue(item.getBids().size() == 2);
	}

	@Test
	public void testGetWinningBid() {
		UniqueTracker tracker = UniqueTracker.getInstance();
		User user = tracker.createUser("testGetWinningBid");
		AuctionItem item = tracker.createAuction("testGetWinningBid");
		
		assertNull(item.getWinningBid());
		user.bidOn(item, 1);
		assertNotNull(item.getWinningBid());
		user.bidOn(item, 2);
		user.bidOn(item, 1);
		user.bidOn(item, 2.5f);
		assertFalse(user.bidOn(item, 2.5f));
		
		float tempPrice = 0;
		Bid maxBid = null;
		for (Bid bid : item.getBids()){
			if (bid.getPrice() > tempPrice){
				tempPrice = bid.getPrice();
				maxBid = bid;
			}
		}
		assertEquals(maxBid, item.getWinningBid());
	}

}
