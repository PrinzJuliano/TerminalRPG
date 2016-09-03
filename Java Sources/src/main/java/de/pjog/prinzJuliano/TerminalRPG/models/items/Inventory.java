package de.pjog.prinzJuliano.TerminalRPG.models.items;

/**
 * @author PrinzJuliano
 */
public class Inventory {
    private Item[] loot;
    private int size;

    public Inventory(int size){
    	this.size = size;
        loot = new Item[size];
    }
    
    public boolean addItem(int slot, Item i)
    {
    	if(slot < size && slot >= 0)
    	{
    		if(loot[slot] == null){
    			loot[slot] = i;
    			return true;
    		}else{
    			
    		}
    	}
    	return false;
    }
    
    public boolean addItem(Item i)
    {
    	if(tryStacking(i))
    		return true;
    	
    	int slot = findNextFreeSlot();
    	
    	if(slot == -1)
    		return false;
    	
    	return addItem(slot, i);
    	
    }
    
    public boolean tryStacking(Item item)
    {
    	for(int i = 0; i < size; i++)
    	{
    		if(loot[i] != null && loot[i].equals(item))
    		{
    			if(loot[i].addToQuantity(item.getQuantity()))
    				return true;
    		}
    	}
    	return false;
    }
    
    private int findNextFreeSlot(){
    	for(int i = 0; i < size; i++)
    		if(loot[i] == null)
    			return i;
    	return -1;
    }
    
    public Item getItem(Item i)
    {
    	return null;
    }
    
    public Item getItemByName(String name)
    {
    	return null;
    }
    
    public Item getItemByID(long id){
    	return null;
    }

}
