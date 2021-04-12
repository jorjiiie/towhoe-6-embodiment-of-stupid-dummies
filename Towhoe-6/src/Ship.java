/*
ship interface used for all ship type objects so we can ensure they work nicely with eachother
author: probably irrelevant
date: today
*/
import java.awt.*;

import java.util.ArrayList;

public interface Ship {
	public ArrayList<Bullet> shoot();
}