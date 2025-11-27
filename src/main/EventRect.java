package main;

import java.awt.Rectangle;

//EventRect.java (or inside EventHandler as static class)

public class EventRect extends Rectangle {
	public boolean isEvent = false;   // true if this cell should check/draw an event
	public boolean eventDone = false; // true after triggered
	public int eventRectDefaultX;
	public int eventRectDefaultY;
	public int eventType = 0;         // 0 = none, 1 = PIT, 2 = HEAL, etc.
}
