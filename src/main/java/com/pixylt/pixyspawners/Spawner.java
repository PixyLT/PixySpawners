package com.pixylt.pixyspawners;

public class Spawner {
    private String loc;
    private String owner;
    private String holo;
    private String count;

    public Spawner(String loc, String owner, String holo, int count){
        this.loc = loc;
        this.owner = owner;
        this.holo = holo;
        this.count = String.valueOf(count);
    }

    public String getOwner() {
        return this.owner;
    }
    public String getCount() {
        return this.count;
    }
    public String getHolo() {
        return this.holo;
    }
}
