package co.stratagem.auctionengine;

import static org.junit.Assert.*;

import org.junit.Test;

public class BidTest {

	@Test
	public void testBid() {
		try {
			new Bid(null, 10);
			fail("Bid with null bidder");
		} catch (IllegalArgumentException ex) { }
		
		User user = UniqueTracker.getInstance().createUser("Kostas");
		
		try {
			new Bid(user, 0);
			fail("Bid with 0 price");
		} catch (IllegalArgumentException ex) { }
		
		try {
			new Bid(user, -10);
			fail("Bid with negative price");
		} catch (IllegalArgumentException ex) { }
	}
	
}
