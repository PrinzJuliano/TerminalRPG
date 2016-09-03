package de.pjog.prinzJuliano.TerminalRPG.models.items;

import java.util.ArrayList;

import de.pjog.prinzJuliano.TerminalRPG.models.FightingClasses;

/**
 * @author PrinzJuliano
 */
public abstract class AbstractItem implements Item {

	public abstract String getPrefix();

	public abstract String getName();

	public abstract String getSuffix();

	public abstract String getType();

	public abstract long getID();

	protected ArrayList<FightingClasses> classes;
	protected int quantity;

	public AbstractItem() {
		classes = new ArrayList<>();
		for (FightingClasses c : FightingClasses.values())
			classes.add(c);
		this.quantity = 1;
	}

	public AbstractItem(int quantity) {
		this();
		this.quantity = Math.min(quantity, getStackSize());
	}

	public Rarity getRarity() {
		return Rarity.COMMON;
	}

	public int getLevel() {
		return 1;
	}

	public ArrayList<FightingClasses> getSuitableClasses() {
		return classes;
	}

	public boolean equals(Item o) {

		if (o == null)
			return false;

		if (!this.getPrefix().equals(o.getPrefix()))
			return false;
		if (!this.getName().equals(o.getName()))
			return false;
		if (!this.getSuffix().equals(o.getSuffix()))
			return false;
		if (!this.getType().equals(o.getType()))
			return false;
		if (this.getID() != o.getID())
			return false;
		if (this.getRarity().compareTo(o.getRarity()) != 0)
			return false;
		if (this.getLevel() != o.getLevel())
			return false;
		if (!this.getSuitableClasses().containsAll(o.getSuitableClasses()))
			return false;

		return true;
	}

	public boolean isSuitableFor(FightingClasses c) {
		return getSuitableClasses().contains(c);
	}

	public boolean isStackable() {
		return true;
	}

	public int getStackSize() {
		return 100;
	}

	public int getQuantity() {
		return quantity;
	}

	public boolean addToQuantity(int amount) {
		if (isStackable()) {
			if (getQuantity() == getStackSize()) {
				return false;
			} else {
				int delta = getStackSize() - getQuantity();
				if (amount <= delta) {
					quantity += amount;
					return true;
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	}
}
