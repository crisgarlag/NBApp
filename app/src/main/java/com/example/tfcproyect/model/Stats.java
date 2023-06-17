package com.example.tfcproyect.model;

public class Stats {

    private int gamesPlayed;
    private int playerId;
    private int season;
    private String min;
    private float fgm;
    private float fga;
    private float fg3m;
    private float ftm;
    private float fta;
    private float oreb;
    private float dreb;
    private float reb;
    private float ast;
    private float stl;
    private float blk;
    private float turnover;
    private float pf;
    private float pts;
    private float fgpct;
    private float fg3pct;
    private float ftpct;

    public Stats(int gamesPlayed, int playerId, int season, String min, float fgm, float fga, float fg3m, float ftm, float fta, float oreb, float dreb, float reb, float ast, float stl, float blk, float turnover, float pf, float pts, float fgpct, float fg3pct, float ftpct) {
        this.gamesPlayed = gamesPlayed;
        this.playerId = playerId;
        this.season = season;
        this.min = min;
        this.fgm = fgm;
        this.fga = fga;
        this.fg3m = fg3m;
        this.ftm = ftm;
        this.fta = fta;
        this.oreb = oreb;
        this.dreb = dreb;
        this.reb = reb;
        this.ast = ast;
        this.stl = stl;
        this.blk = blk;
        this.turnover = turnover;
        this.pf = pf;
        this.pts = pts;
        this.fgpct = fgpct;
        this.fg3pct = fg3pct;
        this.ftpct = ftpct;
    }

    public Stats() {
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public float getFgm() {
        return fgm;
    }

    public void setFgm(float fgm) {
        this.fgm = fgm;
    }

    public float getFga() {
        return fga;
    }

    public void setFga(float fga) {
        this.fga = fga;
    }

    public float getFg3m() {
        return fg3m;
    }

    public void setFg3m(float fg3m) {
        this.fg3m = fg3m;
    }

    public float getFtm() {
        return ftm;
    }

    public void setFtm(float ftm) {
        this.ftm = ftm;
    }

    public float getFta() {
        return fta;
    }

    public void setFta(float fta) {
        this.fta = fta;
    }

    public float getOreb() {
        return oreb;
    }

    public void setOreb(float oreb) {
        this.oreb = oreb;
    }

    public float getDreb() {
        return dreb;
    }

    public void setDreb(float dreb) {
        this.dreb = dreb;
    }

    public float getReb() {
        return reb;
    }

    public void setReb(float reb) {
        this.reb = reb;
    }

    public float getAst() {
        return ast;
    }

    public void setAst(float ast) {
        this.ast = ast;
    }

    public float getStl() {
        return stl;
    }

    public void setStl(float stl) {
        this.stl = stl;
    }

    public float getBlk() {
        return blk;
    }

    public void setBlk(float blk) {
        this.blk = blk;
    }

    public float getTurnover() {
        return turnover;
    }

    public void setTurnover(float turnover) {
        this.turnover = turnover;
    }

    public float getPf() {
        return pf;
    }

    public void setPf(float pf) {
        this.pf = pf;
    }

    public float getPts() {
        return pts;
    }

    public void setPts(float pts) {
        this.pts = pts;
    }

    public float getFgpct() {
        return fgpct;
    }

    public void setFgpct(float fgpct) {
        this.fgpct = fgpct;
    }

    public float getFg3pct() {
        return fg3pct;
    }

    public void setFg3pct(float fg3pct) {
        this.fg3pct = fg3pct;
    }

    public float getFtpct() {
        return ftpct;
    }

    public void setFtpct(float ftpct) {
        this.ftpct = ftpct;
    }
}
