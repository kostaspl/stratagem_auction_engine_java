package co.stratagem.auctionengine;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {

	@Test
	public void testUser() {
		try {
			new User(null);
			fail("User with null name");
		} catch (IllegalArgumentException ex) { }
		
		try {
			new User("");
			fail("User with empty name");
		} catch (IllegalArgumentException ex) { }
		
		try {
			new User(" ");
			fail("User with empty (when trimmed) name");
		} catch (IllegalArgumentException ex) { }
	}

	@Test
	public void testHasBid() {
		UniqueTracker tracker = UniqueTracker.getInstance();
		User user = tracker.createUser("testHasBid");
		assertFalse(user.hasBid());
		AuctionItem item = tracker.createAuction("testHasBid");
		user.bidOn(item, 1);
		assertTrue(user.hasBid());
	}

	@Test
	public void testBidOn() {
		UniqueTracker tracker = UniqueTracker.getInstance();
		User user = tracker.createUser("testBidOn");
		AuctionItem item = tracker.createAuction("testBidOn");
		AuctionItem item2 = tracker.createAuction("testBidOn2");
		
		try {
			user.bidOn(null, 10);
			fail("user.bidOn() with null item");
		} catch (IllegalArgumentException ex) { }
		
		assertTrue(user.getItemsBidOn().size() == 0);
		assertTrue(user.bidOn(item, 1));
		assertFalse(user.bidOn(item, 1));
		assertTrue(user.bidOn(item, 1.5f));
		assertTrue(user.getItemsBidOn().size() == 1);
		assertTrue(user.bidOn(item2, 1));
		assertTrue(user.getItemsBidOn().size() == 2);
	}

	@Test
	public void testHasBidOn() {
		UniqueTracker tracker = UniqueTracker.getInstance();
		User user = tracker.createUser("testHasBidOn");
		AuctionItem item = tracker.createAuction("testHasBidOn");
		
		try {
			user.hasBidOn(null);
			fail("user.hasBidOn() with null item");
		} catch (IllegalArgumentException ex) { }
		
		assertFalse(user.hasBidOn(item));
		user.bidOn(item, 1);
		assertTrue(user.hasBidOn(item));
	}

	@Test
	public void testIsHighestBidderFor() {
		UniqueTracker tracker = UniqueTracker.getInstance();
		User user = tracker.createUser("isHighestBidderFor");
		User user2 = tracker.createUser("isHighestBidderFor2");
		AuctionItem item = tracker.createAuction("isHighestBidderFor");
		
		try {
			user.isHighestBidderFor(null);
			fail("user.isHighestBidderFor() with null item");
		} catch (IllegalArgumentException ex) { }
		
		assertFalse(user.isHighestBidderFor(item));
		user.bidOn(item, 1);
		assertTrue(user.isHighestBidderFor(item));
		user2.bidOn(item, 2);
		assertFalse(user.isHighestBidderFor(item));
		assertTrue(user2.isHighestBidderFor(item));
	}

}
