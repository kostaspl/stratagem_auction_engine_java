package co.stratagem.auctionengine;

import static org.junit.Assert.*;

import org.junit.Test;

public class UniqueTrackerTest {

	@Test
	public void testGetInstance() {
		UniqueTracker t1 = UniqueTracker.getInstance();
		UniqueTracker t2 = UniqueTracker.getInstance();
		assertNotNull(t1);
		assertEquals(t1, t2);
	}

	@Test
	public void testCreateUser() {
		UniqueTracker tracker = UniqueTracker.getInstance();
		assertNotNull(tracker.createUser("testCreateUser"));
		assertNull(tracker.createUser("testCreateUser"));
	}

	@Test
	public void testCreateAuction() {
		UniqueTracker tracker = UniqueTracker.getInstance();
		assertNotNull(tracker.createAuction("testCreateAuction"));
		assertNull(tracker.createAuction("testCreateAuction"));
	}

	@Test
	public void testFindUser() {
		UniqueTracker tracker = UniqueTracker.getInstance();
		tracker.createUser("testFindUser");
		assertNotNull(tracker.findUser("testFindUser"));
		assertNull(tracker.findUser("NotExistingUser"));
		assertNull(tracker.findUser(null));
	}

	@Test
	public void testFindAuction() {
		UniqueTracker tracker = UniqueTracker.getInstance();
		tracker.createAuction("testFindAuction");
		assertNotNull(tracker.findAuction("testFindAuction"));
		assertNull(tracker.findAuction("NotExistingAuction"));
		assertNull(tracker.findAuction(null));
	}
}
