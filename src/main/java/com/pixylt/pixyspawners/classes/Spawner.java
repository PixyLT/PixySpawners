package com.pixylt.pixyspawners.classes;

public class Spawner {
    private String loc;
    private String owner;
    private String count;
    private String type;

    public Spawner(String loc, String owner, int count, String type){
        this.loc = loc;
        this.owner = owner;
        this.count = String.valueOf(count);
        this.type = type;
    }

    public String getOwner() {
        return this.owner;
    }
    public String getCount() {
        return this.count;
    }
    public String getLoc() {
        return this.loc;
    }
    public String getType() {
        return this.type;
    }
}
